package com.ygorxkharo.obey.trackmining

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.ygorxkharo.obey.trackmining.exceptions.LibraryTracksMiningException
import com.ygorxkharo.obey.trackmining.platform.MusicTracksSource
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import com.ygorxkharo.trackmining.platform.MusicLibraryTracksMiner
import com.ygorxkharo.trackmining.tracks.model.LibraryTrack
import org.junit.jupiter.api.assertThrows

internal class DefaultLibraryTracksProviderTest {

    private val mockLibraryTracksMiner: MusicLibraryTracksMiner = mock()
    private val mockLibraryTracksMinerCollection: Map<MusicTracksSource, MusicLibraryTracksMiner> = mock()
    private lateinit var sut: DefaultLibraryTracksProvider
    private val mockOnMiningSuccessCallback: (List<LibraryTrack>) -> Unit = mock()
    private val mockOnMiningErrorCallback: (Throwable) -> Unit = mock()
    private lateinit var chosenPlatformName: String

    @BeforeEach
    fun setup() {
        sut = DefaultLibraryTracksProvider(mockLibraryTracksMinerCollection)
    }

    @Test
    fun `test when library tracks miner is available, expect library track miner to set onMiningSuccess and onMiningError callbacks`() {
        //Arrange
        chosenPlatformName = "spotify"
        val validLibraryTrackMinerRequest = LibraryTrackMiningRequest(chosenPlatformName = chosenPlatformName)
        whenever(mockLibraryTracksMinerCollection[any()]).thenReturn(mockLibraryTracksMiner)

        //Act
        sut.mineFromPlatform(validLibraryTrackMinerRequest, mockOnMiningSuccessCallback, mockOnMiningErrorCallback)

        //Assert
        verify(mockLibraryTracksMiner).mine(mockOnMiningSuccessCallback, mockOnMiningErrorCallback)
        verifyNoMoreInteractions(mockLibraryTracksMiner)
    }

    @Test
    fun `test when music track source type does not exist, expect an exception for a missing music track source to be thrown`() {
        //Arrange
        chosenPlatformName = "test_library_track_miner_key"
        val invalidLibraryTrackMinerRequest = LibraryTrackMiningRequest(chosenPlatformName = chosenPlatformName)
        val expectedErrorMessage = "Music track source type doesn't exist for this platform name"

        //Act
        val expectedException = assertThrows<LibraryTracksMiningException> {
            sut.mineFromPlatform(
                invalidLibraryTrackMinerRequest,
                mockOnMiningSuccessCallback,
                mockOnMiningErrorCallback
            )
        }

        //Assert
        assertEquals(expectedErrorMessage, expectedException.message)
    }

    @Test
    fun `test when library tracks miner does not exist, expect an exception for a missing library track miner to be thrown`() {
        //Arrange
        chosenPlatformName = "spotify"
        val invalidLibraryTrackMinerRequest = LibraryTrackMiningRequest(chosenPlatformName = chosenPlatformName)
        val expectedErrorMessage = "Library track miner for this key doesn't exist"

        //Act
        val expectedException = assertThrows<LibraryTracksMiningException> {
            sut.mineFromPlatform(
                invalidLibraryTrackMinerRequest,
                mockOnMiningSuccessCallback,
                mockOnMiningErrorCallback
            )
        }

        //Assert
        assertEquals(expectedErrorMessage, expectedException.message)
    }
}