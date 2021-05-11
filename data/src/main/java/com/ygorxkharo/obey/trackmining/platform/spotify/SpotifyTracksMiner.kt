package com.ygorxkharo.obey.trackmining.platform.spotify

import com.ygorxkharo.obey.trackmining.api.LibraryTracksHttpClient
import com.ygorxkharo.trackmining.common.api.client.model.Failure
import com.ygorxkharo.trackmining.common.api.client.model.Success
import com.ygorxkharo.trackmining.platform.MusicLibraryTracksMiner
import com.ygorxkharo.trackmining.tracks.model.LibraryTrack

/**
 * Default implementation of a music track miner for Spotify
 *
 * @property libraryTracksApiClient Client used to fetch music tracks from the Spotify streaming platform
 * @property queryResultsLimit Maximum number of tracks to return when mining
 */
class SpotifyTracksMiner(
    private val libraryTracksApiClient: LibraryTracksHttpClient,
    private val queryResultsLimit: Int
): MusicLibraryTracksMiner {

    override fun mine(
        onSuccess: (List<LibraryTrack>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val authToken = "" // TODO: Handle authentication token provision in Jira ticket #B2MVE-1140
        libraryTracksApiClient.getLibraryTracks(authToken, queryResultsLimit) { result ->
            when(result) {
                is Success -> onSuccess(result.payload)
                is Failure -> onError(result.error)
            }
        }
    }
}
