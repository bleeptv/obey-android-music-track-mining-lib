package com.ygorxkharo.trackmining.platform

import com.ygorxkharo.trackmining.common.api.client.model.Result
import com.ygorxkharo.trackmining.tracks.model.LibraryTrack

/**
 * Represents a music platform which contains library tracks that can be mined
 */
interface MusicLibraryTracksMiner {

    /**
     * Mine music tracks from the streaming platform
     *
     */
    fun mine(): Result<List<LibraryTrack>>
}
