package com.ygorxkharo.obey.trackmining.api.spotify.apiclient.mapper

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import com.ygorxkharo.obey.trackmining.api.spotify.apiclient.model.LibraryTrackEntity
import com.ygorxkharo.trackmining.tracks.model.AlbumSource
import com.ygorxkharo.trackmining.tracks.model.LibraryTrack
import com.ygorxkharo.trackmining.tracks.model.LicensingRestrictions
import com.ygorxkharo.trackmining.tracks.model.PlaybackAttribution
import com.ygorxkharo.trackmining.tracks.model.ProductionAttribution
import com.ygorxkharo.trackmining.tracks.model.PublishingAttribution
import com.ygorxkharo.trackmining.tracks.model.SKUs
import com.ygorxkharo.trackmining.tracks.model.SourcingPlatform
import com.ygorxkharo.trackmining.tracks.model.TrackContent

/**
 * Used to convert a Spotify Library track into an Obey Library Track
 *
 */
class LibraryTrackMapper {

    private val platformName = "spotify"
    private val utcDateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    private val utcStringFormat = "%s%s"

    /**
     * This time value was chosen due to a standard in the music industry to release albums by
     * 00:01 UTC: https://en.wikipedia.org/wiki/Global_Release_Day#:~:text=Music%20releases%20are%20now%20uniformly,and%20singles%20would%20become%20available.
     * As spotify doesn't offer dates in UTC format, the library track's datetime has to be re-constructed
     */
    private val utcTimeTemplate = "T00:01:00.000+0000"
    private val utcDateFormatter = DateTimeFormatter.ofPattern(utcDateFormat)

    /**
     * Convert a Spotify library track entity object to a [LibraryTrack] domain object
     *
     * @param trackEntity Spotify track object to convert to domain model
     * @return a domain model representing a library track
     */
    fun convertToLibraryTrack(trackEntity: LibraryTrackEntity): LibraryTrack {
        val trackContent = convertToTrackContents(trackEntity)
        val playbackAttribution = convertToPlaybackAttribution(trackEntity)
        val productionAttribution = convertToProductionAttribution(trackEntity)
        val publishingAttribution = convertToPublishingAttribution(trackEntity)
        val skus = convertToSKUs(trackEntity)

        return LibraryTrack(
            trackContent = trackContent,
            playbackAttribution = playbackAttribution,
            productionAttribution = productionAttribution,
            publishingAttribution = publishingAttribution,
            skus = skus
        )
    }

    /**
     * Extract the Stock Keeping Units (SkUs) for the library track entity object
     *
     * @param trackEntity Contains the SKU data
     * @return an stock keeping units object containing platform and track recording information
     */
    private fun convertToSKUs(trackEntity: LibraryTrackEntity): SKUs {
        return SKUs(
            isrcCode = trackEntity.externalIds.isrcCode,
            sourcingPlatform = SourcingPlatform(
                sourceReferenceSystem = platformName,
                sourceReferenceId = trackEntity.trackId
            )
        )
    }

    /**
     * Extract track publishing details from a library track entity
     *
     * @param trackEntity Contains publishing attribution details to extract
     * @return an object containing a release date and publisher/record label information
     */
    private fun convertToPublishingAttribution(trackEntity: LibraryTrackEntity): PublishingAttribution {
        val albumReleaseDate = trackEntity.albumContents.releaseDateYYYYmmDD
        return PublishingAttribution(
            publisherName = "", // Currently missing from response payload. Will be handled in Jira story B2MVE-1150
            releaseDateUTC = LocalDateTime.parse(
                    String.format(utcStringFormat, albumReleaseDate, utcTimeTemplate),
                    utcDateFormatter
            )
        )
    }

    /**
     * Extract all the parties involved in the production of the track, based on performance
     *
     * @param trackEntity Contains performing artists information to extract. It is assumed that the
     * first artist on the list is the lead artist
     * @return an object containing the lead artist, as well as a collection of featured artists
     */
    private fun convertToProductionAttribution(trackEntity: LibraryTrackEntity): ProductionAttribution {
        val leadArtist = trackEntity.artists.first()
        return ProductionAttribution(
            leadArtist = leadArtist.name,
            featuredArtists = trackEntity.artists
                .filter { artist -> artist.name != leadArtist.name }
                .map { artist -> artist.name }
        )
    }

    /**
     * Extract information on track playback/streaming
     *
     * @param trackEntity contains track streaming details
     * @return an object containing streaming platform name, streaming URL, and regional availability
     */
    private fun convertToPlaybackAttribution(trackEntity: LibraryTrackEntity): PlaybackAttribution {
        return PlaybackAttribution(
            platform = platformName,
            streamingUri = trackEntity.streamingUri,
            licensingRestrictions = LicensingRestrictions(
                regionalAvailability = trackEntity.regionalAvailability
            )
        )
    }

    /**
     * Extract Spotify track details pertaining to album information and track duration
     *
     * @param trackEntity contains track  to extract
     * @return an object containing the track title, originating album, duration, and genres
     */
    private fun convertToTrackContents(
        trackEntity: LibraryTrackEntity,
    ): TrackContent {
        return TrackContent(
            songTitle = trackEntity.title,
            albumSource = AlbumSource(
                albumTitle = trackEntity.albumContents.title,
                coverImageUri = trackEntity.albumContents.images.first().imageUrl
            ),
            durationInMillis = trackEntity.durationInMillis,
            genres = listOf() // Currently missing from response payload. Will be handled in Jira story B2MVE-1150
        )
    }
}
