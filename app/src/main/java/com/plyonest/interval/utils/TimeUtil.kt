package com.plyonest.interval.utils

import java.util.concurrent.TimeUnit

class TimeUtil {
    companion object {
        fun convertMillisToHumanReadableTime(millis: Long, withPrecedingZeroes: Boolean = false): String {
            val hours = TimeUnit.MILLISECONDS.toHours(millis)
            val minutes = TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis))
            val seconds = TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))

            val secondsFormat = "%02ds"
            val minutesFormat = "%02dm"
            val hoursFormat = "%02dh"
            var format = secondsFormat
            if (!withPrecedingZeroes) {
                if (minutes > 0) {
                    format = "$minutesFormat $secondsFormat"
                } else if (hours > 0) {
                    format = "$hoursFormat $minutesFormat $secondsFormat"
                }
            } else {
                format = "$hoursFormat $minutesFormat $secondsFormat"
            }

            return String.format(format, hours, minutes, seconds)
        }

        fun convertHumanReadableTimeToMillis(hms: String): Long {
            val hoursSegments = hms.split("h")
            val hours = hoursSegments[0].toLong() * 3_600_000

            val minutesSegments = hoursSegments[1].split("m")
            val minutes = minutesSegments[0].toLong() * 60_000

            val seconds = minutesSegments[1].toLong() * 1_000

            return hours + minutes + seconds
        }
    }
}