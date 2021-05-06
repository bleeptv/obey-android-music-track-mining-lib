package com.ygorxkharo.obey.trackmining.platform

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class MusicTracksSourceTest {

    @Test
    fun `test when platform name is spotify, expect the streaming platform type to be SPOTIFY`() {
        //Arrange
        val expectedPlatformType = MusicTracksSource.SPOTIFY
        val platformNameValue = "spotify"

        //Act
        val actualPlatformType = MusicTracksSource.getType(platformNameValue)

        //Assert
        assertEquals(expectedPlatformType, actualPlatformType)
    }

    @Test
    fun `test when platform name is apple_music, expect the streaming platform type to be APPLE_MUSIC`() {
        //Arrange
        val expectedPlatformType = MusicTracksSource.APPLE_MUSIC
        val platformNameValue = "apple_music"

        //Act
        val actualPlatformType = MusicTracksSource.getType(platformNameValue)

        //Assert
        assertEquals(expectedPlatformType, actualPlatformType)
    }
}