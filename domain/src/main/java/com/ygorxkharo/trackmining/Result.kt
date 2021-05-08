package com.ygorxkharo.trackmining

/**
 * Represent a result from an operation, normally when performed on the data layer of the app
 * (i.e. when making network calls, database requests, object persistence, etc)
 * If the result of the operation is a success, an object of generic type [T] is embedded
 * in the result
 */
sealed class Result<out T : Any>

/**
 * Represents a successful [Result] state from a operation in the data layer
 *
 * @property payload object of generic type [T] returned as a result of a successful operation
 */
data class Success<out T : Any>(val payload: T) : Result<T>()

/**
 * Represents a failed/error [Result] state from a operation in the data layer
 *
 * @property error The main cause of the performed operation's failure
 */
data class Failure(val error: Throwable) : Result<Nothing>()
