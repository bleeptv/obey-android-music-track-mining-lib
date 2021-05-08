package com.ygorxkharo.obey.trackmining.platform.spotify

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import com.ygorxkharo.obey.networking.errors.ServerError
import com.ygorxkharo.obey.trackmining.api.LibraryTracksHttpClient
import com.ygorxkharo.obey.trackmining.sdk.spotify.apiclient.fixtures.SpotifyTrackJSONResultTestFixture
import com.ygorxkharo.trackmining.Failure
import com.ygorxkharo.trackmining.Success
import com.ygorxkharo.trackmining.tracks.model.LibraryTrack
import org.junit.jupiter.api.Assertions.assertEquals


internal class SpotifyTracksMinerTest {

    private lateinit var sut: SpotifyTracksMiner
    private val mockLibraryTracksApiClient: LibraryTracksHttpClient = mock()
    private val queryResultsLimit = 1
    private val mockOnSuccessCallback: (List<LibraryTrack>) -> Unit = mock()
    private val mockOnErrorCallback: (Throwable) -> Unit = mock()
    private val libraryTrack = SpotifyTrackJSONResultTestFixture.buildLibraryTrack()
    private val expectedFetchedLibraryTracks = listOf(libraryTrack)

    @BeforeEach
    fun setup() {
        SpotifyTrackJSONResultTestFixture
        sut = SpotifyTracksMiner(mockLibraryTracksApiClient, queryResultsLimit)
    }

    @Test
    fun `test when tracks are mined successfully, expect onSuccess callback to be triggered with list of tracks`() {
        //Arrange
        mockLibraryTracksApiClient.stub {
            val result = Success(expectedFetchedLibraryTracks)
            on { getLibraryTracks(any(), any()) }.thenReturn(result)
        }

        //Act
        val actualResult = sut.mine()

        //Assert
        assertEquals(expectedFetchedLibraryTracks, (actualResult as Success).payload)
    }

    @Test
    fun `test when an error occurs when mining tracks, expect onError callback to be triggered with a throwable`() {
        //Arrange
        val errorThrowable = ServerError
        mockLibraryTracksApiClient.stub {
            val result = Failure(errorThrowable)
            on { getLibraryTracks(any(), any()) }.thenReturn(result)
        }

        //Act
        val actualResult = sut.mine()

        //Assert
        assertEquals(errorThrowable, (actualResult as Failure).error)
    }
}