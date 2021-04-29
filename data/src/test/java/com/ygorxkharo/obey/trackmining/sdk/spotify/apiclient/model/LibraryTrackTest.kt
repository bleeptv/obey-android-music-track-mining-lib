package com.ygorxkharo.obey.trackmining.sdk.spotify.apiclient.model

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.ygorxkharo.filesystem.TestFileUtils
import com.ygorxkharo.obey.trackmining.api.spotify.apiclient.model.GetTracksFromSpotifyResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import com.ygorxkharo.obey.trackmining.sdk.spotify.apiclient.fixtures.SpotifyTrackJSONResultTestFixture

internal class LibraryTrackTest {

    private val responsePayloadFileLocation = SpotifyTrackJSONResultTestFixture.responsePayloadFileLocation
    private val moshiInstance = Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    private val likedSongsPayloadAdapter: JsonAdapter<GetTracksFromSpotifyResponse> = moshiInstance.adapter(
        GetTracksFromSpotifyResponse::class.java)


    @Test
    fun `test when transforming liked songs payload to JSON, expect output JSON string value to be the same`() {
        // Arrange
        val likedSongsPayloadJson = TestFileUtils.convertFileContentsToString(responsePayloadFileLocation)
        val responsePayloadToJson = likedSongsPayloadAdapter.fromJson(likedSongsPayloadJson)
        val expectedJsonString = likedSongsPayloadAdapter.toJson(responsePayloadToJson)

        val spotifyLikeSongsResultObject = GetTracksFromSpotifyResponse(
            trackItems = listOf(SpotifyTrackJSONResultTestFixture.buildSpotifyTrackItem())
        )

        //Act
        val actualJsonString = likedSongsPayloadAdapter.toJson(spotifyLikeSongsResultObject)

        //Assert
        assertEquals(expectedJsonString, actualJsonString)
    }
}
