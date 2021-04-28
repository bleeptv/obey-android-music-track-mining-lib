package com.ygorxkharo.trackmining.tracks.spotify.model

import com.squareup.moshi.Json

/**
 * Represents a JSON object of music track metadata fetched from Spotify
 *
 * @property trackItem Spotify-related metadata on a track
 */
data class SpotifyTrackItem(

    @Json(name = "track")
    val trackItem: SpotifyLibraryTrack
)
