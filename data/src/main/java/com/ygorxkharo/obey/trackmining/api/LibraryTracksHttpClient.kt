package com.ygorxkharo.obey.trackmining.api

import com.ygorxkharo.trackmining.common.api.client.model.Result
import com.ygorxkharo.trackmining.tracks.model.LibraryTrack

/**
 * Contract for fetching library tracks from various streaming platform sources
 *
 */
interface LibraryTracksHttpClient {

    /**
     * Get library tracks from the a specific HTTP source
     *
     * @param authToken The user's authentication token to perform requests
     * @param queryResultLimit The maximum number of library tracks to return
     * response or an error)
     */
    fun getLibraryTracks(
        authToken: String,
        queryResultLimit: Int
    ): Result<List<LibraryTrack>>
}
