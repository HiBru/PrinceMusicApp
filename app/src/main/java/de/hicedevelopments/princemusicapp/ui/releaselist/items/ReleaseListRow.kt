package de.hicedevelopments.princemusicapp.ui.releaselist.items

interface ReleaseListListener {
    fun onReleaseItemClick(position: Int, item: ReleaseListItem)
}

interface ReleaseListRow