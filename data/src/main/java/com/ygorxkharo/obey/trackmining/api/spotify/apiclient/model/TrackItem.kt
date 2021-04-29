package com.ygorxkharo.obey.trackmining.api.spotify.apiclient.model

import com.squareup.moshi.Json

/**
 * Represents a JSON object of music track metadata fetched from Spotify
 */
data class TrackItem(

    /**
     * @property track Spotify-related metadata on a track
     */
    @Json(name = "track")
    val track: LibraryTrack
)
