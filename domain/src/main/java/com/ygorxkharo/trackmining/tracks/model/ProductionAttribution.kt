package com.ygorxkharo.trackmining.tracks.model

/**
 * Describes the parties that were involved in the creation of a music track
 *
 * @property leadArtist Principal performer on a music track. Can also be called the lead singer
 * @property featuredArtists A collection of artists that also performed alongside the lead artist
 */
data class ProductionAttribution(
        val leadArtist: String,
        val featuredArtists: List<String>
)
