package com.ygorxkharo.trackmining.tracks.model

/**
 * Metadata on the music track's origin, length, and category
 *
 * @property songTitle Title of the music track
 * @property albumSource Album details where the song was distributed with
 * @property durationInMillis The duration of the music track in milliseconds
 * @property genres A collection of genres the music track belongs to
 */
data class TrackContent(
        val songTitle: String,
        val albumSource: AlbumSource,
        val durationInMillis: Long,
        val genres: List<String>
)
