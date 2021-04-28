package com.ygorxkharo.trackmining.tracks.spotify.model

import com.squareup.moshi.Json

data class SpotifyAlbumImage(

    @Json(name = "height")
    val height: Int,

    @Json(name = "width")
    val width: Int,

    @Json(name = "url")
    val imageUrl: String,
)
