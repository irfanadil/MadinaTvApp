package com.usa.madina.mosques.repo

import android.util.Base64
import android.util.Log
import com.usa.madina.mosques.repo.data.AppType
import com.usa.madina.mosques.repo.data.AuthenticateModel
import com.usa.madina.mosques.repo.data.ClientConfigurationModel
import com.usa.madina.mosques.repo.data.DeviceDetailsModel
import com.usa.madina.mosques.repo.network.ApiResponse
import com.usa.madina.mosques.repo.network.ServiceApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class BasicAuthInterceptor(
    private val username: String,
    private val password: String
) : Interceptor {




    override fun intercept(chain: Interceptor.Chain): Response {
        // Handle special characters
        val credentials = "$username:$password"
            //.toByteArray(Charsets.UTF_8)
            //.let { Base64.encodeToString(it, Base64.NO_WRAP) }

        val request = chain.request().newBuilder()
            .header("Authorization", "Basic $credentials")
            .build()

        return chain.proceed(request)
    }
}

// 2. Retrofit setup with logging
fun createRetrofit(username: String, password: String): Retrofit {

    val client = OkHttpClient.Builder()
        .addInterceptor(BasicAuthInterceptor(username, password))
        .addInterceptor(logger)
        .build()

    return Retrofit.Builder()
        .baseUrl("https://api-gateway.madinaapps.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}


val logger = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

val passwordArray = charArrayOf('h','P','y','2','2','5','H','$','e','#','S','f','f','X','L','3')



/*
class BasicAuthInterceptorTwo(
    private val username: String,
    private val password: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", Credentials.basic(username, password))
            .build()
        return chain.proceed(request)
    }
}

//val password = "hPy225H\$e#SffXL3"

val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(
        BasicAuthInterceptorTwo(
            username = "demo",
            password = String(passwordArray) // Raw string handles special chars
        )
    )
    .addInterceptor(logger)
    .build()

val retrofitGlobal = Retrofit.Builder()
    .baseUrl("https://api-gateway.madinaapps.com/")
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

 */

class TestAuthenticateRepo @Inject constructor(val serviceApi: ServiceApi) {

    suspend fun getAuthenticate(userName:String, password:String, appType: String)  {

        /*
        val passwordArray = charArrayOf('h','P','y','2','2','5','H','$','e','#','S','f','f','X','L','3')
        val retrofit = createRetrofit("demo", String(passwordArray))
        val service = retrofit.create(ServiceApi::class.java)
        try {
            val response = service.getAuthenticate(appType)
            Log.e("response ",response.toString())
        } catch (e: HttpException) {
            if (e.code() == 401) {
                Log.e("TAG","authentication-error")
                // Still failing - check logs for header value
            }
        }

         */


        val retrofit = createRetrofit("demo", String(passwordArray))
        Log.e("TAG", "password = "+String(passwordArray))
        val service = retrofit.create(ServiceApi::class.java)
        //val serviceTwo = retrofitGlobal.create(ServiceApi::class.java)
        try {
           val authHeader = Credentials.basic("demo", String(passwordArray))
            val response = service.getAuthenticate( passKey = "MASJID_DISPLAY")
            Log.e("response ",response.toString())
        } catch (e: HttpException) {
            if (e.code() == 401) {
                Log.e("TAG","authentication-error")
                // Still failing - check logs for header value
            }
        }


    }



}