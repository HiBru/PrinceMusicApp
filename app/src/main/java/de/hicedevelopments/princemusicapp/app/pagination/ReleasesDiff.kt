package de.hicedevelopments.princemusicapp.app.pagination

import androidx.recyclerview.widget.DiffUtil
import de.hicedevelopments.princemusicapp.ui.releaselist.items.ReleaseListRow

class ReleasesDiff : DiffUtil.ItemCallback<ReleaseListRow>() {
    override fun areItemsTheSame(oldItem: ReleaseListRow, newItem: ReleaseListRow): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: ReleaseListRow, newItem: ReleaseListRow): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}