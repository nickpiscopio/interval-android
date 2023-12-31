package com.plyonest.interval.utils

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
}

data class TwoCharString(
    val previousFirstCharacter: String,
    val str: String
)

class TimeUtil {
    companion object {
        fun convertMillisToHoursMinutesSeconds(millis: Long): HoursMinutesSeconds {
            val hours = TimeUnit.MILLISECONDS.toHours(millis)
            val minutes = TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis))
            val seconds = TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))

            return HoursMinutesSeconds(hours.toString(), minutes.toString(), seconds.toString())
        }

        fun convertHoursMinutesSecondsToMillis(hours: String, minutes: String, seconds: String): Long {
            val (firstElementFromSeconds, tempSeconds) = retrieveFirstElement(seconds)
            var tempMinutes = minutes
            var tempHours = hours

            if (firstElementFromSeconds != "0") {
                tempMinutes += firstElementFromSeconds
                val twoCharArrayFromMinutes = retrieveFirstElement(tempMinutes)
                tempMinutes = twoCharArrayFromMinutes.str

                val firstElementFromMinutes = twoCharArrayFromMinutes.previousFirstCharacter
                if (firstElementFromMinutes != "0") {
                    tempHours += firstElementFromSeconds
                }
            }
            val newHours = tempHours.toLong() * 3_600_000
            val newMinutes = tempMinutes.toLong() * 60_000
            val newSeconds = tempSeconds.toLong() * 1_000

            return newHours + newMinutes + newSeconds
        }

        private fun retrieveFirstElement(str: String): TwoCharString {
            var firstElement = "0"
            var tempStr = str
            if (tempStr.length < 2) {
                tempStr = firstElement + str
            }
            var charArr = tempStr.toCharArray()
            if (charArr.size > 2) {
                firstElement = charArr[0].toString()
                charArr = charArr.drop(1).toCharArray()
            }

            return TwoCharString(firstElement, StringBuilder().append(charArr).toString())
        }

        fun convertHoursMinutesSecondsToMillis(hms: HoursMinutesSeconds): Long {
            val hours = hms.getHours().toLong() * 3_600_000
            val minutes = hms.getMinutes().toLong() * 60_000
            val seconds = hms.getSeconds().toLong() * 1_000

            return hours + minutes + seconds
        }

//        fun convertMillisToHumanReadableTime(millis: Long, withPrecedingZeroes: Boolean = false): String {
//            val hours = TimeUnit.MILLISECONDS.toHours(millis)
//            val minutes = TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis))
//            val seconds = TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
//
//            val secondsFormat = "%02ds"
//            val minutesFormat = "%02dm"
//            val hoursFormat = "%02dh"
//            var format = secondsFormat
//            if (!withPrecedingZeroes) {
//                if (minutes > 0) {
//                    format = "$minutesFormat $secondsFormat"
//                } else if (hours > 0) {
//                    format = "$hoursFormat $minutesFormat $secondsFormat"
//                }
//            } else {
//                format = "$hoursFormat $minutesFormat $secondsFormat"
//            }
//
//            return String.format(format, hours, minutes, seconds)
//        }
//
//        fun convertHumanReadableTimeToMillis(hms: String): Long {
//            val hoursSegments = hms.split("h")
//            val hours = hoursSegments[0].toLong() * 3_600_000
//
//            val minutesSegments = hoursSegments[1].split("m")
//            val minutes = minutesSegments[0].toLong() * 60_000
//
//            val seconds = minutesSegments[1].toLong() * 1_000
//
//            return hours + minutes + seconds
//        }
    }
}