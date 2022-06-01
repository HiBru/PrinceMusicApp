package de.hicedevelopments.princemusicapp.app.extension

import android.widget.TextView
import androidx.databinding.BindingAdapter
import de.hicedevelopments.princemusicapp.R
import de.hicedevelopments.princemusicapp.data.model.VideoLink

@BindingAdapter("plurals")
fun TextView.setPlurals(videos: List<VideoLink>?) {
    videos?.let {
        text = context.resources.getQuantityString(R.plurals.videos, videos.size)
    }
}