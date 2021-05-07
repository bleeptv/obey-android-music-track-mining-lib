package com.ygorxkharo.obey.trackmining

import com.ygorxkharo.trackmining.exceptions.LibraryTracksMiningException
import com.ygorxkharo.obey.trackmining.platform.MusicTracksSource
import com.ygorxkharo.trackmining.platform.MusicLibraryTracksMiner
import com.ygorxkharo.trackmining.LibraryTrackMiningRequest
import com.ygorxkharo.trackmining.LibraryTracksProvider
import com.ygorxkharo.trackmining.tracks.model.LibraryTrack

/**
 * Default implementation to provide library tracks from various music platforms. This version
 * uses a [MusicTracksSource] to describe which platform to use for the track mining process
 *
 */
class DefaultLibraryTracksProvider(
    override val platformCollection: Map<MusicTracksSource, MusicLibraryTracksMiner>
): LibraryTracksProvider<MusicTracksSource> {

    override fun provideFromPlatform(
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
