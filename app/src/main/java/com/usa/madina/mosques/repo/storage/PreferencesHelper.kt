package com.usa.madina.mosques.repo.storage

import android.content.Context
import com.google.gson.Gson
import com.usa.madina.mosques.ui.domain.UserDataModel
import androidx.core.content.edit

class PreferencesHelper(context: Context) {
    private val sharedPrefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveUserDataModel(userDataModel: UserDataModel) {
        val userJson = gson.toJson(userDataModel)
        sharedPrefs.edit { putString(USER_KEY, userJson) }
    }

    fun getUserDataModel(): UserDataModel? {
        val userJson = sharedPrefs.getString(USER_KEY, null)
        return if (userJson != null) {
            gson.fromJson(userJson, UserDataModel::class.java)
        } else {
            null
        }
    }

    companion object {
        private const val USER_KEY = "user_data_model"
    }
}