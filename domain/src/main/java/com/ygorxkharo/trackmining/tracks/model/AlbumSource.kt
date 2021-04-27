package com.ygorxkharo.trackmining.tracks.model

/**
 * Original source of a music track when it's been released for consumption
 *
 * @property albumTitle Title of the album in which the music track was released
 * @property coverImage Reference link to the cover image of the album
 */
data class AlbumSource(
        val albumTitle: String,
        val coverImage: String
)
