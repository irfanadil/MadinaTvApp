package com.usa.madina.mosques.ui.slides

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class MarginPageTransformer(private val marginPx: Int) : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val pager = page.parent.parent as ViewPager2
        val offset = marginPx * pager.orientation

        if (pager.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
            page.translationX = offset * position
        } else {
            page.translationY = offset * position
        }
    }
}