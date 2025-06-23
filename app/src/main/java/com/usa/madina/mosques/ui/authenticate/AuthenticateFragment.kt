package com.usa.madina.mosques.ui.authenticate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import kotlin.getValue
import com.usa.madina.mosques.ui.AuthenticateViewModel
import com.usa.madina.mosques.R
import com.usa.madina.mosques.databinding.FragmentAuthenticateBinding
import dagger.hilt.android.AndroidEntryPoint

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

        //setupRecyclerView()
        //loadAppointments()
        //setupFilterSearch()
        //addBookingFabIcon()
        authenticateUser()
    }

    private var userName = ""
    private var password = ""

    fun authenticateUser(){
        userName =  resources.getString(R.string.userName)
        password =  resources.getString(R.string.password)

        binding.validateButton.setOnClickListener {
            viewModel.authenticateUser(userName, password)
        }
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}