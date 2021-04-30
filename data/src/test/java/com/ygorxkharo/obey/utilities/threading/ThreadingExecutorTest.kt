package com.ygorxkharo.obey.utilities.threading

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
internal class ThreadingExecutorTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test when performing operation, expect main thread task to pass result from background thread`() {
        //Arrange
        val backgroundResultValue = "test successful"
        val fakeBackgroundThreadTask: suspend () -> String = backgroundTask@{
            return@backgroundTask backgroundResultValue
        }
        val mockMainThreadTask: (String) -> Unit = mock()
        val coroutineContextProvider = DefaultCoroutineContextProvider()

        //Act
        val backgroundJob = ThreadingExecutor.executeAsyncOperation(
            fakeBackgroundThreadTask,
            mockMainThreadTask,
            coroutineContextProvider
        )

        //Assert
        verify(mockMainThreadTask).invoke(backgroundResultValue)
        backgroundJob.cancel()
    }
}