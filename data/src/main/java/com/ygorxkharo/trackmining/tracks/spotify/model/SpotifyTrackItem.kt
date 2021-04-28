package com.ygorxkharo.trackmining.tracks.spotify.model

import com.squareup.moshi.Json

data class SpotifyTrackItem(

    @Json(name = "track")
    val trackItem: SpotifyLibraryTrack
)
