package com.ygorxkharo.trackmining.tracks.spotify.model

import com.squareup.moshi.Json

/**
 * Represents the JSON response payload when fetching tracks from Spotify
 *
 * @property trackItems A collection of Spotify Track Items, which contain details on a specific track
 */
data class SpotifyTracksResult(

    @Json(name = "items")
    val trackItems: List<SpotifyTrackItem>
)
