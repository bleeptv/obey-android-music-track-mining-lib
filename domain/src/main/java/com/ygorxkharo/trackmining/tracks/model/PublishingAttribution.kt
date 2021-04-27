package com.ygorxkharo.trackmining.tracks.model

import java.util.Date

/**
 * Describes the parties involved in the distribution of the music track
 *
 * @property publisher Name of the record label/publisher of a music track
 * @property releaseDate The date which the music track was released
 */
data class PublishingAttribution(
        val publisher: String,
        val releaseDate: Date
)
