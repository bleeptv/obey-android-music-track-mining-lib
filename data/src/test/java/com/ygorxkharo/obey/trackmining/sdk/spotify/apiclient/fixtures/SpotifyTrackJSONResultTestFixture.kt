package com.ygorxkharo.obey.trackmining.sdk.spotify.apiclient.fixtures

import com.ygorxkharo.obey.trackmining.api.spotify.apiclient.model.Album
import com.ygorxkharo.obey.trackmining.api.spotify.apiclient.model.Artist
import com.ygorxkharo.obey.trackmining.api.spotify.apiclient.model.ExternalIds
import com.ygorxkharo.obey.trackmining.api.spotify.apiclient.model.AlbumImage
import com.ygorxkharo.obey.trackmining.api.spotify.apiclient.model.LibraryTrack
import com.ygorxkharo.obey.trackmining.api.spotify.apiclient.model.TrackItem

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
     * @return a [Album] containing album art and details on the album's release and title
     */
    private fun buildAlbumContents(): Album {
        val albumCoverImage = AlbumImage(
            width = DEFAULT_IMAGE_WIDTH,
            height = DEFAULT_IMAGE_HEIGHT,
            imageUrl = albumArtUrl
        )
        return Album(
            title = albumTitle,
            images = listOf(albumCoverImage),
            releaseDateYYYYmmDD = trackReleaseDate,
        )
    }

    /**
     * Build a Spotify Track Item to add to a response JSON payload object
     *
     * @return a Spotify Track Item consisting of a single track
     */
    fun buildSpotifyTrackItem(): TrackItem {
        val performingArtist = Artist(name = artistName)
        val likedTrack = LibraryTrack(
            title = trackTitle,
            trackId = trackId,
            albumContents = buildAlbumContents(),
            durationInMillis = trackDuration,
            externalIds = ExternalIds(
                isrcCode = trackISRCCode
            ),
            regionalAvailability = regionalAvailabilityList,
            streamingUri = testTrackStreamingUri,
            artists = listOf(performingArtist)
        )
        return TrackItem(
            track = likedTrack
        )
    }
}