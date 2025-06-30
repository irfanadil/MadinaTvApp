package com.usa.madina.mosques.repo.data

data class PrayerTimingModel(
    val prayerTimes: List<PrayerTime> = listOf(),
    val settings: Settings?= null
)


data class PrayerTime(
    val asr: Asr,
    val date: String,
    val day: String,
    val dhuhr: Dhuhr,
    val displayDate: String,
    val fajr: Fajr,
    val hijriDate: String,
    val isha: Isha,
    val juma: Juma,
    val jumaTimes: List<JumaTime>,
    val maghrib: Maghrib,
    val prayerTimesDailyId: String,
    val sunrise: String
)

data class Fajr(
    val adhaanTime: String,
    val iqamahTime: String,
    val notes: Any
)

data class Dhuhr(
    val adhaanTime: String,
    val iqamahTime: String,
    val notes: Any
)

data class Asr(
    val adhaanTime: String,
    val iqamahTime: String,
    val notes: Any
)

data class Maghrib(
    val adhaanTime: String,
    val iqamahTime: String,
    val notes: Any
)

data class Isha(
    val adhaanTime: String,
    val iqamahTime: String,
    val notes: Any
)

data class Juma(
    val juma1IqamahTime: String,
    val juma1KhutbaTime: String,
    val juma1Notes: Any,
    val juma2IqamahTime: String,
    val juma2KhutbaTime: String,
    val juma2Notes: Any,
    val juma3IqamahTime: String,
    val juma3KhutbaTime: String,
    val juma3Notes: Any
)

data class JumaTime(
    val iqamahTime: String,
    val khateeb: Khateeb,
    val khutbaTags: List<Any>,
    val khutbaTime: String,
    val khutbaTitle: Any,
    val locationLink: Any,
    val notes: Any,
    val prayerTimesFridayId: String,
    val title: String
)

data class Khateeb(
    val bio: String,
    val isActive: Boolean,
    val khateebId: String,
    val name: String,
    val profilePhotoPath: String
)

data class Settings(
    val adhaanLabel: String,
    val asrLabel: String,
    val dhuhrLabel: String,
    val fajrLabel: String,
    val hijriDateOffset: Int,
    val iqamahLabel: String,
    val ishaLabel: String,
    val jumaLabel: String,
    val maghribLabel: String,
    val prayerTimeFormat: String,
    val showHijriDate: Boolean,
    val sunriseLabel: String,
    val timeZone: String
)