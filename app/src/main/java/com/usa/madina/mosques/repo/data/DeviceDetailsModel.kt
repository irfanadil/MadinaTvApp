package com.usa.madina.mosques.repo.data

data class DeviceDetailsModel(
    val appType: AppType,
    val appVersion: AppVersion,
    val configurations: Configurations,
    val deviceCode: String,
    val deviceId: String,
    val installationDate: String,
    val isActive: Boolean,
    val location: String,
    val slideshow: Slideshow,
    val versionUpdateDate: String
)

data class AppType(
    val appTypeCode: String,
    val appTypeId: String,
    val appTypeLabel: String,
    val isActive: Boolean
)

data class AppVersion(
    val appApkPath: String,
    val appType: AppType,
    val appVersionId: String,
    val description: String,
    val isActive: Boolean,
    val isDefault: Boolean,
    val version: String
)

data class Configurations(
    val IQAMAH_SLEEP_TIME: Int,
    val MD_BACKGROUND_IMAGE: String,
    val MD_CLOCK_TIME_COLOR: String,
    val MD_DISPLAY_MODE: String,
    val MD_DISPLAY_SLIDE_SHOW_TIMER: Int,
    val MD_FRIDAY_TIME_BACKGROUND_COLOR: String,
    val MD_FRIDAY_TIME_TEXT_COLOR: String,
    val MD_PRAYER_TIME_BACKGROUND_COLOR: String,
    val MD_PRAYER_TIME_SELECTED_BACKGROUND_COLOR: String,
    val MD_PRAYER_TIME_SELECTED_TEXT_COLOR: String,
    val MD_PRAYER_TIME_TEXT_COLOR: String,
    val MD_SHOW_ATHAN_TIME: Boolean,
    val MD_SHOW_IQAMAH_TIME: Boolean,
    val MD_VERTICAL_BACKGROUND_IMAGE: String,
    val QT_PAYMENT_OPTION: Any,
    val STRIPE_TERMINAL_ID: String
)

data class Slideshow(
    val isActive: Boolean,
    val slideShowName: String,
    val slideshowId: String
)