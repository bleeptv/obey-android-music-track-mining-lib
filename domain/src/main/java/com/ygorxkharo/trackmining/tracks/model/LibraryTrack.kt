package com.ygorxkharo.trackmining.tracks.model


/**
 * Representing a library music track containing all the details of a song to use in mixtape
 * performance
 *
 * @property trackContent All the metadata on the music track's origin (i.e. genre, album, title, etc)
 * @property skus Stock Keeping Units for tracing the track (i.e. isrc ID, etc)
 * @property productionAttribution Describes the parties that were involved in the creation of the music track
 * @property publishingAttribution Describes the parties involved in the distribution of the music track
 * @property playbackAttribution Describes the elements involved in the playback of the music track
 */
data class LibraryTrack(
        val trackContent: TrackContent,
        val skus: SKUs,
        val productionAttribution: ProductionAttribution,
        val publishingAttribution: PublishingAttribution,
        val playbackAttribution: PlaybackAttribution
)
