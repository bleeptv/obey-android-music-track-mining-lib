package com.ygorxkharo.obey.networking.client.retrofit

import com.nhaarman.mockitokotlin2.any

import retrofit2.Call
import retrofit2.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException
import java.lang.IllegalStateException
import java.net.ConnectException
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import com.ygorxkharo.obey.networking.errors.AuthenticationError
import com.ygorxkharo.obey.networking.errors.NetworkError
import com.ygorxkharo.obey.networking.errors.ServerError
import com.ygorxkharo.trackmining.Failure
import com.ygorxkharo.trackmining.Success

internal class NetworkCallExtensionsKtTest {

    private val mockNetworkCall: Call<FakeNetworkCallObject> = mock()
    private val processResponseCallback: (FakeNetworkCallObject) -> String = mock()

    private fun stubNetworkCallCloning() {
        mockNetworkCall.stub {
            on { clone() }.thenReturn(mockNetworkCall)
        }
    }

    @Nested
    @DisplayName("Given a network request is successful")
    inner class SuccessfulNetworkRequestTests {

        @BeforeEach
        fun setup(){
            stubNetworkCallCloning()
        }

        @Test
        fun `test when response is received with a body payload, expect the processResponse callback to be triggered`() {
            //Arrange
            mockNetworkCall.stub {
                on { execute() }.thenReturn(Response.success(FakeNetworkCallObject()))
            }

            val expectedEndResultValue = "test successful"
            val processResponse: (FakeNetworkCallObject) -> String = mock()
            whenever(processResponse.invoke(any())).thenReturn(expectedEndResultValue)

            //Act
            val actualResult = mockNetworkCall.getResult(processResponse)

            //Assert
            assertTrue(actualResult is Success)
            val result = actualResult as Success
            assertEquals(expectedEndResultValue, result.payload)
        }
    }

    @Nested
    @DisplayName("Given a network request fails")
    inner class FailedNetworkRequestTests {

        private val generalErrorResponse = """
                {
                    "message": "Error contacting server"
                }
            """.toResponseBody()

        @BeforeEach
        fun setup(){
            stubNetworkCallCloning()
        }

        @Test
        fun `test when request failure is server related, expect the result payload to be a ServerError`() {
            //Arrange
            val errorCode = 500

            mockNetworkCall.stub {
                on { execute() }.thenReturn(Response.error(errorCode, generalErrorResponse))
            }

            //Act
            val actualResult = mockNetworkCall.getResult(processResponseCallback)

            //Assert
            assertTrue(actualResult is Failure)
            val result = actualResult as Failure
            assertTrue(result.error is ServerError)
        }

        @Test
        fun `test when request failure is related to authentication, expect the result payload to be a AuthenticationError`() {
            //Arrange
            val errorCode = 401
            mockNetworkCall.stub {
                on { execute() }.thenReturn(Response.error(errorCode, generalErrorResponse))
            }

            //Act
            val actualResult = mockNetworkCall.getResult(processResponseCallback)

            //Assert
            assertTrue(actualResult is Failure)
            val result = actualResult as Failure
            assertTrue(result.error is AuthenticationError)
        }

        @Test
        fun `test when request failure is due to connect exception, expect the result payload to be a NetworkError`() {
            //Arrange
            mockNetworkCall.stub {
                on { execute() }.thenThrow(ConnectException())
            }

            //Act
            val actualResult = mockNetworkCall.getResult(processResponseCallback)

            //Assert
            assertTrue(actualResult is Failure)
            val result = actualResult as Failure
            assertTrue(result.error is NetworkError)
        }

        @Test
        fun `test when request failure is due to network operation IO, expect the result payload to be a NetworkError`() {
            //Arrange
            mockNetworkCall.stub {
                on { execute() }.thenThrow(IOException())
            }

            //Act
            val actualResult = mockNetworkCall.getResult(processResponseCallback)

            //Assert
            assertTrue(actualResult is Failure)
            val result = actualResult as Failure
            assertTrue(result.error is NetworkError)
        }
    }

    @Nested
    @DisplayName("Given a request is sent successfully by fails to return a valid response")
    inner class UnkwownErrorTests {

        @BeforeEach
        fun setup(){
            stubNetworkCallCloning()
        }

        @Test
        fun `test when a request returns response contains no body, expect the result payload message to be unknown error`() {
            //Arrange
            val errorMessage = "Response has no body"
            mockNetworkCall.stub {
                on { execute() }.thenReturn(Response.success(null))
            }

            //Act
            val actualResult = mockNetworkCall.getResult(processResponseCallback)

            //Assert
            assertTrue(actualResult is Failure)
            val result = actualResult as Failure
            assertTrue(result.error.message == errorMessage)
        }

        @Test
        fun `test when a request throws an exception, expect the result payload message to be the same as the original error`() {
            //Arrange
            val errorMessage = "Illegal State"
            mockNetworkCall.stub {
                on { execute() }.thenThrow(IllegalStateException(errorMessage))
            }

            //Act
            val actualResult = mockNetworkCall.getResult(processResponseCallback)

            //Assert
            assertTrue(actualResult is Failure)
            val result = actualResult as Failure
            println(result.error.message)
            assertTrue(result.error.message == errorMessage)
        }
    }

    inner class FakeNetworkCallObject
}