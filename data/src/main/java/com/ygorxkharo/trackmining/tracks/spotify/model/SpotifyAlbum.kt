package com.ygorxkharo.trackmining.tracks.spotify.model

import com.squareup.moshi.Json

/**
 * Represents a JSON object for Spotify Album information
 *
 * @property albumTitle The title of an album
 * @property albumImages A collection af Spotify album image metdata, such as height, width, and URL
 * @property releaseDate The date in which the album was released. Formatted to "YYYY-mm-DD"
 */
data class SpotifyAlbum(

    @Json(name = "name")
    val albumTitle: String,

    @Json(name = "images")
    val albumImages: List<SpotifyAlbumImage>,

    @Json(name = "release_date")
    val releaseDate: String
)
