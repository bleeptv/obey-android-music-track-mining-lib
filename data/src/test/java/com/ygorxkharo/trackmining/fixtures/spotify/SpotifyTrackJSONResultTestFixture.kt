package com.ygorxkharo.trackmining.fixtures.spotify

import com.ygorxkharo.trackmining.tracks.spotify.model.SpotifyAlbum
import com.ygorxkharo.trackmining.tracks.spotify.model.SpotifyArtist
import com.ygorxkharo.trackmining.tracks.spotify.model.SpotifyExternalIds
import com.ygorxkharo.trackmining.tracks.spotify.model.SpotifyAlbumImage
import com.ygorxkharo.trackmining.tracks.spotify.model.SpotifyLibraryTrack
import com.ygorxkharo.trackmining.tracks.spotify.model.SpotifyTrackItem

/**
 * Test fixture to build a serialized Spotify JSON track object representation
 */
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

    /**
     * Build album information for a track object
     *
     * @return a [SpotifyAlbum] containing album art and details on the album's release and title
     */
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

    /**
     * Build a Spotify Track Item to add to a response JSON payload object
     *
     * @return a Spotify Track Item consisting of a single track
     */
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