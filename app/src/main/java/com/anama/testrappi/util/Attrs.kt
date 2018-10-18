package com.anama.testrappi.util

import android.databinding.BindingAdapter
import android.util.Log
import com.facebook.drawee.view.SimpleDraweeView

@BindingAdapter("app:imgUrl")
fun setImageUrl(img: SimpleDraweeView, url: String?) {
    Log.i("IMGGGG", ""+url)
    if (url != null) img.setImageURI(url)
}