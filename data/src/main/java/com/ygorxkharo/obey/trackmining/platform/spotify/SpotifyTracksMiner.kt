package com.ygorxkharo.obey.trackmining.platform.spotify

import com.ygorxkharo.obey.trackmining.api.LibraryTracksHttpClient
import com.ygorxkharo.trackmining.Failure
import com.ygorxkharo.trackmining.Result
import com.ygorxkharo.trackmining.Success
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

    override fun mine(): Result<List<LibraryTrack>> {
        //val authToken = "" // TODO: Handle authentication token provision in Jira ticket #B2MVE-1140
        val authToken = "Bearer BQBoHXK7TvW8KB-njsUCw41sT94eVql3f_Ih-60FO38IPgohGo-fAK7YyvI196K_2ZOBMnD_hXAqCApuCMR1-h5Oyz2zeQataNDFNGZm4IR2kRO7HFKo4vIv_2GTSHaUU0GemKhX85o1oyEZ3hCxoGSvUaUYraaLMVJUo2lU_lzdy32JQJgLkCtsJnfZjzjuGu3mj9Lsccl81_fo3lT0L6lnrm-fsFKmeTpmdjIpbGTnoV_gmSQxsryMtXALxxtLp4jcXgJAemr2gR35U5fwpeXpFg"
        return libraryTracksApiClient.getLibraryTracks(authToken, queryResultsLimit)
    }
}
