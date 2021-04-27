package com.ygorxkharo.trackmining.tracks.model

/**
 * Contains a collection of all the regions where a music track is playable
 *
 * @property regionalAvailability a collection of regions where a music track can be played in
 */
data class LicensingRestrictions(
        val regionalAvailability: List<String>
)
