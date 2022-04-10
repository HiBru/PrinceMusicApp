package de.hicedevelopments.princemusicapp.app.extension

import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter

@BindingAdapter("useAsBackButton")
fun Toolbar.setOnNavigationClickListener(obj: Any) {
    setNavigationOnClickListener { v -> v.findHostingActivity()?.onBackPressed() }
}