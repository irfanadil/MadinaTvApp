package com.usa.madina.mosques.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.usa.madina.mosques.repo.network.BearerTokenInterceptor
import com.usa.madina.mosques.repo.network.ServiceApi
import com.usa.madina.mosques.repo.storage.PreferencesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Provides
    @Singleton
    fun buildRetrofit(): Retrofit{
        return Retrofit
            .Builder()
            .baseUrl("https://api-gateway.madinaapps.com/")
            .client(OkHttpClient.Builder()
                .addInterceptor(BearerTokenInterceptor("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJ0cnVzdCIsInJlYWQiLCJ3cml0ZSJdLCJleHAiOjE3NTEyNTI2NDAsImF1dGhvcml0aWVzIjpbIlJPTEVfU0VTU0lPTl9JRF9lYzMxN2Y1MS00ZmIzLTQ5NDEtYjlmZi00MjVlY2VmNmQ0YTgiLCJST0xFX0NMSUVOVCIsIlJPTEVfQ0xJRU5UX0lEX2E5M2JiODFhLTMyOGEtMTFlZC1iYTFjLTBhNTMwOWZlYmVmZSJdLCJqdGkiOiJzNnFraUJRTkpFZnBVVzMwaEFCem5UU1pnUU0iLCJjbGllbnRfaWQiOiJkZW1vIn0.pc9gOxw43ZMHTEFlJ21Vv-RUrqwf1c1p1G7F0LiAnuIGqLSS7bhoErjpgXQhewVqBUrpVKYqSKt1hXGtXX0Oq6sBsA6blqhpAKQ74tiN_T9FR_RT9N4HDmGJ3OcQbLRXBzCvMkAK008e86ixd78GaS9kF6C0VJP5j2GaCRkuqMe-cD6BDrHd14fPkeBzbtVtjmpHsnBBstR6M0zT-vQbq2ujBgzeRhSpz6JoHN5wsgSwOe28aWHAWOVSYpjrpKRNvXEHtVD0qgt-WVtZl7QT3jp3DZ4xkIeNwPG-Z_PpDqUjcQCwPmNmDvvBejPwyR2fzULhDsPVQAHQJ2tqtRAy0w"))
                .build())
            .addConverterFactory(
                GsonConverterFactory
                    .create(
                        GsonBuilder().serializeNulls().enableComplexMapKeySerialization().create()
                    ))
            .build()
    }

    @Provides
    @Singleton
    fun getClient(retrofit: Retrofit): ServiceApi{
        return retrofit.create(ServiceApi::class.java)
    }

    @Provides
    @Singleton
    fun getPreferencesHelper(@ApplicationContext context: Context ): PreferencesHelper{
        return PreferencesHelper(context)
    }

}