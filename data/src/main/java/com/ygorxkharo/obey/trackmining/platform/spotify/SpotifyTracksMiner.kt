package com.ygorxkharo.obey.trackmining.platform.spotify

import com.ygorxkharo.obey.common.Failure
import com.ygorxkharo.obey.common.Success
import com.ygorxkharo.obey.trackmining.api.LibraryTracksHttpClient
import com.ygorxkharo.trackmining.platform.MusicLibraryTracksMiner
import com.ygorxkharo.trackmining.tracks.model.LibraryTrack

/**
 * Default implementation of a music track miner for Spotify
 *
 * @property tracksClient Client used to fetch music tracks from the Spotify streaming platform
 * @property trackResultLimit Maximum number of tracks to return when mining
 */
class SpotifyTracksMiner(
    private val tracksClient: LibraryTracksHttpClient,
    private val trackResultLimit: Int
): MusicLibraryTracksMiner {

    override fun mine(
        onSuccess: (List<LibraryTrack>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val authToken = "" // TODO: Handle authentication token provision in Jira ticket #B2MVE-1140
        tracksClient.getLibraryTracks(authToken, trackResultLimit) { result ->
            when(result) {
                is Success -> onSuccess(result.payload)
                is Failure -> onError(result.error)
            }
        }
    }
}
