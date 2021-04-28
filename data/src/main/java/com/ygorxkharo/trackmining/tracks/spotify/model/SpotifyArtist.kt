package com.ygorxkharo.trackmining.tracks.spotify.model

import com.squareup.moshi.Json

/**
 * Represents a JSON object to describe an artist on a Spotify track
 *
 * @property name The name of a performing artist on the Spotify platform
 */
data class SpotifyArtist(

    @Json(name = "name")
    val name: String
)
