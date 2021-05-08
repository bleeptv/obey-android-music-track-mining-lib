package com.ygorxkharo.trackmining

import com.ygorxkharo.trackmining.tracks.model.LibraryTrack

interface MineLibraryTracksUseCase {
    fun execute(miningTrackRequest: LibraryTrackMiningRequest): Result<List<LibraryTrack>>
}

class MineLibraryTracksUseCaseImpl(
    private val libraryTracksProvider: LibraryTracksProvider<String>
): MineLibraryTracksUseCase {

    override fun execute(miningTrackRequest: LibraryTrackMiningRequest): Result<List<LibraryTrack>> {
        return libraryTracksProvider.provideFromPlatform(miningTrackRequest)
    }
}