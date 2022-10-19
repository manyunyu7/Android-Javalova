package com.feylabs.javalova.util

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.feylabs.javalova.R
import okhttp3.*
import java.io.File

object ImageViewUtils {


    enum class ThumbnailsType(val value: Int) {
        ADD_PHOTO_1(R.drawable.bg_header_daylight),
        LOADING_1(R.drawable.bg_header_daylight),
    }

    fun ImageView.loadImageFromURL(
        context: Context,
        url: String? = "",
        thumbnailsType: ThumbnailsType = ThumbnailsType.LOADING_1
    ) {
        Glide.with(context)
            .load(url)
            .placeholder(loadThumbnails())
//            .thumbnail(Glide.with(context).load(R.raw.ic_loading_google).fitCenter())
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(this)
    }

    private fun loadThumbnails(): Int {
        val list = listOf(
            R.drawable.ic_bz002,
            R.drawable.ic_cc002,
            R.drawable.ic_dh002,
            R.drawable.ic_lg002,
            R.drawable.ic_ezzii01,
            R.drawable.ic_ni002,
//            R.drawable.bg_header_sunrise
        )
        return list.random()
    }

    fun ImageView.loadImage(
        context: Context,
        uri: Uri? = null,
        file: File? = null,
        thumbnailsType: ThumbnailsType = ThumbnailsType.LOADING_1
    ) {
        if (file != null) {
            Glide.with(context)
                .load(file)
                .placeholder(thumbnailsType.value)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(this)
        } else {
            if (uri != null) {
                Glide.with(context)
                    .load(uri)
                    .placeholder(thumbnailsType.value)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(this)
            }
        }

    }

    fun ImageView.loadImage(
        context: Context,
        drawable: Int,
        thumbnailsType: ThumbnailsType = ThumbnailsType.LOADING_1
    ) {
        Glide.with(context)
            .load(drawable)
            .placeholder(thumbnailsType.value)
            .thumbnail(Glide.with(context).load(R.raw.ic_loading_google))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(this)
    }

}