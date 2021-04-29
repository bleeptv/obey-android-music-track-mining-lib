package com.ygorxkharo.obey.utilities.threading

import kotlin.coroutines.CoroutineContext

/**
 * Provides coroutine context to run a particular task
 */
interface CoroutineContextProvider {

    /**
     * @property mainContext Used to perform operations on the main/application thread (foreground)
     */
    val mainContext: CoroutineContext

    /**
     * @property ioContext Used to perform operations on a background thread
     */
    val ioContext: CoroutineContext
}
