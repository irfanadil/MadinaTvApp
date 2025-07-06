package com.usa.madina.mosques.repo.data

import android.util.Log
import kotlinx.serialization.Serializable

@Serializable
data class ClientConfigurationModel(
    val alias: String,
    val audit: Audit,
    val city: String,
    val clientId: String,
    val code: String,
    val country: Country,
    val id: Int,
    val joinedTime: String,
    val logoUrl: String,
    val name: String,
    val registrationType: String,
    val state: State,
    val status: String,
    val street: String,
    val timeZone: String,
    val timeZoneOffset: Int,
    val updatedTime: String,
    val website: String,
    val zipcode: String,
    val myChoice: String = "some my way"
)

@Serializable
data class Audit(
    val createdDate: String,
    val lastModifiedDate: String
)



@Serializable
data class Country(
    val code: String,
    val name: String,
    val shortCode: String
)

@Serializable
data class State(
    val code: String,
    val name: String
)