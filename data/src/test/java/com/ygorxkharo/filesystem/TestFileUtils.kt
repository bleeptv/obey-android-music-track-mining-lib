package com.ygorxkharo.filesystem

import java.io.BufferedReader
import java.io.InputStream
import java.lang.StringBuilder

object TestFileUtils {

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