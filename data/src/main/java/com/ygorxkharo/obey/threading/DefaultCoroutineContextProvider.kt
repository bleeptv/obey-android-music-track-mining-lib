package com.ygorxkharo.obey.threading

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers

/**
 * Default implementation to provide coroutine context to run a particular task
 */
class DefaultCoroutineContextProvider: CoroutineContextProvider {
    override val mainContext: CoroutineContext by lazy { Dispatchers.Main }
    override val ioContext: CoroutineContext by lazy { Dispatchers.Main }
}
