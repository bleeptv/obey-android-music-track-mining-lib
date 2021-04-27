package com.ygorxkharo.trackmining.tracks.model

/**
 * Stock Keeping Units (SKUs) used to trace the music track in a music library
 *
 * @property isrcCode International Standard Recording Code (ISRC) of the music track
 * @property sourcingPlatform Metadata details on the platform from which the music track was acquired
 */
data class SKUs(
        val isrcCode: String,
        val sourcingPlatform: SourcingPlatform
)
