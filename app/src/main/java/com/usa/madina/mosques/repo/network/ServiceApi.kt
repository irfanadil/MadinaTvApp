package com.usa.madina.mosques.repo.network

import com.usa.madina.mosques.repo.data.AuthenticateModel
import com.usa.madina.mosques.repo.data.ClientConfigurationModel
import com.usa.madina.mosques.repo.data.DeviceDetailsModel
import com.usa.madina.mosques.repo.data.PrayerTimingModel
import com.usa.madina.mosques.repo.data.SlidesModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ServiceApi {

    @POST("oauth/token")
    suspend fun getAuthenticate(@Header("x-login-app-type") passKey: String): AuthenticateModel


    @POST("oauth/token")
    suspend fun getAuthenticateWithParam(@Header("Authorization") authHeader: String, @Header("x-login-app-type") passKey: String): AuthenticateModel

    @GET("v1/client/devices/123456")
    suspend fun getDeviceDetails(@Header("x-login-app-type") passKey: String): DeviceDetailsModel

    @GET("v1/client/demo")
    suspend fun getClientConfigurations(): ClientConfigurationModel

    @GET("v1/client/prayerTimes")
    suspend fun getPrayerTimes(): PrayerTimingModel

    @GET("v1/client/devices/123456/slideshow")
    suspend fun getSlidesShow(): SlidesModel

}