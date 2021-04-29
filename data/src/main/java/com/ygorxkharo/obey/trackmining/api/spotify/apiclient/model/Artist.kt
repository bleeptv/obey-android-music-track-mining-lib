package com.ygorxkharo.obey.trackmining.api.spotify.apiclient.model

import com.squareup.moshi.Json

/**
 * Represents a JSON object to describe an artist on a Spotify track
 */
data class Artist(

    /**
     * @property name The name of a performing artist on the Spotify platform
     */
    @Json(name = "name")
    val name: String
)
