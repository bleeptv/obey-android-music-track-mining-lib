package com.ygorxkharo.obey.trackmining.api.spotify.apiclient.model

import com.squareup.moshi.Json

/**
 * Represents an album image JSON Object on Spotify
 */
data class AlbumImage(

    /**
     * @property height The height dimension of an image
     */
    @Json(name = "height")
    val height: Int,

    /**
     * @property width The width dimension of an image
     */
    @Json(name = "width")
    val width: Int,

    /**
     * @property imageUrl The web URL leading to the image
     */
    @Json(name = "url")
    val imageUrl: String,
)
