package com.ygorxkharo.obey.trackmining.api.spotify.apiclient.model

import com.squareup.moshi.Json

/**
 * Represents the JSON response payload when fetching tracks from Spotify
 */
data class GetTracksFromSpotifyResponse(

    /**
     * @property trackItems A collection of Spotify Track Items, which contain details on a specific track
     */
    @Json(name = "items")
    val trackItems: List<TrackItem>
)
