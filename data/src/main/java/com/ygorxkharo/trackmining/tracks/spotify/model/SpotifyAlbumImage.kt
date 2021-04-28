package com.ygorxkharo.trackmining.tracks.spotify.model

import com.squareup.moshi.Json

/**
 *
 * @property height The height dimension of an image
 * @property width The width dimension of an image
 * @property imageUrl The web URL leading to the image
 */
data class SpotifyAlbumImage(

    @Json(name = "height")
    val height: Int,

    @Json(name = "width")
    val width: Int,

    @Json(name = "url")
    val imageUrl: String,
)
