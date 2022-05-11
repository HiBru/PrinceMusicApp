package de.hicedevelopments.princemusicapp.app.extension

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import de.hicedevelopments.princemusicapp.R
import de.hicedevelopments.princemusicapp.common.GlideApp

@BindingAdapter("imageUrl")
fun ImageView.loadImageUrl(url: String?) {
    url?.let {
        GlideApp.with(context)
            .load(url)
            .thumbnail(.5f)
            .placeholder(R.mipmap.prince_the_symbol_gold)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(this)
    } ?: let {
        Log.w("Prince Music App", "Cannot load image, missing required 'imagePath' parameters")
    }
}