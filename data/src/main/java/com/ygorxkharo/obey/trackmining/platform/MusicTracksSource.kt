package com.ygorxkharo.obey.trackmining.platform

/**
 * Describes a streaming platform in use by Obey to mine music tracks
 *
 * @property sourceName The name of the music platform
 */
enum class MusicTracksSource(val sourceName: String) {
    SPOTIFY("spotify"),
    APPLE_MUSIC("apple_music");

    companion object {
        private val map = values().associateBy(MusicTracksSource::sourceName)

        /**
         * Performs a reverse lookup of a streaming platform based on it's name
         *
         * @param sourceName The platform name for which a type must be returned
         * @return the streaming platform type based on the platform name provided
         */
        fun getType(sourceName: String) = map[sourceName]
    }
}
