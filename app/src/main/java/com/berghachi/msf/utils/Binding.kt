package com.berghachi.msf.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.berghachi.msf.R
import com.bumptech.glide.Glide

class Binding {

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, url: String?) {
            Glide.with(view.context).load(url).placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_person).into(view)
            if (!url.isNullOrEmpty()) {
                view.scaleType = ImageView.ScaleType.FIT_XY
            } else {
                view.scaleType = ImageView.ScaleType.CENTER_INSIDE
            }
        }
    }
}
