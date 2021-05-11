package com.ygorxkharo.obey.trackmining.api.spotify.apiclient

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.stub
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Call
import retrofit2.Response
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import com.ygorxkharo.obey.filesystem.TestFileUtils
import com.ygorxkharo.obey.utilities.threading.DefaultCoroutineContextProvider
import com.ygorxkharo.obey.trackmining.sdk.spotify.apiclient.fixtures.SpotifyTrackJSONResultTestFixture
import com.ygorxkharo.trackmining.common.api.client.model.Failure
import com.ygorxkharo.trackmining.common.api.client.model.Result
import com.ygorxkharo.trackmining.common.api.client.model.Success
import com.ygorxkharo.obey.trackmining.api.spotify.apiclient.mapper.LibraryTrackMapper
import com.ygorxkharo.obey.trackmining.api.spotify.apiclient.model.GetTracksFromSpotifyResponse
import com.ygorxkharo.trackmining.tracks.model.LibraryTrack

internal class SpotifyTracksClientTest {

    private lateinit var sut: SpotifyTracksClient
    private val libraryTrackMapper = LibraryTrackMapper()
    private val mockHttpAPI: HTTPApi = mock()
    private val coroutineContextProvider = DefaultCoroutineContextProvider()
    private val mockOnFetchLikedSongsCallback: (Result<List<LibraryTrack>>) -> Unit = mock()
    private val authToken = "Bearer ey.Tjlaowwwoijfaljf242sfsjaf"
    private val libraryTrackResultCount = 1
    private val httpErrorCode = 500
    private val mockFetchLikedSongsCall: Call<GetTracksFromSpotifyResponse> = mock()
    private val moshiInstance = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val likedSongsResultCaptor = argumentCaptor<Result<List<LibraryTrack>>>()

    @ExperimentalCoroutinesApi
    @BeforeEach
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        sut = SpotifyTracksClient(
            mockHttpAPI,
            coroutineContextProvider,
            libraryTrackMapper
        )

        mockFetchLikedSongsCall.stub {
            on { clone() }.thenReturn(mockFetchLikedSongsCall)
        }
    }

    @ExperimentalCoroutinesApi
    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Nested
    @DisplayName("When fetching tracks")
    inner class FetchingLibraryTracksTests {

        @BeforeEach
        fun setup() {
            whenever(mockHttpAPI.getLikedSongs(any(), any())).thenReturn(mockFetchLikedSongsCall)
        }

        private val likedSongsPayloadAdapter = moshiInstance.adapter(
            GetTracksFromSpotifyResponse::class.java
        )

        @Test
        fun `test when request is successful, expect the onComplete callback to trigger with a success result`() {
            //Arrange
            val responsePayloadFileLocation =
                SpotifyTrackJSONResultTestFixture.responsePayloadFileLocation
            val likedSongsPayloadJson =
                TestFileUtils.convertFileContentsToString(responsePayloadFileLocation)
            val responsePayloadToJson = likedSongsPayloadAdapter.fromJson(likedSongsPayloadJson)

            mockFetchLikedSongsCall.stub {
                on { execute() }.thenReturn(Response.success(responsePayloadToJson))
            }
            val expectedTotalTracksCount = 1

            //Act
            runBlocking {
                sut.getLibraryTracks(
                    authToken,
                    libraryTrackResultCount,
                    mockOnFetchLikedSongsCallback
                )
                sut.networkCallsCollection.first().join()
            }

            //Assert
            verify(mockOnFetchLikedSongsCallback).invoke(likedSongsResultCaptor.capture())
            val actualResult = likedSongsResultCaptor.firstValue
            assertTrue(actualResult is Success)
            assertEquals(
                expectedTotalTracksCount,
                (actualResult as Success<List<LibraryTrack>>).payload.size
            )
        }

        @Test
        fun `test when the request fails, expect the onComplete callback to trigger with a Failure result`() {
            //Arrange
            val errorBody = """
                    {
                        "message": "Server error"
                    }
                """.toResponseBody()

            mockFetchLikedSongsCall.stub {
                on { execute() }.thenReturn(Response.error(httpErrorCode, errorBody))
            }

            //Act
            runBlocking {
                sut.getLibraryTracks(
                    authToken,
                    libraryTrackResultCount,
                    mockOnFetchLikedSongsCallback
                )
                sut.networkCallsCollection.first().join()
            }

            //Assert
            verify(mockOnFetchLikedSongsCallback).invoke(likedSongsResultCaptor.capture())
            val actualResult = likedSongsResultCaptor.firstValue
            assertTrue(actualResult is Failure)
        }
    }

    @Nested
    @DisplayName("When cancelling all requests")
    inner class CancelledRequestsTests {

        @Test
        fun `test when all requests are cancelled, expect network calls collection to be empty`() {
            //Arrange: Simulate failing request
            val errorResponseString = "".toResponseBody()
            mockFetchLikedSongsCall.stub {
                on { execute() }.thenReturn(Response.error(httpErrorCode, errorResponseString))
            }

            sut.getLibraryTracks(authToken, libraryTrackResultCount, mockOnFetchLikedSongsCallback)

            //Act
            sut.cancelAllRequests()

            //Assert
            assertTrue(sut.networkCallsCollection.isEmpty())
        }
    }
}