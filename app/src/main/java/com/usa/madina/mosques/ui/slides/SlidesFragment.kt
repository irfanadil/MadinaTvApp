package com.usa.madina.mosques.ui.slides

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.usa.madina.mosques.databinding.FragmentSlidesBinding
import com.usa.madina.mosques.ui.MainViewModel
import java.util.Timer
import java.util.TimerTask
import kotlin.getValue
import com.usa.madina.mosques.R

class SlidesFragment: Fragment() {

    private var _binding: FragmentSlidesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var viewPager: ViewPager2
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var autoScrollRunnable: Runnable

    private val imagePaths = listOf(
        "https://example.com/image1.jpg",
        "https://example.com/image2.jpg",
        "https://example.com/image3.jpg",
        "/storage/emulated/0/DCIM/image4.jpg"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Force landscape orientation
         ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSlidesBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        setupAutoScroll()
    }

    private fun setupViewPager() {
        viewPager = binding.viewPager
        viewPager.adapter = GalleryAdapter(requireContext(), imagePaths)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        // Add page margin for gallery effect
        viewPager.setPageTransformer(MarginPageTransformer(resources.getDimensionPixelSize(R.dimen.page_margin)))

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                handler.removeCallbacks(autoScrollRunnable)
                handler.postDelayed(autoScrollRunnable, 10000)
            }
        })
    }

    private fun setupAutoScroll() {
        autoScrollRunnable = object : Runnable {
            override fun run() {
                val nextItem = (viewPager.currentItem + 1) % imagePaths.size
                viewPager.setCurrentItem(nextItem, true)
                handler.postDelayed(this, 10000)  // Reschedule after 10 seconds
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Start auto-scroll when activity is visible
        handler.postDelayed(autoScrollRunnable, 10000)
    }

    override fun onPause() {
        super.onPause()
        // Stop auto-scroll when activity is not visible
        handler.removeCallbacks(autoScrollRunnable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}