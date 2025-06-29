package com.usa.madina.mosques.ui.authenticate

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.gson.GsonBuilder
import kotlin.getValue
import com.usa.madina.mosques.ui.AuthenticateViewModel
import com.usa.madina.mosques.R
import com.usa.madina.mosques.databinding.FragmentAuthenticateBinding
import com.usa.madina.mosques.repo.network.ApiResponse
import com.usa.madina.mosques.repo.network.BearerTokenInterceptor
import com.usa.madina.mosques.repo.network.ServiceApi
import com.usa.madina.mosques.ui.domain.UserDataModel
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.inject.Inject
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@AndroidEntryPoint
class AuthenticateFragment : Fragment() {

    private var _binding: FragmentAuthenticateBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var serviceApi: ServiceApi

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
            Log.e("TAG", "hit again...")
            viewModel.authenticateUser("demo","asdas")
            //callFromHere()
        }
    }

    private fun observeUserAuthentication(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.userDetailStateFlow.collect() {
                    it.configurationModel?.let{

                        findNavController().navigate(R.id.action_authenticate_to_prayerTimesFragment)
                    }

                }

            }
        }



    }


    fun callFromHere() {

        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        })

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        sslContext.socketFactory


        val myRetrofit = Retrofit
            .Builder()
            .baseUrl("https://api-gateway.madinaapps.com/")
            .client(OkHttpClient.Builder()
                .hostnameVerifier { hostname: String, session: SSLSession -> true }
                .sslSocketFactory(
                    sslContext.socketFactory,
                    trustAllCerts[0] as X509TrustManager
                )
                .addInterceptor(BearerTokenInterceptor("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJ0cnVzdCIsInJlYWQiLCJ3cml0ZSJdLCJleHAiOjE3NTE3NTA2MDUsImF1dGhvcml0aWVzIjpbIlJPTEVfU0VTU0lPTl9JRF8xNjA2ZDRiMC03NjVjLTRmNjUtYTA5NS02YjI4YWNlZDk4N2EiLCJST0xFX0NMSUVOVCIsIlJPTEVfQ0xJRU5UX0lEX2E5M2JiODFhLTMyOGEtMTFlZC1iYTFjLTBhNTMwOWZlYmVmZSJdLCJqdGkiOiJfaVhxVk9sV05id0VBbUNTX2w0bHVvQXlaQ00iLCJjbGllbnRfaWQiOiJkZW1vIn0.pU9Sgt1UpRQLZoxV2GZNsi9g0pubZ6GejCqhQYGrXsHJ8JMPGaYlzjyQXVROffmt_NNwRVtNsOyIjs71k5-v3ek4ajpO9hGcaP8z19a5ECVV2xyn0fNBPnkqdQY6F7V5OVLG2524aCUiif-VqEendz3meSz9-PeveU1gnH0FNpqhcOXDs9phuBNmd846eISSsVA9hiDHtA191KffAb11sRi8kh9un2S2eYDqKWtRd8O3FyLxgvTjVC9kngecq8KlEZDBNfSewhxIkvgJXWEPlBE3Qbi1x6iln4iAexq9G7vnfXS1OpmVSw78VWFq8A2U1rCs7aCfBbuSxs30gBeJmg"))
                .build())
            .addConverterFactory(
                GsonConverterFactory
                    .create(
                        GsonBuilder().serializeNulls().enableComplexMapKeySerialization().create()
                    )
            )
            .build()


        val myServiceClient = myRetrofit.create(ServiceApi::class.java)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val deviceDetailResponse = myServiceClient.getDeviceDetails(passKey = "MASJID_DISPLAY")
                Log.e("DDR", deviceDetailResponse.toString())
                val clientConfigurationResponse = serviceApi.getClientConfigurations()
                Log.e("CCR",clientConfigurationResponse.toString())
                val userDataModel = UserDataModel(null, deviceDetailResponse, clientConfigurationResponse)
                Log.e("UserData",userDataModel.toString())
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}