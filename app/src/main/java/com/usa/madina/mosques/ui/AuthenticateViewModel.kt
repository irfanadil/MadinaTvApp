package com.usa.madina.mosques.ui
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usa.madina.mosques.repo.AuthenticateRepo
import com.usa.madina.mosques.repo.network.ApiResponse
import com.usa.madina.mosques.ui.domain.UserDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticateViewModel  @Inject constructor(val repo: AuthenticateRepo) : ViewModel(){

    private val _userDetailMutableState = MutableStateFlow<UserDataModel>(UserDataModel(null, null, null))
    val userDetailStateFlow = _userDetailMutableState.asStateFlow()

    fun authenticateUser(userName:String, password:String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.getUserDetail(userName, password, "MASJID_DISPLAY")
            when(result){
                is ApiResponse.Error -> {

                }
                ApiResponse.Loading -> {

                }
                is ApiResponse.Success -> {
                    _userDetailMutableState.value = result.data
                }
            }

        }
    }





}