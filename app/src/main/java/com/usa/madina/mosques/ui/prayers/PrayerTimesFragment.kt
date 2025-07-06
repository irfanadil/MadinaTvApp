package com.usa.madina.mosques.ui.prayers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.usa.madina.mosques.Utils
import com.usa.madina.mosques.databinding.FragmentPrayerBinding
import com.usa.madina.mosques.hideBackground
import com.usa.madina.mosques.repo.data.PrayerTimingModel
import com.usa.madina.mosques.repo.network.ApiResponse
import com.usa.madina.mosques.saveOriginalBackground
import com.usa.madina.mosques.showBackground
import com.usa.madina.mosques.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue
import com.usa.madina.mosques.R

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
        setupUI()
        setupListeners()
        observePrayerResponse()
        loadData()
    }

    private fun loadData(){
        viewModel.getPrayersData()
    }

    private fun setupUI(){
        binding.fajrRow.saveOriginalBackground()
        binding.fajrRow.hideBackground()
        binding.dhuhrRow.saveOriginalBackground()
        binding.dhuhrRow.hideBackground()
        binding.asrRow.saveOriginalBackground()
        binding.asrRow.hideBackground()
        binding.maghribRow.saveOriginalBackground()
        binding.maghribRow.hideBackground()
        binding.ishaRow.saveOriginalBackground()
        binding.ishaRow.hideBackground()
    }

    private fun setupListeners(){
        binding.settingButton.setOnClickListener{
            findNavController().navigate(R.id.action_prayerTimesFragment_to_settingFragment)
        }
    }

    private fun observePrayerResponse(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.prayerApiResponse.collect{ result->
                    when(result){
                        is ApiResponse.Error -> {
                            Toast.makeText(requireContext(), "Prayer Time Data Not Found...", Toast.LENGTH_LONG).show()
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

        val timeList = arrayListOf<String>()
        val prayerData = model.prayerTimes[0]
        binding.fajrStarts.text = prayerData.fajr.adhaanTime
        binding.fajrIqamah.text = prayerData.fajr.iqamahTime
        timeList.add(prayerData.fajr.adhaanTime)

        val dhurStart = Utils.convertAmToPm(prayerData.dhuhr.adhaanTime)
        val dhurIqamah = Utils.convertAmToPm(prayerData.dhuhr.iqamahTime)
        binding.dhuhrStarts.text = dhurStart
        binding.dhuhrIqamah.text = dhurIqamah
        timeList.add(dhurStart)

        val asrStart = Utils.convertAmToPm(prayerData.asr.adhaanTime)
        val asrIqamah = Utils.convertAmToPm(prayerData.asr.iqamahTime)
        binding.asrStarts.text = Utils.convertAmToPm(prayerData.asr.adhaanTime)
        binding.asrIqamah.text = Utils.convertAmToPm(prayerData.asr.iqamahTime)
        timeList.add(asrStart)

        val maghribStart = Utils.convertAmToPm(prayerData.maghrib.adhaanTime)
        val maghribIqamah = Utils.convertAmToPm(prayerData.maghrib.iqamahTime)
        binding.maghribStarts.text = Utils.convertAmToPm(prayerData.maghrib.adhaanTime)
        binding.maghribIqamah.text = Utils.convertAmToPm(prayerData.maghrib.iqamahTime)
        timeList.add(maghribStart)

        val ishaStart = Utils.convertAmToPm(prayerData.isha.adhaanTime)
        val ishaIqamah = Utils.convertAmToPm(prayerData.isha.iqamahTime)
        binding.ishaStarts.text = Utils.convertAmToPm(prayerData.isha.adhaanTime)
        binding.ishaIqamah.text = Utils.convertAmToPm(prayerData.isha.iqamahTime)
        timeList.add(ishaStart)

        binding.todayDate.text = Utils.convertAmToPm(prayerData.date)
        binding.todayIslamicDate.text = Utils.convertAmToPm(prayerData.hijriDate)

        binding.sunriseTime.text = "SUNRISE  "+prayerData.sunrise
        binding.ishraqTime.text  = "ISHRAQ  "+ Utils.add15Minutes(prayerData.sunrise)




        when(Utils.findNextUpcomingTime(timeList)){
            prayerData.fajr.adhaanTime -> {
                binding.fajrRow.showBackground()
            }
            dhurStart -> {
                binding.dhuhrRow.showBackground()
            }
            asrStart -> {
                binding.asrRow.showBackground()
            }
            maghribStart -> {
                binding.maghribRow.showBackground()
            }
            ishaStart -> {
                binding.ishaRow.showBackground()
            }
            else -> {
                Toast.makeText(requireContext(), "Next Prayer Time Not Found...", Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}