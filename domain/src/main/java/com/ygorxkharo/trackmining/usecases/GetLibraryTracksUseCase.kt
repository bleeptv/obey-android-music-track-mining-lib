package com.ygorxkharo.trackmining.usecases

import com.ygorxkharo.trackmining.LibraryTrackMiningRequest
import com.ygorxkharo.trackmining.LibraryTracksProvider
import com.ygorxkharo.trackmining.common.api.client.model.Result
import com.ygorxkharo.trackmining.tracks.model.LibraryTrack

/**
 * Illustrates how library tracks are fetched in the library, showing which components are called
 * to mine tracks
 *
 * @property libraryTracksProvider Used to fetch library tracks resources from various available source
 */
class GetLibraryTracksUseCase(
    private val libraryTracksProvider: LibraryTracksProvider<String>
) {

    /**
     * Executes the use case to get library tracks from the library
     *
     * @param miningTrackRequest A request to mine a library tracks from a specific streaming platform
     * @return a collection of library tracks when executed successfully or returns an failure result
     * if an error occurs while getting library tracks
     */
    operator fun invoke(miningTrackRequest: LibraryTrackMiningRequest): Result<List<LibraryTrack>> {
        return libraryTracksProvider.provideFromPlatform(miningTrackRequest)
    }
}
