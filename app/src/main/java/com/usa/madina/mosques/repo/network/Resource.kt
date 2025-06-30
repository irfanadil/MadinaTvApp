package com.usa.madina.mosques.repo.network

sealed class Resource<out T>(
    val data: T? = null,
    val message: String = "",
    val status: NetworkStatus,
    val error: ApiError? = null,
    val responseCode: String? = null,
) {
    class Success<out T>(
        data: T,
        message: String = "Request Successful",
    ) : Resource<T>(data, message, NetworkStatus.SUCCESS)

    class Loading<out T>(
        data: T? = null,
        message: String = "Loading...",
    ) : Resource<T>(data, message, NetworkStatus.LOADING)

    class Failure<out T>(
        error: ApiError,
        data: T? = null,
    ) : Resource<T>(data, error.message, NetworkStatus.FAILED, error, error.responseCode)
}

data class ApiError(
    val code: Int,
    val message: String,
    val isNetworkError: Boolean = true,
    val responseCode: String? = null,
)

enum class NetworkStatus(
    val value: String,
) {
    LOADING("loading"),
    SUCCESS("success"),
    FAILED("failed"),
}