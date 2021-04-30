package com.ygorxkharo.obey.utilities.threading

import kotlinx.coroutines.async
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Control programme execution between the background and main thread
 */
object ThreadingExecutor {

    /**
     *
     * @param performInBackground Runs an operation on the background process
     * @param performInMainProcess Runs an operation on the foreground/main application process
     * @param contextProvider Provides the appropriate coroutine context for the background/foreground
     * operations of a task
     * @return a [Job] which will be executed on the main application process
     */
    fun <T : Any> executeAsyncOperation(
        performInBackground: suspend (() -> T),
        performInMainProcess: ((T) -> Unit),
        contextProvider: CoroutineContextProvider
    ): Job {
        return CoroutineScope(contextProvider.mainContext).launch {
            val backgroundTaskResult = async(contextProvider.ioContext) { performInBackground() }
            performInMainProcess(backgroundTaskResult.await())
        }
    }
}
