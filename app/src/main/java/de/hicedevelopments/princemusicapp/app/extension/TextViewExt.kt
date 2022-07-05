package de.hicedevelopments.princemusicapp.app.extension

import android.graphics.Paint
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["textList", "separator"], requireAll = false)
fun TextView.textListToString(list: List<String>?, separator: String?) {
    list?.let { data ->
        text = data.joinToString(separator ?: ", ")
    }
}

@BindingAdapter("enableLinkMovement")
fun TextView.enableLinkMovement(enable: Boolean) {
    if (enable) {
        paintFlags = Paint.UNDERLINE_TEXT_FLAG
        movementMethod = LinkMovementMethod.getInstance()
    }
}