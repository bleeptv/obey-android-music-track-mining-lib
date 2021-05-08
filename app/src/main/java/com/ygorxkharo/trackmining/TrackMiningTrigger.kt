package com.ygorxkharo.trackmining

import android.util.Log
import com.ygorxkharo.obey.utilities.threading.CoroutineContextProvider
import com.ygorxkharo.obey.utilities.threading.ThreadingExecutor
import com.ygorxkharo.trackmining.di.CommonDIInjector
import kotlinx.coroutines.Job
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import java.util.ArrayList

class DefaultTrackMiningTrigger: TrackMiningTrigger,  DIAware {

    private val networkCallsCollection = ArrayList<Job>()

    override val di: DI = CommonDIInjector.diContainer
    override val useCase: MineLibraryTracksUseCase by di.instance()
    override val coroutineContextProvider: CoroutineContextProvider by di.instance()

    override fun mineTracks(platform: String) {
        val miningRequest = LibraryTrackMiningRequest(chosenPlatformName = platform)
        val currentMiningRequest = ThreadingExecutor.executeAsyncOperation(
            performInBackground = { useCase.execute(miningRequest) },
            performInMainProcess = {
                when(it) {
                    is Success -> Log.e("TrackMiningTrigger", "Got tracks: ${it.payload}")
                    is Failure -> Log.e("TrackMiningTrigger", "Failed to mine: ${it.error}")
                }
            },
            coroutineContextProvider
        )

        networkCallsCollection.add(currentMiningRequest)
    }

    /**
     * Cancel all network calls made to the HTTP resource
     */
    fun cancelAllRequests() {
        networkCallsCollection
            .filter { job -> job.isActive}
            .forEach { job -> job.cancel()}
        networkCallsCollection.clear()
    }
}

interface TrackMiningTrigger {
    val useCase: MineLibraryTracksUseCase
    val coroutineContextProvider: CoroutineContextProvider
    fun mineTracks(platform: String)
}