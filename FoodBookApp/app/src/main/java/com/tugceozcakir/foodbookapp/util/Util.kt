package com.tugceozcakir.foodbookapp.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tugceozcakir.foodbookapp.R

fun ImageView.downloadImage(url: String?, placeHolder: CircularProgressDrawable){
    val options = RequestOptions().placeholder(placeHolder).error(R.mipmap.ic_launcher_round)
    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
}
fun MakePlaceHolder(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 35f
        start()
    }
}
@BindingAdapter("android:downloadImage")
fun downloadImage(view: ImageView, url: String?){
    view.downloadImage(url, MakePlaceHolder(view.context))
}