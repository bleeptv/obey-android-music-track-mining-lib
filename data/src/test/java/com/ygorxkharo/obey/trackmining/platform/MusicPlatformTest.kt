package com.ygorxkharo.obey.trackmining.platform

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class MusicPlatformTest {

    @Test
    fun `test when platform name is spotify, expect the streaming platform type to be SPOTIFY`() {
        //Arrange
        val expectedPlatformType = MusicPlatform.SPOTIFY
        val platformNameValue = "spotify"

        //Act
        val actualPlatformType = MusicPlatform.getType(platformNameValue)

        //Assert
        assertEquals(expectedPlatformType, actualPlatformType)
    }

    @Test
    fun `test when platform name is apple_music, expect the streaming platform type to be APPLE_MUSIC`() {
        //Arrange
        val expectedPlatformType = MusicPlatform.APPLE_MUSIC
        val platformNameValue = "apple_music"

        //Act
        val actualPlatformType = MusicPlatform.getType(platformNameValue)

        //Assert
        assertEquals(expectedPlatformType, actualPlatformType)
    }
}