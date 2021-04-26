package com.ygorxkharo.trackmining

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ExampleClassTest {

    @Test
    fun `test add method`() {
        val exampleClass = ExampleClass()
        val value1 = 2
        val value2 = 3
        val expected = 5
        assertEquals(expected, exampleClass.add(value1, value2))
    }

    @Test
    fun `test subtract method`() {
        val exampleClass = ExampleClass()
        val value1 = 3
        val value2 = 2
        val expected = 1
        assertEquals(expected, exampleClass.subtract(value1, value2))
    }
}