package com.ygorxkharo.trackmining.fixtures.spotify

import com.ygorxkharo.trackmining.tracks.spotify.model.SpotifyAlbum
import com.ygorxkharo.trackmining.tracks.spotify.model.SpotifyArtist
import com.ygorxkharo.trackmining.tracks.spotify.model.SpotifyExternalIds
import com.ygorxkharo.trackmining.tracks.spotify.model.SpotifyAlbumImage
import com.ygorxkharo.trackmining.tracks.spotify.model.SpotifyLibraryTrack
import com.ygorxkharo.trackmining.tracks.spotify.model.SpotifyTrackItem

object SpotifyTrackJSONResultTestFixture {

    const val responsePayloadFileLocation = "tracks/spotify/responses/spotify_liked_songs_response_payload.json"
    private val regionalAvailabilityList = listOf("NG","ZA")
    private const val trackId = "6E1ZonB1Fosz99Rg0tUBTi"
    private const val testTrackStreamingUri = "spotify:track:$trackId"
    private const val albumTitle = "Stories"
    private const val trackTitle = albumTitle
    private const val albumArtUrl = "https://i.scdn.co/image/ab67616d0000b27309bd912a6dd8ab6faad57346"
    private const val artistName = "Refs"
    private const val trackReleaseDate = "2019-06-07"
    private const val DEFAULT_IMAGE_WIDTH = 640
    private const val DEFAULT_IMAGE_HEIGHT = 640
    private const val trackDuration = 219475L
    private const val trackISRCCode = "GBKPL1828466"

    private fun buildAlbumContents(): SpotifyAlbum {
        val albumCoverImage = SpotifyAlbumImage(
            width = DEFAULT_IMAGE_WIDTH,
            height = DEFAULT_IMAGE_HEIGHT,
            imageUrl = albumArtUrl
        )
        return SpotifyAlbum(
            albumTitle = albumTitle,
            albumImages = listOf(albumCoverImage),
            releaseDate = trackReleaseDate,
        )
    }

    fun buildSpotifyTrackItem(): SpotifyTrackItem {
        val performingArtist = SpotifyArtist(name = artistName)
        val likedTrack = SpotifyLibraryTrack(
            trackTitle = trackTitle,
            trackId = trackId,
            albumContents = buildAlbumContents(),
            durationInMillis = trackDuration,
            externalIds = SpotifyExternalIds(
                isrcCode = trackISRCCode
            ),
            regionalAvailability = regionalAvailabilityList,
            streamingUri = testTrackStreamingUri,
            artists = listOf(performingArtist)
        )
        return SpotifyTrackItem(
            trackItem = likedTrack
        )
    }
}