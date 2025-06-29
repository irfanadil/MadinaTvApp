package com.usa.madina.mosques.ui
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usa.madina.mosques.repo.AuthenticateRepo
import com.usa.madina.mosques.repo.network.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticateViewModel  @Inject constructor(val repo: AuthenticateRepo) : ViewModel(){

    private val _userDetailReceived = MutableStateFlow<Boolean>(false)
    val userDetailReceived = _userDetailReceived.asStateFlow()

    fun authenticateUser(userName:String, password:String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.getUserDetail(userName, password, "MASJID_DISPLAY")
            when(result){
                is ApiResponse.Error -> {

                }
                ApiResponse.Loading -> {

                }
                is ApiResponse.Success -> {
                    _userDetailReceived.value = result.data
                }
            }

        }
    }





}