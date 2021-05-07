package com.ygorxkharo.obey.trackmining

import com.ygorxkharo.trackmining.platform.MusicLibraryTracksMiner
import com.ygorxkharo.trackmining.tracks.model.LibraryTrack

/**
 * Default implementation to provide library tracks from various music platforms
 *
 */
class DefaultLibraryTracksProvider(
    override val platformCollection: Map<String, MusicLibraryTracksMiner>
): LibraryTracksProvider<String>  {

    override fun mineFromPlatform(
        chosenMusicPlatformSource: String,
        onMineSuccess: (List<LibraryTrack>) -> Unit,
        onMineError: (Throwable) -> Unit
    ) {
        val currentPlatform = platformCollection[chosenMusicPlatformSource]
        currentPlatform?.mine(onMineSuccess, onMineError) ?:
        onMineError(Throwable("Music platform miner for this key doesn't exist"))
    }
}
