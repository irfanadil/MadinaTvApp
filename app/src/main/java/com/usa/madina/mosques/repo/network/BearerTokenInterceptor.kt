package com.usa.madina.mosques.repo.network

import okhttp3.Interceptor
import okhttp3.Response

class BearerTokenInterceptor(
    private val token: String // Token supplier lambda
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = token
        val request = chain.request().newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}