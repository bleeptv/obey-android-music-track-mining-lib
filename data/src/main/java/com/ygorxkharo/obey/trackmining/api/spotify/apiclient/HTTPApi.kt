package com.ygorxkharo.obey.trackmining.api.spotify.apiclient

import com.ygorxkharo.obey.trackmining.api.spotify.apiclient.model.GetTracksFromSpotifyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Network connection to the Spotify Web API
 */
interface HTTPApi {

    /**
     * Get a collection of tracks liked by the current user
     *
     * @param authorizationToken String
     * @return Call<GetTracksFromSpotifyResponse>
     */
    @GET("v1/me/tracks")
    fun getLikedSongs(
        @Header(Constants.AUTHORIZATION_HEADER_KEY) authorizationToken: String,
        @Query(Constants.RESULT_LIMIT) resultLimit: Int
    ): Call<GetTracksFromSpotifyResponse>
}