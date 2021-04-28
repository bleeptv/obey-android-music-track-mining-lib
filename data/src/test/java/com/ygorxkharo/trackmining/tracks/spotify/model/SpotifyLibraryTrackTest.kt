package com.ygorxkharo.trackmining.tracks.spotify.model

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.ygorxkharo.filesystem.TestFileUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import com.ygorxkharo.trackmining.fixtures.spotify.SpotifyTrackJSONResultTestFixture

internal class SpotifyLibraryTrackTest {

    private val responsePayloadFileLocation = SpotifyTrackJSONResultTestFixture.responsePayloadFileLocation
    private val moshiInstance = Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    private val likedSongsPayloadAdapter: JsonAdapter<SpotifyTracksResult> = moshiInstance.adapter(SpotifyTracksResult::class.java)


    @Test
    fun `test when transforming liked songs payload to JSON, expect output JSON string value to be the same`() {
        // Arrange
        val likedSongsPayloadJson = TestFileUtils.convertFileContentsToString(responsePayloadFileLocation)
        val responsePayloadToJson = likedSongsPayloadAdapter.fromJson(likedSongsPayloadJson)
        val expectedJsonString = likedSongsPayloadAdapter.toJson(responsePayloadToJson)

        val spotifyLikeSongsResultObject = SpotifyTracksResult(
            trackItems = listOf(SpotifyTrackJSONResultTestFixture.buildSpotifyTrackItem())
        )

        //Act
        val actualJsonString = likedSongsPayloadAdapter.toJson(spotifyLikeSongsResultObject)

        //Assert
        assertEquals(expectedJsonString, actualJsonString)
    }
}
