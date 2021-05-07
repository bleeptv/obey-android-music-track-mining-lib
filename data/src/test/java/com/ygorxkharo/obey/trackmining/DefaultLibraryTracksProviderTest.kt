package com.ygorxkharo.obey.trackmining

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import com.ygorxkharo.trackmining.platform.MusicLibraryTracksMiner
import com.ygorxkharo.trackmining.tracks.model.LibraryTrack

internal class DefaultLibraryTracksProviderTest {

    private val mockLibraryTracksMiner: MusicLibraryTracksMiner = mock()
    private val mockLibraryTracksMinerCollection: Map<String, MusicLibraryTracksMiner> = mock()
    private lateinit var sut: DefaultLibraryTracksProvider
    private val mockOnMiningSuccessCallback: (List<LibraryTrack>) -> Unit = mock()
    private val mockOnMiningErrorCallback: (Throwable) -> Unit = mock()
    private val musicPlatformMinerKey = "test_library_track_miner_key"

    @BeforeEach
    fun setup() {
        sut = DefaultLibraryTracksProvider(mockLibraryTracksMinerCollection)
    }

    @Test
    fun `test when library tracks miner is available, expect library track miner to set onMiningSuccess and onMiningError callbacks`() {
        //Arrange
        whenever(mockLibraryTracksMinerCollection[any()]).thenReturn(mockLibraryTracksMiner)

        //Act
        sut.mineFromPlatform(musicPlatformMinerKey, mockOnMiningSuccessCallback, mockOnMiningErrorCallback)

        //Assert
        verify(mockLibraryTracksMiner).mine(mockOnMiningSuccessCallback, mockOnMiningErrorCallback)
        verifyNoMoreInteractions(mockLibraryTracksMiner)
    }

    @Test
    fun `test when library tracks miner does not exist, expect onMiningError to be triggered with throwable`() {
        //Arrange
        //whenever(mockLibraryTracksMinerCollection[any()]).thenReturn(mockLibraryTracksMiner)
        val expectedErrorMessage = "Music platform miner for this key doesn't exist"

        //Act
        sut.mineFromPlatform(musicPlatformMinerKey, mockOnMiningSuccessCallback, mockOnMiningErrorCallback)

        //Assert
        val throwableCaptor = argumentCaptor<Throwable>()
        verify(mockOnMiningErrorCallback).invoke(throwableCaptor.capture())
        assertEquals(expectedErrorMessage, throwableCaptor.firstValue.message)
    }
}