package com.ygorxkharo.trackmining.tracks.spotify.model

import com.squareup.moshi.Json

data class SpotifyLibraryTrack(

    @Json(name = "name")
    val trackTitle: String,

    @Json(name = "album")
    val albumContents: SpotifyAlbum,

    @Json(name = "artists")
    val artists: List<SpotifyArtist>,

    @Json(name = "available_markets")
    val regionalAvailability: List<String>,

    @Json(name = "duration_ms")
    val durationInMillis: Long,

    @Json(name = "external_ids")
    val externalIds: SpotifyExternalIds,

    @Json(name = "id")
    val trackId: String,

    @Json(name = "uri")
    val streamingUri: String
)


