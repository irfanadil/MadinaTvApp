package com.usa.madina.mosques.repo

import com.usa.madina.mosques.repo.data.PrayerTimingModel
import com.usa.madina.mosques.repo.network.ApiResponse
import com.usa.madina.mosques.repo.network.ServiceApi
import com.usa.madina.mosques.repo.storage.PreferencesHelper
import javax.inject.Inject

class PrayerRepo @Inject constructor(private val serviceApi: ServiceApi , private val preferencesHelper: PreferencesHelper) {

    suspend fun getAllPrayerTimes(): ApiResponse<PrayerTimingModel>  {
        try {
            val prayerTimesResponse = serviceApi.getPrayerTimes()
            return ApiResponse.Success(prayerTimesResponse)
        }
        catch (e: Exception){
            return ApiResponse.Error(e.toString())
        }
    }


}
