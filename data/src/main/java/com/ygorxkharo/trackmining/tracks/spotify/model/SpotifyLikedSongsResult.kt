package com.ygorxkharo.trackmining.tracks.spotify.model

import com.squareup.moshi.Json

class SpotifyLikedSongsResult(

    @Json(name = "items")
    val trackItems: List<SpotifyTrackItem>
)
