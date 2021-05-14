package com.ygorxkharo.obey.trackmining

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import com.ygorxkharo.trackmining.platform.MusicLibraryTracksMiner
import com.ygorxkharo.trackmining.exceptions.LibraryTracksMiningException
import com.ygorxkharo.trackmining.LibraryTrackMiningRequest

internal class DefaultLibraryTracksRepositoryTest {

    private val mockLibraryTracksMiner: MusicLibraryTracksMiner = mock()
    private val mockLibraryTracksMinerCollection: Map<String, MusicLibraryTracksMiner> = mock()
    private lateinit var sut: DefaultLibraryTracksRepository
    private lateinit var chosenPlatformName: String

    @BeforeEach
    fun setup() {
        sut = DefaultLibraryTracksRepository(mockLibraryTracksMinerCollection)
    }

    @Test
    fun `test when library tracks miner is available, expect library track miner to set onMiningSuccess and onMiningError callbacks`() {
        //Arrange
        chosenPlatformName = "spotify"
        val validLibraryTrackMinerRequest = LibraryTrackMiningRequest(chosenPlatformName = chosenPlatformName)
        whenever(mockLibraryTracksMinerCollection[any()]).thenReturn(mockLibraryTracksMiner)

        //Act
        sut.getFromPlatform(validLibraryTrackMinerRequest)

        //Assert
        verify(mockLibraryTracksMiner).mine()
        verifyNoMoreInteractions(mockLibraryTracksMiner)
    }

    @Test
    fun `test when library tracks miner does not exist, expect an exception for a missing library track miner to be thrown`() {
        //Arrange
        chosenPlatformName = "spotify"
        val invalidLibraryTrackMinerRequest = LibraryTrackMiningRequest(chosenPlatformName = chosenPlatformName)
        val expectedErrorMessage = "Library track miner for this key doesn't exist"

        //Act
        val expectedException = assertThrows<LibraryTracksMiningException> {
            sut.getFromPlatform(invalidLibraryTrackMinerRequest)
        }

        //Assert
        assertEquals(expectedErrorMessage, expectedException.message)
    }
}