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
import com.usa.madina.mosques.ui.MainViewModel
import com.usa.madina.mosques.R
import com.usa.madina.mosques.databinding.FragmentAuthenticateBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthenticateFragment : Fragment() {

    private var _binding: FragmentAuthenticateBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()
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
        observeUserAuthenticationResponse()
    }

    private fun setupListeners(){
        binding.validateButton.setOnClickListener {
            viewModel.authenticateUser("demo","asdas")
        }
    }

    private fun observeUserAuthenticationResponse(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.userDetailReceived.collect{
                    if(it)
                        findNavController().navigate(R.id.action_authenticate_to_slidesFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}