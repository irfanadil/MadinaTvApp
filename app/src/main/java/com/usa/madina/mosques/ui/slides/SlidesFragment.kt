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
import com.usa.madina.mosques.ui.prayers.PrayerTimesFragment
import com.usa.madina.mosques.ui.slides.data.GalleryItem

class SlidesFragment: Fragment() {

    private var _binding: FragmentSlidesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var viewPager: ViewPager2
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var autoScrollRunnable: Runnable

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

        // Create gallery items with fragments mixed in
        val galleryItems = listOf(
            GalleryItem.ImageItem( "https://media.madinaapps.com/prod/kiosk-cp-media/client_6/slideshow_275/bWFnbm9saWEtMjIxODc4OF8xOTIwLmpwZzE1NzkwNjA0MTM0MTQ=.jpg"),
            GalleryItem.ImageItem("https://media.madinaapps.com/prod/kiosk-cp-media/client_6/gallery-items/a3bcd084-ff5e-4fa4-9f2e-4183c51a51a9.jpg"),
            GalleryItem.FragmentItem(PrayerTimesFragment()),  // Fragment between images
            GalleryItem.ImageItem("https://media.madinaapps.com/prod/kiosk-cp-media/client_6/slideshow_275/c3Vuc2V0LTEzNzMxNzFfMTkyMC5qcGcxNTc5MDYwNDI4Mzkz.jpg"),
        )
        setupViewPager(galleryItems)
        setupAutoScroll(galleryItems.size)
    }

    private fun setupViewPager(items: List<GalleryItem>) {
        viewPager = binding.viewPager
        viewPager.adapter = GalleryAdapter(this, items)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPager.offscreenPageLimit = 3  // Keep fragments in memory

        // Add page margin
        viewPager.setPageTransformer(MarginPageTransformer(
            resources.getDimensionPixelSize(R.dimen.page_margin)
        ))
    }

    private fun setupAutoScroll(totalItems: Int) {
        autoScrollRunnable = object : Runnable {
            override fun run() {
                val nextItem = (viewPager.currentItem + 1) % totalItems
                viewPager.setCurrentItem(nextItem, true)
                handler.postDelayed(this, 10000)
            }
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                handler.removeCallbacks(autoScrollRunnable)
                handler.postDelayed(autoScrollRunnable, 10000)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(autoScrollRunnable, 10000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(autoScrollRunnable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}