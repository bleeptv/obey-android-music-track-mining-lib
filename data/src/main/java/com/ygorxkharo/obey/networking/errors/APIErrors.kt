package com.ygorxkharo.obey.networking.errors

/**
 * Represents any network-related exception triggered when making a network call
 * i.e. SocketTimeOutException, UnknownHostException
 */
object NetworkError : Throwable("No Network. Check your internet connection and try again")

/**
 * Represents any server-side related exception triggered when making a network call
 */
object ServerError : Throwable("There was an issue with the server. Try again soon")

/**
 * Represents any authentication related exception triggered when making a network call
 */
object AuthenticationError : Throwable("Authentication error with the server")
