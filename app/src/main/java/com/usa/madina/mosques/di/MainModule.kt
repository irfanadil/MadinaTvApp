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
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.jvm.java
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLSession

@Module
@InstallIn(SingletonComponent::class)
class MainModule {



    /*@Provides
    @Singleton
    fun buildRetrofit(): Retrofit{

        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        })

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())


        val myRetrofit = Retrofit
            .Builder()
            .baseUrl("https://api-gateway.madinaapps.com/")
            .client(OkHttpClient.Builder()
                .hostnameVerifier { hostname: String, session: SSLSession -> true }
                .sslSocketFactory(
                    sslContext.socketFactory,
                    trustAllCerts[0] as X509TrustManager
                )
                .addInterceptor(BearerTokenInterceptor("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJ0cnVzdCIsInJlYWQiLCJ3cml0ZSJdLCJleHAiOjE3NTE3NTA2MDUsImF1dGhvcml0aWVzIjpbIlJPTEVfU0VTU0lPTl9JRF8xNjA2ZDRiMC03NjVjLTRmNjUtYTA5NS02YjI4YWNlZDk4N2EiLCJST0xFX0NMSUVOVCIsIlJPTEVfQ0xJRU5UX0lEX2E5M2JiODFhLTMyOGEtMTFlZC1iYTFjLTBhNTMwOWZlYmVmZSJdLCJqdGkiOiJfaVhxVk9sV05id0VBbUNTX2w0bHVvQXlaQ00iLCJjbGllbnRfaWQiOiJkZW1vIn0.pU9Sgt1UpRQLZoxV2GZNsi9g0pubZ6GejCqhQYGrXsHJ8JMPGaYlzjyQXVROffmt_NNwRVtNsOyIjs71k5-v3ek4ajpO9hGcaP8z19a5ECVV2xyn0fNBPnkqdQY6F7V5OVLG2524aCUiif-VqEendz3meSz9-PeveU1gnH0FNpqhcOXDs9phuBNmd846eISSsVA9hiDHtA191KffAb11sRi8kh9un2S2eYDqKWtRd8O3FyLxgvTjVC9kngecq8KlEZDBNfSewhxIkvgJXWEPlBE3Qbi1x6iln4iAexq9G7vnfXS1OpmVSw78VWFq8A2U1rCs7aCfBbuSxs30gBeJmg"))
                .build())
            .addConverterFactory(
                GsonConverterFactory
                    .create(
                        GsonBuilder().serializeNulls().enableComplexMapKeySerialization().create()
                    )
            )
            .build()

        return myRetrofit
    }*/



    @Provides
    @Singleton
    fun getClient(): ServiceApi{


        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        })

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())


        val myRetrofit = Retrofit
            .Builder()
            .baseUrl("https://api-gateway.madinaapps.com/")
            .client(OkHttpClient.Builder()
                .hostnameVerifier { hostname: String, session: SSLSession -> true }
                .sslSocketFactory(
                    sslContext.socketFactory,
                    trustAllCerts[0] as X509TrustManager
                )
                .addInterceptor(BearerTokenInterceptor("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJ0cnVzdCIsInJlYWQiLCJ3cml0ZSJdLCJleHAiOjE3NTE3NTA2MDUsImF1dGhvcml0aWVzIjpbIlJPTEVfU0VTU0lPTl9JRF8xNjA2ZDRiMC03NjVjLTRmNjUtYTA5NS02YjI4YWNlZDk4N2EiLCJST0xFX0NMSUVOVCIsIlJPTEVfQ0xJRU5UX0lEX2E5M2JiODFhLTMyOGEtMTFlZC1iYTFjLTBhNTMwOWZlYmVmZSJdLCJqdGkiOiJfaVhxVk9sV05id0VBbUNTX2w0bHVvQXlaQ00iLCJjbGllbnRfaWQiOiJkZW1vIn0.pU9Sgt1UpRQLZoxV2GZNsi9g0pubZ6GejCqhQYGrXsHJ8JMPGaYlzjyQXVROffmt_NNwRVtNsOyIjs71k5-v3ek4ajpO9hGcaP8z19a5ECVV2xyn0fNBPnkqdQY6F7V5OVLG2524aCUiif-VqEendz3meSz9-PeveU1gnH0FNpqhcOXDs9phuBNmd846eISSsVA9hiDHtA191KffAb11sRi8kh9un2S2eYDqKWtRd8O3FyLxgvTjVC9kngecq8KlEZDBNfSewhxIkvgJXWEPlBE3Qbi1x6iln4iAexq9G7vnfXS1OpmVSw78VWFq8A2U1rCs7aCfBbuSxs30gBeJmg"))
                .build())
            .addConverterFactory(
                GsonConverterFactory
                    .create(
                        GsonBuilder().serializeNulls().enableComplexMapKeySerialization().create()
                    )
            )
            .build()

        return myRetrofit.create(ServiceApi::class.java)
    }

    @Provides
    @Singleton
    fun getPreferencesHelper(@ApplicationContext context: Context ): PreferencesHelper{
        return PreferencesHelper(context)
    }


    /*



    @Provides
    @Singleton
    fun provideSSLCertificate(trustAllCerts:Array<TrustManager> ): SSLSocketFactory{


    }


    @Provides
    @Singleton
    fun provideSSLCertificate(): Array<TrustManager>{

        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        })

        return trustAllCerts
    }

     */



}