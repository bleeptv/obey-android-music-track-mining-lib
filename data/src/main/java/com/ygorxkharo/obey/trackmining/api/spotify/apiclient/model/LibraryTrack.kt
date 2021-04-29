package com.ygorxkharo.obey.trackmining.api.spotify.apiclient.model

import com.squareup.moshi.Json

/**
 * Represents a JSON object for a Spotify track resource
 */
data class LibraryTrack(

    /**
     * @property title The name of the track
     */
    @Json(name = "name")
    val title: String,

    /**
     * @property albumContents The details on the album the track belongs to
     */
    @Json(name = "album")
    val albumContents: Album,

    /**
     * @property artists Collection of artists who performed on the track.
     */
    @Json(name = "artists")
    val artists: List<Artist>,

    /**
     * @property regionalAvailability A collection of country codes where the track is available for streaming
     */
    @Json(name = "available_markets")
    val regionalAvailability: List<String>,

    /**
     * @property durationInMillis The length of track while streaming it in milliseconds
     */
    @Json(name = "duration_ms")
    val durationInMillis: Long,

    /**
     * @property externalIds External identifiers for a track created outside Spotify
     */
    @Json(name = "external_ids")
    val externalIds: ExternalIds,

    /**
     * @property trackId Track identifier on the Spotify platform
     */
    @Json(name = "id")
    val trackId: String,

    /**
     * @property streamingUri The link used to stream the track from Spotify
     */
    @Json(name = "uri")
    val streamingUri: String
)


