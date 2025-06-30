package com.usa.madina.mosques.ui
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usa.madina.mosques.repo.PrayerRepo
import com.usa.madina.mosques.repo.UserDetailRepo
import com.usa.madina.mosques.repo.data.PrayerTimingModel
import com.usa.madina.mosques.repo.network.ApiResponse
import com.usa.madina.mosques.ui.domain.UserDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class IncomingResponse() {
    SUCCESS_FROM_USER_DATA,
    SUCCESS_FROM_PRAYER_DATA,
    SUCCESS_FROM_SLIDESHOW,
    FAIL_FROM_USER_DATA,
    FAIL_FROM_PRAYER_DATA,
    FAIL_FROM_SLIDESHOW
}

@HiltViewModel
class MainViewModel  @Inject constructor(
    val userRepo: UserDetailRepo,
    val prayerRepo: PrayerRepo

) : ViewModel(){

    private val _userDetailReceived = MutableStateFlow<Boolean>(false)
    val userDetailReceived = _userDetailReceived.asStateFlow()

    private val _prayerApiResponse = MutableStateFlow<ApiResponse<PrayerTimingModel>>(ApiResponse.Loading)
    val prayerApiResponse = _prayerApiResponse.asStateFlow()

    lateinit var userDataModel: UserDataModel

    fun authenticateUser(userName:String, password:String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = userRepo.getUserDetail(userName, password, "MASJID_DISPLAY")
            when(result){
                is ApiResponse.Error -> {

                }
                ApiResponse.Loading -> {

                }
                is ApiResponse.Success -> {
                    _userDetailReceived.value = true
                    userDataModel = result.data
                }
            }
        }
    }


    fun getPrayersData(){
        viewModelScope.launch(Dispatchers.IO) {
            _prayerApiResponse.value = prayerRepo.getAllPrayerTimes()
        }
    }


}