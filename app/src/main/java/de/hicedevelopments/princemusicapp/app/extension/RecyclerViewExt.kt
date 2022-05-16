package de.hicedevelopments.princemusicapp.app.extension

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.hicedevelopments.princemusicapp.ui.releaselist.ReleaseItemDecoration

@BindingAdapter("addReleaseItemDecoration")
fun RecyclerView.addReleaseItemDecoration(enabled: Boolean) {
    if (layoutManager !is LinearLayoutManager) return
    if (enabled) {
        with(ReleaseItemDecoration()) {
            addItemDecoration(this)
        }
    }
}