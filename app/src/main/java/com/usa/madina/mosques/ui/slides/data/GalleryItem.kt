package com.usa.madina.mosques.ui.slides.data

import androidx.fragment.app.Fragment

sealed class GalleryItem {
    data class ImageItem(val path: String) : GalleryItem()
    data class FragmentItem(val fragment: Fragment) : GalleryItem()
}