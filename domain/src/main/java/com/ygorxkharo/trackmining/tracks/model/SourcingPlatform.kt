package com.ygorxkharo.trackmining.tracks.model

/**
 * Describes a source platform where a music track is streamed from
 *
 * @property sourceReferenceSystem An enumerator to describe from which platform the music track is
 * acquired from, i.e. Spotify or Apple Music
 * @property sourceReferenceId The identifier to a music track on a specific platform
 */
data class SourcingPlatform(
        val sourceReferenceSystem: String,
        val sourceReferenceId: String
)
