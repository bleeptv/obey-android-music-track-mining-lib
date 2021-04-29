package com.ygorxkharo.obey.trackmining.api.spotify.apiclient.model

import com.squareup.moshi.Json

/**
 * Represents a JSON object for Spotify Album information
 */
data class Album(

    /**
     * @property title The title of an album
     */
    @Json(name = "name")
    val title: String,

    /**
     * @property images A collection af Spotify album image metdata, such as height, width, and URL
     */
    @Json(name = "images")
    val images: List<AlbumImage>,

    /**
     * @property releaseDateYYYYmmDD The date in which the album was released. Formatted to "YYYY-mm-DD"
     */
    @Json(name = "release_date")
    val releaseDateYYYYmmDD: String
)
