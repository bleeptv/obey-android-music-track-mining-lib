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
     * @param onTaskCompleted Runs an operation on the foreground/main application process
     * @param contextProvider Provides the appropriate coroutine context for the background/foreground
     * operations of a task
     * @return a [Job] which will be executed
     */
    fun <T : Any> executeAsyncOperation(
        performInBackground: suspend (() -> T),
        onTaskCompleted: ((T) -> Unit),
        contextProvider: CoroutineContextProvider
    ): Job {
        return CoroutineScope(contextProvider.mainContext).launch {
            val resultFromBackgroundTask = CoroutineScope(contextProvider.ioContext).async IOScope@{
                return@IOScope performInBackground()
            }.await()
            onTaskCompleted(resultFromBackgroundTask)
        }
    }
}
