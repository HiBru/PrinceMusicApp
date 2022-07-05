package de.hicedevelopments.princemusicapp.app.extension

import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import de.hicedevelopments.princemusicapp.R
import de.hicedevelopments.princemusicapp.common.GlideApp
import de.hicedevelopments.princemusicapp.data.model.Image

@BindingAdapter(value = ["imageUrl", "showPlaceholder"], requireAll = false)
fun ImageView.loadImageUrl(url: String?, showPlaceholder: Boolean = true) {
    if (showPlaceholder) loadImageWithPlaceholder(url)
    else loadImageWithoutPlaceholder(url)
}

fun ImageView.loadImageWithPlaceholder(url: String?) {
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

fun ImageView.loadImageWithoutPlaceholder(url: String?) {
    url?.let {
        GlideApp.with(context)
            .load(url)
            .thumbnail(.5f)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(this)
    } ?: let {
        Log.w("Prince Music App", "Cannot load image, missing required 'imagePath' parameters")
    }
}

@BindingAdapter("imageDimensionRatio")
fun ImageView.imageDimensionRatio(image: Image) {
    if (parent !is ConstraintLayout) return
    if (image.height == 0) {
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        return
    }
    with(layoutParams as ConstraintLayout.LayoutParams) {
        dimensionRatio = "H, ${image.width}:${image.height}"
        layoutParams = this
    }
}