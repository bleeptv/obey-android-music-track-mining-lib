package com.ygorxkharo.filesystem

import java.io.BufferedReader
import java.io.InputStream
import java.lang.StringBuilder

/**
 * Handles the loading/persistence of text files to use in a test environment
 */
object TestFileUtils {

    /**
     * Converts a file's contents into a string for further processing
     *
     * @param filePath File location in the filesystem
     * @return the contents of the file in the form af a [String]
     */
    fun convertFileContentsToString(filePath: String): String {

        val fileInputStream =  this.javaClass.classLoader?.getResourceAsStream(filePath)
        val bufferedReader = BufferedReader((fileInputStream as InputStream).reader())
        val content = StringBuilder()
        bufferedReader.use { reader ->
            var line = reader.readLine()
            while (line != null) {
                content.append(line)
                line = reader.readLine()
            }
        }
        return content.toString()
    }
}
