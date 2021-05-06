package com.ygorxkharo.trackmining.platform

import com.ygorxkharo.trackmining.tracks.model.LibraryTrack

/**
 * Represents a streaming platform which contains tracks that can be mined
 */
interface StreamingPlatform {

    /**
     * Mine music tracks from the streaming platform
     *
     * @param onSuccess Triggered once the mining process is completed
     * @param onError Triggered when the mining process fails
     */
    fun mineMusicLibraryTracks(
        onSuccess: (List<LibraryTrack>) -> Unit,
        onError: (Throwable) -> Unit
    )
}