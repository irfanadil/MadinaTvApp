package com.usa.madina.mosques.repo

import android.util.Log
import com.usa.madina.mosques.repo.data.AuthenticateModel
import com.usa.madina.mosques.repo.data.ClientConfigurationModel
import com.usa.madina.mosques.repo.data.DeviceDetailsModel
import com.usa.madina.mosques.repo.network.ApiResponse
import com.usa.madina.mosques.repo.network.ServiceApi
import javax.inject.Inject

class AuthenticateRepo @Inject constructor(val serviceApi: ServiceApi) {

    fun getBasicAuthHeader(username: String, password: String): String {
        val credentials = "$username:$password"
        //val credentials = "$username:$password".toByteArray()
          //  .let { Base64.encodeToString(it, Base64.NO_WRAP) }
        return "Basic $credentials"
    }

    suspend fun getAuthenticate(userName:String, password:String, appType: String): ApiResponse<AuthenticateModel>  {
        try {
            val authHeader = getBasicAuthHeader(userName,password)
           // val response = serviceApi.getAuthenticate(authHeader)
            return ApiResponse.Success(AuthenticateModel(
                access_token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJ0cnVzdCIsInJlYWQiLCJ3cml0ZSJdLCJleHAiOjE3NTEyNTI2NDAsImF1dGhvcml0aWVzIjpbIlJPTEVfU0VTU0lPTl9JRF9lYzMxN2Y1MS00ZmIzLTQ5NDEtYjlmZi00MjVlY2VmNmQ0YTgiLCJST0xFX0NMSUVOVCIsIlJPTEVfQ0xJRU5UX0lEX2E5M2JiODFhLTMyOGEtMTFlZC1iYTFjLTBhNTMwOWZlYmVmZSJdLCJqdGkiOiJzNnFraUJRTkpFZnBVVzMwaEFCem5UU1pnUU0iLCJjbGllbnRfaWQiOiJkZW1vIn0.pc9gOxw43ZMHTEFlJ21Vv-RUrqwf1c1p1G7F0LiAnuIGqLSS7bhoErjpgXQhewVqBUrpVKYqSKt1hXGtXX0Oq6sBsA6blqhpAKQ74tiN_T9FR_RT9N4HDmGJ3OcQbLRXBzCvMkAK008e86ixd78GaS9kF6C0VJP5j2GaCRkuqMe-cD6BDrHd14fPkeBzbtVtjmpHsnBBstR6M0zT-vQbq2ujBgzeRhSpz6JoHN5wsgSwOe28aWHAWOVSYpjrpKRNvXEHtVD0qgt-WVtZl7QT3jp3DZ4xkIeNwPG-Z_PpDqUjcQCwPmNmDvvBejPwyR2fzULhDsPVQAHQJ2tqtRAy0w",
                token_type =  "bearer",
                jti = "s6qkiBQNJEfpUW30hABznTSZgQM",
                scope = "trust read write",
                expires_in = 604799)
            )
        }
        catch (e: Exception){
            return ApiResponse.Error(e.toString())
        }
    }

    suspend fun getDeviceDetails(passKey: String): ApiResponse<DeviceDetailsModel>  {
        try {
            val response = serviceApi.getDeviceDetails( passKey = "MASJID_DISPLAY" )
            Log.e("response ",response.toString())
            return ApiResponse.Success(response)
        }
        catch (e: Exception){
            Log.e("response ",e.toString())
            return ApiResponse.Error(e.toString())
        }
    }

    suspend fun getClientConfigurations(userName:String, password:String, appType: String): ApiResponse<ClientConfigurationModel>  {
        try {
            val response = serviceApi.getClientConfigurations()
            return ApiResponse.Success(response)
        }
        catch (e: Exception){
            return ApiResponse.Error(e.toString())
        }
    }

}