package com.ygorxkharo.trackmining.usecases

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.ygorxkharo.trackmining.LibraryTrackMiningRequest
import com.ygorxkharo.trackmining.LibraryTracksProvider
import org.junit.Test

internal class GetLibraryTracksUseCaseTest {

    private val mockLibraryTracksProvider: LibraryTracksProvider<String> = mock()

    @Test
    fun `test when getting library tracks, expect the library tracks provider to be called with a mining request`() {
        //Arrange
        val sut = GetLibraryTracksUseCase(mockLibraryTracksProvider)
        val chosenPlatformName = "spotify"
        val libraryTrackMiningRequest = LibraryTrackMiningRequest(chosenPlatformName)

        //Act
        sut(libraryTrackMiningRequest)

        //Assert
        verify(mockLibraryTracksProvider).provideFromPlatform(libraryTrackMiningRequest)
    }
}