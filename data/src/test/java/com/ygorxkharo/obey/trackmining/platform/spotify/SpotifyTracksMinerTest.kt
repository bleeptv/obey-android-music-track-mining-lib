package com.ygorxkharo.obey.trackmining.platform.spotify

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import com.ygorxkharo.obey.common.Failure
import com.ygorxkharo.obey.common.Result
import com.ygorxkharo.obey.common.Success
import com.ygorxkharo.obey.networking.errors.ServerError
import com.ygorxkharo.obey.trackmining.api.LibraryTracksHttpClient
import com.ygorxkharo.obey.trackmining.sdk.spotify.apiclient.fixtures.SpotifyTrackJSONResultTestFixture
import com.ygorxkharo.trackmining.tracks.model.LibraryTrack


internal class SpotifyTracksMinerTest {

    private lateinit var sut: SpotifyTracksMiner
    private val mockTracksClient: LibraryTracksHttpClient = mock()
    private val resultLimit = 1
    private val mockOnSuccessCallback: (List<LibraryTrack>) -> Unit = mock()
    private val mockOnErrorCallback: (Throwable) -> Unit = mock()
    private val libraryTrack = SpotifyTrackJSONResultTestFixture.buildLibraryTrack()
    private val fetchedLibraryTracks = listOf(libraryTrack)

    @BeforeEach
    fun setup() {
        SpotifyTrackJSONResultTestFixture
        sut = SpotifyTracksMiner(mockTracksClient, resultLimit)
    }

    @Test
    fun `test when tracks are mined successfully, expect onSuccess callback to be triggered with list of tracks`() {
        //Arrange
        mockTracksClient.stub {
            on { getLibraryTracks(any(), any(), any()) }.thenAnswer { invocation ->
                val onComplete = invocation.arguments[2] as (Result<List<LibraryTrack>>) -> Unit
                val result = Success(fetchedLibraryTracks)
                onComplete(result)
            }
        }

        //Act
        sut.mine(mockOnSuccessCallback, mockOnErrorCallback)

        //Assert
        verify(mockOnSuccessCallback).invoke(fetchedLibraryTracks)
        verifyNoMoreInteractions(mockOnErrorCallback)
    }

    @Test
    fun `test when an error occurs when mining tracks, expect onError callback to be triggered with a throwable`() {
        //Arrange
        val errorThrowable = ServerError
        mockTracksClient.stub {
            on { getLibraryTracks(any(), any(), any()) }.thenAnswer { invocation ->
                val onComplete = invocation.arguments[2] as (Result<List<LibraryTrack>>) -> Unit
                val result = Failure(errorThrowable)
                onComplete(result)
            }
        }

        //Act
        sut.mine(mockOnSuccessCallback, mockOnErrorCallback)

        //Assert
        verify(mockOnErrorCallback).invoke(errorThrowable)
        verifyNoMoreInteractions(mockOnSuccessCallback)
    }
}