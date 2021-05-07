package com.ygorxkharo.obey.trackmining

import com.ygorxkharo.trackmining.platform.MusicLibraryTracksMiner
import com.ygorxkharo.trackmining.tracks.model.LibraryTrack

/**
 * Represents the object in charge of fetching library tracks from various music platforms. The
 * generic type [T] represents the way a music tracks mining platform is represented
 * (i.e as a [String], the Spotify platform would be represented as "spotify")
 *
 */
interface LibraryTracksProvider<T> {

    /**
     * @property platformCollection Contains a collection of music platforms to mine music tracks
     * from, using a key defining the music platform of generic type [T]
     */
    val platformCollection: Map<T, MusicLibraryTracksMiner>

    /**
     *
     * @param chosenMusicPlatformSource Defines the music platform to mine tracks from, of generic type [T]
     * @param onMineSuccess Callback triggered when the mining process is successful
     * @param onMineError Callback triggered when the mining process fails due to an error
     */
    fun mineFromPlatform(
        chosenMusicPlatformSource: T,
        onMineSuccess: (List<LibraryTrack>) -> Unit,
        onMineError: (Throwable) -> Unit
    )
}
