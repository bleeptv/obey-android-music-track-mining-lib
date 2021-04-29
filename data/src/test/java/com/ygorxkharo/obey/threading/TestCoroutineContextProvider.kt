package com.ygorxkharo.obey.threading

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * Test implementation to provide coroutine context to run a particular task
 */
class TestCoroutineContextProvider: CoroutineContextProvider {
    override val mainContext: CoroutineContext by lazy { Dispatchers.Unconfined }
    override val ioContext: CoroutineContext by lazy { Dispatchers.Unconfined }
}