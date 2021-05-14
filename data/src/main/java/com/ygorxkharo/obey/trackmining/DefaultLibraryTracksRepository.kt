package com.ygorxkharo.obey.trackmining

import com.ygorxkharo.trackmining.exceptions.LibraryTracksMiningException
import com.ygorxkharo.obey.trackmining.platform.MusicTracksSource
import com.ygorxkharo.trackmining.platform.MusicLibraryTracksMiner
import com.ygorxkharo.trackmining.LibraryTrackMiningRequest
import com.ygorxkharo.trackmining.LibraryTracksRepository
import com.ygorxkharo.trackmining.common.api.client.model.Result
import com.ygorxkharo.trackmining.tracks.model.LibraryTrack

/**
 * Default implementation to provide library tracks from various music platforms. This version
 * uses a [MusicTracksSource] to describe which platform to use for the track mining process
 *
 */
class DefaultLibraryTracksRepository(
    override val platformCollection: Map<String, MusicLibraryTracksMiner>
): LibraryTracksRepository<String> {

    override fun getFromPlatform(
        trackMiningRequest: LibraryTrackMiningRequest
    ): Result<List<LibraryTrack>> {
        val currentPlatform = platformCollection[trackMiningRequest.chosenPlatformName] ?:
            throw LibraryTracksMiningException("Library track miner for this key doesn't exist")
        return currentPlatform.mine()
    }
}
