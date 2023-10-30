package com.georfernandez.openpayfit.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.georfernandez.openpayfit.R

fun ImageView.showImage(imageURL: String) {
    Glide.with(context)
        .load("${Constants.SERVICE_IMAGE_URL}$imageURL")
        .placeholder(R.drawable.picture)
        .circleCrop()
        .into(this)
}
