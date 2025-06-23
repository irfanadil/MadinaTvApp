package com.usa.madina.mosques.ui
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usa.madina.mosques.repo.AuthenticateRepo
import com.usa.madina.mosques.repo.TestAuthenticateRepo
import com.usa.madina.mosques.repo.network.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticateViewModel  @Inject constructor(val repo: AuthenticateRepo) : ViewModel(){


    fun authenticateUser(userName:String, password:String){
        viewModelScope.launch(Dispatchers.IO){
           val result = repo.getAuthenticate(userName, password , "MASJID_DISPLAY")
            when(result){
                is ApiResponse.Error -> {

                }
                ApiResponse.Loading -> {

                }
                is ApiResponse.Success -> {

                }
            }


            val resultTwo = repo.getDeviceDetails("asdf")

        }


    }

    fun getDeviceDetails(userName:String, password:String){
        viewModelScope.launch(Dispatchers.IO){

        }
    }


    fun getClientConfigurations(userName:String, password:String){
        viewModelScope.launch(Dispatchers.IO){

        }
    }



}