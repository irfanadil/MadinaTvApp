package com.usa.madina.mosques

object Utils {

    fun add15Minutes(timeStr: String): String {
        val parts = timeStr.split(" ")
        require(parts.size == 2) { "Invalid time format" }

        val timePart = parts[0]
        val period = parts[1]

        val timeComponents = timePart.split(":")
        require(timeComponents.size == 2) { "Invalid time format" }

        val hour = timeComponents[0].toInt()
        val minute = timeComponents[1].toInt()

        var hour24 = hour
        when {
            period == "PM" && hour != 12 -> hour24 += 12
            period == "AM" && hour == 12 -> hour24 = 0
        }

        val totalMinutes = hour24 * 60 + minute + 15
        val newTotalMinutes = totalMinutes % (24 * 60) // Wrap around if exceeding a day
        val newHour24 = newTotalMinutes / 60
        val newMinute = newTotalMinutes % 60

        val newPeriod = if (newHour24 < 12) "AM" else "PM"
        val displayHour = when (newHour24) {
            0 -> 12
            in 1..12 -> newHour24
            else -> newHour24 - 12
        }

        val formattedHour = "%02d".format(displayHour)
        val formattedMinute = "%02d".format(newMinute)

        return "$formattedHour:$formattedMinute $newPeriod"
    }
}