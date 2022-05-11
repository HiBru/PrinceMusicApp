package de.hicedevelopments.princemusicapp.ui.releaselist.items

import de.hicedevelopments.princemusicapp.data.model.Release

data class ReleaseListItem(
    val release: Release
) : ReleaseListRow {
    val id = release.id
    val thumb = release.thumb
    val title = release.title
    val year = release.year
}
