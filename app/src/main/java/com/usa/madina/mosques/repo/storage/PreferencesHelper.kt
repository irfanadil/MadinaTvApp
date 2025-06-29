package com.usa.madina.mosques.repo.storage

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.usa.madina.mosques.ui.domain.UserDataModel
import androidx.core.content.edit
import com.usa.madina.mosques.repo.TestDataClass
import com.usa.madina.mosques.repo.data.Country
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PreferencesHelper(context: Context) {
    val sharedPrefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveUserDataModel(userDataModel: UserDataModel) {
        try {
             val json = Json {
                coerceInputValues = true // Auto-fill defaults for nulls
                ignoreUnknownKeys = false // Throw on missing fields
            }
            val userJson = json.encodeToString(userDataModel)
            Log.e("userJson", userJson.toString())
            sharedPrefs.edit { putString(USER_KEY, userJson) }
        } catch (e: SerializationException) {
            e.printStackTrace()
        } catch (e: Exception) {
            println("General error: ${e.message}")
        }
    }

    fun getUserDataModel(): UserDataModel? {
        val userJson = sharedPrefs.getString(USER_KEY, null)
        return if (userJson != null) {
            val decodedUserDataModel: UserDataModel = Json.decodeFromString<UserDataModel>(userJson)
            return decodedUserDataModel
        } else {
            null
        }
    }




    companion object {
        private const val USER_KEY = "user_data_model"
    }


    /*
    //Serialize using Gson and reflection and can not handle null values or sealed class

    fun saveUserDataModel(userDataModel: UserDataModel) {
        val userJson = gson.toJson(userDataModel)
        sharedPrefs.edit { putString(USER_KEY, userJson) }
        Log.e("TAFG", userDataModel.toString())
        Log.e("TAFG", userJson)
    }

    fun getUserDataModel(): UserDataModel? {
        val userJson = sharedPrefs.getString(USER_KEY, null)
        return if (userJson != null) {
            gson.fromJson(userJson, UserDataModel::class.java)
        } else {
            null
        }
    }

     */

}