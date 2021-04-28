package com.ygorxkharo.trackmining.tracks.spotify.model

import com.squareup.moshi.Json

data class SpotifyArtist(

    @Json(name = "name")
    val name: String
)
