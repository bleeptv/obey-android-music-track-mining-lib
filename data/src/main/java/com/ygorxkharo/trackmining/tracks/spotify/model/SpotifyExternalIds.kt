package com.ygorxkharo.trackmining.tracks.spotify.model

import com.squareup.moshi.Json

/**
 * Represents a JSON object containing identifiers for a track created outside Spotify
 *
 * @property isrcCode International Standard Recording Code for a track
 */
data class SpotifyExternalIds(

    @Json(name = "isrc")
    val isrcCode: String
)
