package com.usa.madina.mosques.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.usa.madina.mosques.databinding.FragmentSettingBinding
import com.usa.madina.mosques.ui.MainViewModel
import kotlin.getValue

class SettingFragment: Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setupRecyclerView()
        //loadAppointments()
        // setupFilterSearch()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}