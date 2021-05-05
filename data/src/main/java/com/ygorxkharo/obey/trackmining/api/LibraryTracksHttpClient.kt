package com.ygorxkharo.obey.trackmining.api

import kotlinx.coroutines.Job
import com.ygorxkharo.obey.common.Result
import com.ygorxkharo.trackmining.tracks.model.LibraryTrack

/**
 * Contract for fetching library tracks from various streaming platform sources
 *
 */
interface LibraryTracksHttpClient {

    /**
     * @property networkCallsCollection A collection of Coroutine [Job] so they can be cancelled at
     * any point if necessary
     */
    val networkCallsCollection: MutableList<Job>

    /**
     * Get library tracks from the a specific HTTP source
     *
     * @param authToken The user's authentication token to perform requests
     * @param resultLimit The maximum number of library tracks to return
     * @param onComplete Callback triggered when the request is completed (either with a successful
     * response or an error)
     */
    fun getLibraryTracks(
        authToken: String,
        resultLimit: Int,
        onComplete: (Result<List<LibraryTrack>>) -> Unit
    )

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