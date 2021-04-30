package com.ygorxkharo.obey.networking

/**
 * Represents a single/range of HTTP codes
 */
object HttpCodes {
    val serverErrors: IntRange = 500..599
    val authErrors: IntRange = 400..499
}
