package com.ygorxkharo.trackmining.tracks.spotify.model

import com.squareup.moshi.Json

data class SpotifyAlbum(

    @Json(name = "name")
    val albumTitle: String,

    @Json(name = "images")
    val albumImages: List<SpotifyAlbumImage>,

    @Json(name = "release_date")
    val releaseDate: String
)
