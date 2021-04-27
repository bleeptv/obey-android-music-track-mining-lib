package com.ygorxkharo.trackmining.tracks.model

/**
 * Stock Keeping Units used to trace the music track in a music library
 *
 * @property isrc International Standard Recording code of the music track
 * @property sourcingPlatform Metadata details on the platform from which the music track was acquired
 */
data class SKUs(
        val isrc: String,
        val sourcingPlatform: SourcingPlatform
)
