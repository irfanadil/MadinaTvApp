package com.usa.madina.mosques.repo

import android.util.Base64
import android.util.Log
import com.usa.madina.mosques.repo.network.ApiResponse
import com.usa.madina.mosques.repo.network.ServiceApi
import com.usa.madina.mosques.repo.storage.PreferencesHelper
import com.usa.madina.mosques.ui.domain.UserDataModel
import javax.inject.Inject

class UserDetailRepo @Inject constructor(private val serviceApi: ServiceApi, private val preferencesHelper: PreferencesHelper) {

    private fun getBasicAuthHeader(username: String, password: String): String {
        //val credentials = "$username:$password"
        val credentials = "$username:$password".toByteArray()
            .let { Base64.encodeToString(it, Base64.NO_WRAP) }
        return "Basic $credentials"
    }

    suspend fun getUserDetail(userName:String, password:String, appType: String): ApiResponse<UserDataModel>  {

        preferencesHelper.getUserDataModel()?.let {
            return ApiResponse.Success(it)
        }
        try {
            //val authResponse = serviceApi.getAuthenticate(getBasicAuthHeader(userName, password))
            //Log.e("UserData =",authResponse.toString())
            val deviceDetailResponse = serviceApi.getDeviceDetails( passKey = "MASJID_DISPLAY" )
            val clientConfigurationResponse = serviceApi.getClientConfigurations()
            val userDataModel = UserDataModel(null, deviceDetailResponse, clientConfigurationResponse)
            preferencesHelper.saveUserDataModel(userDataModel) // Save to SharedPreferences
            return ApiResponse.Success(userDataModel)
        }
        catch (e: Exception){
            return ApiResponse.Error(e.toString())
        }
    }


}