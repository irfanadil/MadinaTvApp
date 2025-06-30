package com.usa.madina.mosques.ui.prayers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.usa.madina.mosques.Utils
import com.usa.madina.mosques.databinding.FragmentPrayerBinding
import com.usa.madina.mosques.repo.data.PrayerTimingModel
import com.usa.madina.mosques.repo.network.ApiResponse
import com.usa.madina.mosques.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class PrayerTimesFragment: Fragment() {

    private var _binding: FragmentPrayerBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        observePrayerResponse()
        loadData()
    }

    private fun loadData(){
        viewModel.getPrayersData()
    }

    private fun setupListeners(){

    }

    private fun observePrayerResponse(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.prayerApiResponse.collect{ result->
                    when(result){
                        is ApiResponse.Error -> {

                        }
                        ApiResponse.Loading -> {

                        }
                        is ApiResponse.Success -> {
                            displayPrayerData(result.data)
                        }
                    }
                }
            }
        }
    }

    private fun displayPrayerData(model: PrayerTimingModel){

        val prayerData = model.prayerTimes[0]
        binding.fajrStarts.text = prayerData.fajr.adhaanTime
        binding.fajrIqamah.text = prayerData.fajr.iqamahTime

        binding.dhuhrStarts.text = prayerData.dhuhr.adhaanTime
        binding.dhuhrIqamah.text = prayerData.dhuhr.iqamahTime

        binding.asrStarts.text = prayerData.asr.adhaanTime
        binding.asrIqamah.text = prayerData.asr.iqamahTime

        binding.maghribStarts.text = prayerData.maghrib.adhaanTime
        binding.maghribIqamah.text = prayerData.maghrib.iqamahTime

        binding.ishaStarts.text = prayerData.isha.adhaanTime
        binding.ishaIqamah.text = prayerData.isha.iqamahTime

        binding.todayDate.text = prayerData.date
        binding.todayIslamicDate.text = prayerData.hijriDate

        binding.sunriseTime.text = "SUNRISE  "+prayerData.sunrise
        binding.ishraqTime.text  = "ISHRAQ  "+ Utils.add15Minutes(prayerData.sunrise)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}