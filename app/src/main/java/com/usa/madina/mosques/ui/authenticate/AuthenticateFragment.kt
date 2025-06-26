package com.usa.madina.mosques.ui.authenticate

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
import kotlin.getValue
import com.usa.madina.mosques.ui.AuthenticateViewModel
import com.usa.madina.mosques.R
import com.usa.madina.mosques.databinding.FragmentAuthenticateBinding
import com.usa.madina.mosques.repo.network.ApiResponse
import com.usa.madina.mosques.ui.domain.UserDataModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthenticateFragment : Fragment() {

    private var _binding: FragmentAuthenticateBinding? = null
    private val binding get() = _binding!!


    private val viewModel: AuthenticateViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthenticateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        observeUserAuthentication()
    }

    private var userName = ""
    private var password = ""

    private fun setupListeners(){
        userName =  resources.getString(R.string.userName)
        password =  resources.getString(R.string.password)

        binding.validateButton.setOnClickListener {
            viewModel.authenticateUser(userName, password)
        }
    }

    private fun observeUserAuthentication(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.userDetailStateFlow.collect{
                    when(it){
                        is ApiResponse.Error<*> -> { println(it.message) }
                        ApiResponse.Loading -> {}
                        is ApiResponse.Success<UserDataModel> -> {
                                findNavController().navigate(R.id.action_authenticate_to_prayerTimesFragment)
                        }
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}