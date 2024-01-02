package com.plyonest.interval.presentation.utils

import java.util.concurrent.TimeUnit

class HoursMinutesSeconds(
    private val hours: String,
    private val minutes: String,
    private val seconds: String
) {
    fun getHours(): String {
        return hours
    }

    fun getMinutes(): String {
        return minutes
    }

    fun getSeconds(): String {
        return seconds
    }

    fun areSecondsEmpty(): Boolean {
        return areMinutesEmpty() && seconds.toLong() <= 0
    }

    fun areMinutesEmpty(): Boolean {
        return areHoursEmpty() && minutes.toLong() <= 0
    }

    fun areHoursEmpty(): Boolean {
        return hours.toLong() <= 0
    }
}

class TimeUtil {
    companion object {
        fun convertHoursMinutesSecondsToMillis(hms: HoursMinutesSeconds): Long {
            val hours = hms.getHours().toLong() * 3_600_000
            val minutes = hms.getMinutes().toLong() * 60_000
            val seconds = hms.getSeconds().toLong() * 1_000

            return hours + minutes + seconds
        }

        fun convertTimeAsStringToHms(timeAsString: String): HoursMinutesSeconds {
            val tempTimeAsString = timeAsString.padStart(6, '0')
            val timeAsArr = tempTimeAsString.toCharArray()
            val indicesToMove = 2
            val secondsIndexStart = timeAsArr.size - indicesToMove
            val minutesIndexStart = secondsIndexStart - indicesToMove
            val secondsArr = timeAsArr.slice(secondsIndexStart..<timeAsArr.size)
            val minutesArr = timeAsArr.slice(minutesIndexStart..<secondsIndexStart)
            val hoursArr = timeAsArr.slice(0..<minutesIndexStart)
            val seconds = secondsArr.joinToString("")
            val minutes = minutesArr.joinToString("")
            val hours = hoursArr.joinToString("")

            return HoursMinutesSeconds(hours, minutes, seconds)
        }

        fun convertTimeAsMillisToHms(millis: Long): HoursMinutesSeconds {
            val hours = TimeUnit.MILLISECONDS.toHours(millis)
            val minutes = TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(
                TimeUnit.MILLISECONDS.toHours(millis)
            )
            val seconds = TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(millis)
            )

            return HoursMinutesSeconds(hours.toString(), minutes.toString(), seconds.toString())
        }
    }
}