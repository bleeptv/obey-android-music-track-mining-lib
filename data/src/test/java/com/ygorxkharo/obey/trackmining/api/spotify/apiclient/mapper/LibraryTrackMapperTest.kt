package com.ygorxkharo.obey.trackmining.api.spotify.apiclient.mapper

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import com.ygorxkharo.obey.trackmining.api.spotify.apiclient.model.LibraryTrackEntity
import com.ygorxkharo.obey.trackmining.sdk.spotify.apiclient.fixtures.SpotifyTrackJSONResultTestFixture
import com.ygorxkharo.trackmining.tracks.model.LibraryTrack

internal class LibraryTrackMapperTest {

    private val regionalAvailabilityList = listOf("NG","ZA")
    private val trackId = "6E1ZonB1Fosz99Rg0tUBTi"
    private val testTrackStreamingUri = "spotify:track:$trackId"
    private val albumTitle = "Stories"
    private val trackTitle = albumTitle
    private val albumArtUrl = "https://i.scdn.co/image/ab67616d0000b27309bd912a6dd8ab6faad57346"
    private val artistName = "Refs"
    private val featureArtists = listOf("DJ Mix")
    private val trackReleaseDate = "2019-06-07T00:01"
    private val trackDuration = 219475L
    private val trackISRCCode = "GBKPL1828466"
    private val libraryTrackMapper = LibraryTrackMapper()
    private lateinit var libraryTrackEntity: LibraryTrackEntity
    private lateinit var actualLibraryTrack: LibraryTrack


    @BeforeEach
    fun setup() {
        //Arrange
        libraryTrackEntity = SpotifyTrackJSONResultTestFixture.buildSpotifyTrackItem().track

        //Act
        actualLibraryTrack = libraryTrackMapper.convertToLibraryTrack(libraryTrackEntity)
    }

    @Test
    fun `test when mapping a track entity to a library track, expect a library track's content to be created`() {

        //Assert
        assertEquals(albumTitle, actualLibraryTrack.trackContent.albumSource.albumTitle)
        assertEquals(albumArtUrl, actualLibraryTrack.trackContent.albumSource.coverImageUri)
        assertEquals(trackTitle, actualLibraryTrack.trackContent.songTitle)
        assertEquals(trackDuration, actualLibraryTrack.trackContent.durationInMillis)
    }

    @Test
    fun `test when mapping a track entity to a library track, expect a library track to be contain SKU information`() {

        //Assert
        assertEquals(trackISRCCode, actualLibraryTrack.skus.isrcCode)
        assertEquals("spotify", actualLibraryTrack.skus.sourcingPlatform.sourceReferenceSystem)
        assertEquals(trackId, actualLibraryTrack.skus.sourcingPlatform.sourceReferenceId)
    }

    @Test
    fun `test when mapping a track entity to a library track, expect a library track to be contain playback attribution`() {

        //Assert
        assertEquals(testTrackStreamingUri, actualLibraryTrack.playbackAttribution.streamingUri)
        assertEquals(regionalAvailabilityList, actualLibraryTrack.playbackAttribution.licensingRestrictions.regionalAvailability)
        assertEquals("spotify", actualLibraryTrack.playbackAttribution.platform)
    }

    @Test
    fun `test when mapping a track entity to a library track, expect a library track to be contain production attribution`() {

        //Assert
        assertEquals(artistName, actualLibraryTrack.productionAttribution.leadArtist)
        assertTrue(actualLibraryTrack.productionAttribution.featuredArtists == featureArtists)
    }

    @Test
    fun `test when mapping a track entity to a library track, expect a library track to be contain publishing attribution`() {

        //Assert
        assertEquals(trackReleaseDate, actualLibraryTrack.publishingAttribution.releaseDateUTC.toString())
    }
}