package com.ygorxkharo.obey.threading

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test

internal class ThreadingExecutorTest {

    @Test
    fun `test when performing operation, expect main thread task to pass result from background thread`() {
        //Arrange
        val backgroundResultValue = "test successful"
        val fakeBackgroundThreadTask: suspend () -> String = backgroundTask@{
            return@backgroundTask backgroundResultValue
        }
        val mockMainThreadTask: (String) -> Unit = mock()
        val coroutineContextProvider = TestCoroutineContextProvider()

        //Act
        ThreadingExecutor.executeAsyncOperation(
            fakeBackgroundThreadTask,
            mockMainThreadTask,
            coroutineContextProvider
        )

        //Assert
        verify(mockMainThreadTask).invoke(backgroundResultValue)
    }
}