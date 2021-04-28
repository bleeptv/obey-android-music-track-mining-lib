package com.ygorxkharo.trackmining.tracks.spotify.model

import com.squareup.moshi.Json

/**
 * Represents a JSON object for a Spotify track resource
 *
 * @property trackTitle The name of the track
 * @property albumContents The details on the album the track belongs to
 * @property artists Collection of artists who performed on the track.
 * @property regionalAvailability A collection of country codes where the track is available for streaming
 * @property durationInMillis The length of track while streaming it in milliseconds
 * @property externalIds External identifiers for a track created outside Spotify
 * @property trackId Track identifier on the Spotify platform
 * @property streamingUri The link used to stream the track from Spotify
 */
data class SpotifyLibraryTrack(

    @Json(name = "name")
    val trackTitle: String,

    @Json(name = "album")
    val albumContents: SpotifyAlbum,

    @Json(name = "artists")
    val artists: List<SpotifyArtist>,

    @Json(name = "available_markets")
    val regionalAvailability: List<String>,

    @Json(name = "duration_ms")
    val durationInMillis: Long,

    @Json(name = "external_ids")
    val externalIds: SpotifyExternalIds,

    @Json(name = "id")
    val trackId: String,

    @Json(name = "uri")
    val streamingUri: String
)


