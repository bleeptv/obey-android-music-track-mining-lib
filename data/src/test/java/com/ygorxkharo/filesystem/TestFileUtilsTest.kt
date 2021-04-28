package com.ygorxkharo.filesystem

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class TestFileUtilsTest {

    @Test
    fun `test when reading a file content, expect contents to be equal to test file input`() {
        //Arrange
        val expectedTestFileContent = "Testing file reader text"
        val filePath = "filesystem/test_file_reader_text.txt"

        //Act
        val actualFileContents = TestFileUtils.convertFileContentsToString(filePath)

        //Assert
        assertEquals(expectedTestFileContent, actualFileContents)
    }
}