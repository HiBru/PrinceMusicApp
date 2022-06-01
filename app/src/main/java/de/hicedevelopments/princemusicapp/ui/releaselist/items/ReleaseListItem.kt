package de.hicedevelopments.princemusicapp.ui.releaselist.items

import android.os.Parcelable
import de.hicedevelopments.princemusicapp.data.model.Release
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReleaseListItem(
    val release: Release
) : Parcelable, ReleaseListRow {
    val id = release.id
    val thumb = release.thumb
    val title = release.title
    val type = release.type?.let { ReleaseType.getType(it) }
    val year = release.year
}

enum class ReleaseType(val type: String) {
    MASTER("master"),
    RELEASE("release");

    companion object {
        fun getType(type: String) = when(type) {
            "master" -> MASTER
            "release" -> RELEASE
            else -> null
        }
    }
}
