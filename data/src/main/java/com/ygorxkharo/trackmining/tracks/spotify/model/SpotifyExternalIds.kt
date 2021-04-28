package com.ygorxkharo.trackmining.tracks.spotify.model

import com.squareup.moshi.Json

data class SpotifyExternalIds(

    @Json(name = "isrc")
    val isrcCode: String
)
