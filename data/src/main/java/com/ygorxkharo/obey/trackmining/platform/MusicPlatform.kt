package com.ygorxkharo.obey.trackmining.platform

/**
 * Describes a streaming platform in use by Obey to mine music tracks
 *
 * @property platformName The name of the streaming platform
 */
enum class MusicPlatform(val platformName: String) {
    SPOTIFY("spotify"),
    APPLE_MUSIC("apple_music");

    companion object {
        private val map = values().associateBy(MusicPlatform::platformName)

        /**
         * Performs a reverse lookup of a streaming platform based on it's name
         *
         * @param platformName The platform name for which a type must be returned
         * @return the streaming platform type based on the platform name provided
         */
        fun getType(platformName: String) = map[platformName]
    }
}