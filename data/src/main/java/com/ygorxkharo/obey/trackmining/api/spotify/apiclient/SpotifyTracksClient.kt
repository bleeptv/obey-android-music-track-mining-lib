package com.ygorxkharo.obey.trackmining.api.spotify.apiclient

import kotlinx.coroutines.Job
import com.ygorxkharo.obey.networking.client.retrofit.getResult
import com.ygorxkharo.obey.utilities.threading.CoroutineContextProvider
import com.ygorxkharo.obey.utilities.threading.ThreadingExecutor
import com.ygorxkharo.obey.trackmining.api.LibraryTracksHttpClient
import com.ygorxkharo.obey.trackmining.api.spotify.apiclient.mapper.LibraryTrackMapper
import com.ygorxkharo.trackmining.tracks.model.LibraryTrack
import com.ygorxkharo.trackmining.common.api.client.model.Result

/**
 * Spotify's HTTP implementation for fetching library tracks
 *
 * @property spotifyApi A representation of the Spotify Web API
 * @property coroutineContextProvider Provides the appropriate coroutine context depending on the
 * task process for a request
 * @property libraryTrackMapper Transforms the Spotify library tracks to domain model tracks
 */
class SpotifyTracksClient(
    private val spotifyApi: HTTPApi,
    private val coroutineContextProvider: CoroutineContextProvider,
    private val libraryTrackMapper: LibraryTrackMapper
): LibraryTracksHttpClient {

    override val networkCallsCollection: MutableList<Job> = ArrayList()

    override fun getLibraryTracks(
        authToken: String,
        resultLimit: Int,
        onComplete: (Result<List<LibraryTrack>>) -> Unit
    )  {
        val currentRequest = ThreadingExecutor.executeAsyncOperation(
            performInBackground = { fetchLikedSongs(authToken, resultLimit) },
            performInMainProcess = { libraryTracksResult -> onComplete(libraryTracksResult) },
            contextProvider = coroutineContextProvider
        )
        networkCallsCollection.add(currentRequest)
    }

    /**
     * Fetch the current user's liked songs from Spotify
     *
     * @param authToken Authentication token used in the header of the network request
     * @return a collection of library tracks
     */
    private fun fetchLikedSongs(authToken: String, resultLimit: Int): Result<List<LibraryTrack>> {
        return spotifyApi
            .getLikedSongs(authToken, resultLimit)
            .getResult {
                val likedSongsResponse = it
                likedSongsResponse.trackItems.map { trackItem ->
                    libraryTrackMapper.convertToLibraryTrack(trackItem.track)
                }
            }
    }
}
