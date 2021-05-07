package com.ygorxkharo.trackmining.exceptions

/**
 * Represent an error occurring when trying to use a non-existent music
 * library track miner or something else goes wrong with the mining process
 */
class LibraryTracksMiningException: Exception {
    /**
     * An exception constructor that takes a message
     * @param message The message explaining the cause of the error
     */
    constructor(message: String): super(message)

    /**
     * An exception constructor that takes a throwable in the case of chained exceptions
     * @param throwable The throwable containing the original stacktrace explaining the cause of the error
     */
    constructor(throwable: Throwable): super(throwable)
}
