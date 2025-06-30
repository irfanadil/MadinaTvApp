package com.usa.madina.mosques.ui.slides

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.usa.madina.mosques.R

class ImageFragment : Fragment() {

    companion object {
        private const val ARG_IMAGE_PATH = "image_path"

        fun newInstance(imagePath: String) = ImageFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_IMAGE_PATH, imagePath)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.item_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imagePath = arguments?.getString(ARG_IMAGE_PATH) ?: return
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        progressBar.visibility = View.VISIBLE
        Glide.with(this)
            .load(imagePath)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable?>,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable?>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }
            })
            .into(imageView)
    }
}