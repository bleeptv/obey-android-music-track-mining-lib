package com.ygorxkharo.trackmining.tracks.model

/**
 * Describes a source platform where a music track is streamed from
 *
 * @property sourceReferenceSystem An enumerator to describe from which platform the music track is
 * acquired from, i.e. [StreamingPlatform.SPOTIFY] or [StreamingPlatform.APPLE_MUSIC]
 * @property sourceReferenceId The identifier to a music track on a specific platform
 */
data class SourcingPlatform(
        val sourceReferenceSystem: StreamingPlatform,
        val sourceReferenceId: String
)
