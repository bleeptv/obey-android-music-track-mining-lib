package com.ygorxkharo.trackmining.usecases

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime
import com.ygorxkharo.trackmining.LibraryTrackMiningRequest
import com.ygorxkharo.trackmining.LibraryTracksRepository
import com.ygorxkharo.trackmining.common.api.client.model.Failure
import com.ygorxkharo.trackmining.common.api.client.model.Success
import com.ygorxkharo.trackmining.tracks.model.AlbumSource
import com.ygorxkharo.trackmining.tracks.model.LibraryTrack
import com.ygorxkharo.trackmining.tracks.model.LicensingRestrictions
import com.ygorxkharo.trackmining.tracks.model.PlaybackAttribution
import com.ygorxkharo.trackmining.tracks.model.ProductionAttribution
import com.ygorxkharo.trackmining.tracks.model.PublishingAttribution
import com.ygorxkharo.trackmining.tracks.model.SKUs
import com.ygorxkharo.trackmining.tracks.model.SourcingPlatform
import com.ygorxkharo.trackmining.tracks.model.TrackContent

internal class GetLibraryTracksUseCaseTest {

    private val mockLibraryTracksRepository: LibraryTracksRepository<String> = mock()
    private lateinit var sut: GetLibraryTracksUseCase
    private val chosenPlatformName = "spotify"
    private val libraryTrackMiningRequest = LibraryTrackMiningRequest(chosenPlatformName)

    @Before
    fun setup() {
        sut = GetLibraryTracksUseCase(mockLibraryTracksRepository)
    }

    @Test
    fun `test when result is successful, expect the result to contain a collection of library tracks`() {
        //Arrange
        val libraryTrack = buildLibraryTrack()
        val expectedLibraryTracks = listOf(libraryTrack)
        whenever(mockLibraryTracksRepository.getLibraryTracks(any())).thenReturn(Success(expectedLibraryTracks))

        //Act
        val actualResult = sut(libraryTrackMiningRequest)

        //Assert
        assertTrue(actualResult is Success)
        assertEquals(expectedLibraryTracks, (actualResult as Success).payload)
    }

    @Test
    fun `test when result is a failure, expect the result to contain a throwable`() {
        //Arrange
        val expectedThrowable = Throwable("Error with network")
        whenever(mockLibraryTracksRepository.getLibraryTracks(any())).thenReturn(Failure(expectedThrowable))

        //Act
        val actualResult = sut(libraryTrackMiningRequest)

        //Assert
        assertTrue(actualResult is Failure)
        assertEquals(expectedThrowable, (actualResult as Failure).error)
    }

    private fun buildLibraryTrack(): LibraryTrack {

        val albumTitle = "Test Song"
        val musicPlatform = "spotify"
        val albumSource = AlbumSource(
            albumTitle = albumTitle,
            coverImageUri = albumTitle
        )

        val trackContent = TrackContent(
            songTitle = "test",
            albumSource = albumSource,
            durationInMillis = 0L,
            genres = listOf()
        )

        val playbackAttribution = PlaybackAttribution(
            platform = musicPlatform,
            streamingUri = "",
            licensingRestrictions = LicensingRestrictions(
                regionalAvailability = listOf("US", "UK")
            )
        )

        val productionAttribution = ProductionAttribution(
            leadArtist = "Test",
            featuredArtists = listOf("Test 1", "Test 2")
        )

        val publishingAttribution = PublishingAttribution(
            publisherName = "Test Records",
            releaseDateUTC = LocalDateTime.now()
        )

        val skus = SKUs(
            isrcCode = "test_code",
            sourcingPlatform = SourcingPlatform(
                sourceReferenceId = "test_id",
                sourceReferenceSystem = musicPlatform
            )
        )

        return LibraryTrack(
            trackContent = trackContent,
            playbackAttribution = playbackAttribution,
            productionAttribution = productionAttribution,
            publishingAttribution = publishingAttribution,
            skus = skus
        )
    }
}