package com.ygorxkharo.trackmining.tracks.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

/**
 * Describes the parties involved in the distribution of the music track
 *
 * @property publisherName Name of the record label/publisher of a music track
 * @property releaseDateUTC The date which the music track was released
 */
data class PublishingAttribution(
        val publisherName: String,
        val releaseDateUTC: LocalDateTime
)
