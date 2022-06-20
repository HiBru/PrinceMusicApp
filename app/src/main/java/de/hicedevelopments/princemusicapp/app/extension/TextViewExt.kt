package de.hicedevelopments.princemusicapp.app.extension

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["textList", "separator"], requireAll = false)
fun TextView.textListToString(list: List<String>?, separator: String?) {
    list?.let { data ->
        text = data.joinToString(separator ?: ", ")
    }
}