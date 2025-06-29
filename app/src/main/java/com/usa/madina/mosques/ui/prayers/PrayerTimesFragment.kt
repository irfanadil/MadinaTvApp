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
import androidx.navigation.fragment.findNavController
import com.usa.madina.mosques.R
import com.usa.madina.mosques.databinding.FragmentAuthenticateBinding
import com.usa.madina.mosques.databinding.FragmentPrayerBinding
import com.usa.madina.mosques.repo.network.ServiceApi
import com.usa.madina.mosques.ui.AuthenticateViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.getValue

@AndroidEntryPoint
class PrayerTimesFragment: Fragment() {

    private var _binding: FragmentPrayerBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthenticateViewModel by activityViewModels()
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
        observeUserAuthenticationResponse()
    }

    private fun setupListeners(){

    }

    private fun observeUserAuthenticationResponse(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.userDetailReceived.collect{

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}