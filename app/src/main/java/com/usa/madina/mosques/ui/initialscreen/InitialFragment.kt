package com.usa.madina.mosques.ui.initialscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.usa.madina.mosques.databinding.FragmentInitialBinding
import com.usa.madina.mosques.ui.AuthenticateViewModel
import kotlin.getValue
import com.usa.madina.mosques.R

class InitialFragment : Fragment() {

    private var _binding: FragmentInitialBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AuthenticateViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInitialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setupRecyclerView()
        //loadAppointments()
       // setupFilterSearch()
        addBookingFabIcon()
    }


    private fun addBookingFabIcon(){
        binding.fab.setOnClickListener {
                findNavController().navigate(R.id.action_appointmentListFragment_to_authenticate)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}