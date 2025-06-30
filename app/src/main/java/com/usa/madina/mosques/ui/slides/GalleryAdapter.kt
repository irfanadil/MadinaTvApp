package com.usa.madina.mosques.ui.slides

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.usa.madina.mosques.R
import com.usa.madina.mosques.ui.slides.data.GalleryItem

class GalleryAdapter(
    fragment: Fragment,
    private val items: List<GalleryItem>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment {
        return when (val item = items[position]) {
            is GalleryItem.ImageItem -> ImageFragment.newInstance(item.path)
            is GalleryItem.FragmentItem -> item.fragment
        }
    }
}