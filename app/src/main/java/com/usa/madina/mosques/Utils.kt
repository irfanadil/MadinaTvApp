package com.usa.madina.mosques

import android.graphics.drawable.Drawable
import android.view.View
import java.util.Calendar

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


    fun timeToMinutes(timeStr: String): Int {
        val parts = timeStr.split(" ")
        require(parts.size == 2) { "Invalid time format: $timeStr" }
        val (timePart, period) = parts
        val timeComponents = timePart.split(":")
        require(timeComponents.size == 2) { "Invalid time format: $timeStr" }
        val hour = timeComponents[0].toInt()
        val minute = timeComponents[1].toInt()

        return when {
            period == "AM" && hour == 12 -> 0 * 60 + minute
            period == "PM" && hour == 12 -> 12 * 60 + minute
            period == "PM" -> (hour + 12) * 60 + minute
            else -> hour * 60 + minute
        }
    }

    // Function to convert minutes back to time string
    fun minutesToTime(minutes: Int): String {
        val normalizedMinutes = minutes % 1440
        val hour24 = normalizedMinutes / 60
        val minute = normalizedMinutes % 60
        val period = if (hour24 < 12) "AM" else "PM"
        val hour12 = when (hour24) {
            0 -> 12
            in 1..12 -> hour24
            else -> hour24 - 12
        }
        return String.format("%02d:%02d %s", hour12, minute, period)
    }

    // Function to find next upcoming time from list
    fun nextUpcomingTime(times: List<String>, currentTime: String): String {
        require(times.isNotEmpty()) { "times list must not be empty" }
        val currentMinutes = timeToMinutes(currentTime)
        val timeMinutes = times.map { timeToMinutes(it) }.sorted()
        val nextTimeMinute = timeMinutes.firstOrNull { it >= currentMinutes } ?: timeMinutes[0]
        return minutesToTime(nextTimeMinute)
    }

    // Function to get current time in "hh:mm AM/PM" format
    fun getCurrentTimeString(): String {
        val calendar = Calendar.getInstance()
        val hour12 = calendar.get(Calendar.HOUR) // 0-11 format (12-hour clock)
        val minute = calendar.get(Calendar.MINUTE)
        val amPm = calendar.get(Calendar.AM_PM)

        // Convert 0 to 12 for proper display
        val displayHour = if (hour12 == 0) 12 else hour12
        val period = if (amPm == Calendar.AM) "AM" else "PM"

        return String.format("%02d:%02d %s", displayHour, minute, period)
    }

    // Example usage in Android
    fun findNextUpcomingTime(times: List<String>): String {
        val currentTime = getCurrentTimeString()
        return nextUpcomingTime(times, currentTime)
    }


    fun convertAmToPm(timeString: String): String {
        val parts = timeString.split(' ')
        if (parts.size != 2) return timeString
        val period = parts[1]
        if (period.length != 2) return timeString
        if (!period.equals("AM", ignoreCase = true)) return timeString

        val newFirstChar = if (period[0] == 'A') 'P' else 'p'
        val newPeriod = "$newFirstChar${period[1]}"
        return "${parts[0]} $newPeriod"
    }







}



// Save original background in view's tag
fun View.saveOriginalBackground() {
    setTag(R.id.original_background_tag, background)
}

fun View.hideBackground() {
    if (getTag(R.id.original_background_tag) == null) {
        saveOriginalBackground()
    }
    background = null
}

fun View.showBackground() {
    (getTag(R.id.original_background_tag) as? Drawable)?.let {
        background = it
    }
}