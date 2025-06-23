package com.usa.madina.mosques.repo.data

data class AuthenticateModel(
    val access_token: String,
    val expires_in: Int,
    val jti: String,
    val scope: String,
    val token_type: String
)