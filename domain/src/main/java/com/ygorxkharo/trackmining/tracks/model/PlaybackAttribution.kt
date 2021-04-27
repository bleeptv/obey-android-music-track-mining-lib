package com.ygorxkharo.trackmining.tracks.model

/**
 * Describes the elements involved in the playback of the music track
 *
 * @property platform An enumerator to describe from which platform the music track is playable from
 * i.e. Spotify or Apple Music
 * @property streamingUri The URI leading to the music track's MP3 record (i.e.
 * @property licensingRestrictions Details on the regions that are available to play the track
 */
data class PlaybackAttribution(
        val platform: String,
        val streamingUri: String,
        val licensingRestrictions: LicensingRestrictions
)
