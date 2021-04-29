package com.ygorxkharo.obey.trackmining.api.spotify.apiclient.model

import com.squareup.moshi.Json

/**
 * Represents a JSON object containing identifiers for a track created outside Spotify
 */
data class ExternalIds(

    /**
     * @property isrcCode International Standard Recording Code for a track
     */
    @Json(name = "isrc")
    val isrcCode: String
)
