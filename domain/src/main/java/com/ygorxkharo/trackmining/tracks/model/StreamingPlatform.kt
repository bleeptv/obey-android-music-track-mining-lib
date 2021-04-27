package com.ygorxkharo.trackmining.tracks.model

/**
 * Describes a streaming platform where a music track can be streamed from
 * @property platformName The name of the streaming platform
 */
enum class StreamingPlatform(val platformName: String) {
    SPOTIFY("spotify"),
    APPLE_MUSIC("apple_music");
}
