package com.ygorxkharo.trackmining

import com.ygorxkharo.trackmining.platform.MusicLibraryTracksMiner
import com.ygorxkharo.trackmining.tracks.model.LibraryTrack

/**
 * Represents the object in charge of fetching library tracks from various music platforms.
 * The key defining which platform to use for mining is defined by the generic type [T]
 */
interface LibraryTracksProvider<T> {

    /**
     * @property platformCollection Contains a collection of music platforms to mine music tracks
     * from, using a key defining the music platform of generic type [T]
     */
    val platformCollection: Map<T, MusicLibraryTracksMiner>

    /**
     *
     * @param trackMiningRequest Defines the music platform to mine tracks from, of generic type [T]
     * @param onSuccess Callback triggered when the mining process is successful
     * @param onError Callback triggered when the mining process fails due to an error
     */
    fun mineFromPlatform(
        trackMiningRequest: LibraryTrackMiningRequest,
        onSuccess: (List<LibraryTrack>) -> Unit,
        onError: (Throwable) -> Unit
    )
}
