package com.ygorxkharo.obey.trackmining

import com.ygorxkharo.obey.trackmining.exceptions.LibraryTracksMiningException
import com.ygorxkharo.obey.trackmining.platform.MusicTracksSource
import com.ygorxkharo.trackmining.platform.MusicLibraryTracksMiner
import com.ygorxkharo.trackmining.tracks.model.LibraryTrack

/**
 * Default implementation to provide library tracks from various music platforms
 *
 */
class DefaultLibraryTracksProvider(
    override val platformCollection: Map<MusicTracksSource, MusicLibraryTracksMiner>
): LibraryTracksProvider  {

    override fun mineFromPlatform(
        trackMiningRequest: LibraryTrackMiningRequest,
        onSuccess: (List<LibraryTrack>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val platformType = MusicTracksSource.getType(trackMiningRequest.chosenPlatformName) ?:
            throw LibraryTracksMiningException("Music track source type doesn't exist for this platform name")
        val currentPlatform = platformCollection[platformType] ?:
            throw LibraryTracksMiningException("Library track miner for this key doesn't exist")
        currentPlatform.mine(onSuccess, onError)
    }
}
