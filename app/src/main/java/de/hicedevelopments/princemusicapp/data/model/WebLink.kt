package de.hicedevelopments.princemusicapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WebLink(
    val text: String?,
    val link: String
) : Parcelable

interface WebLinkListener {
    fun onWebLinkClick(link: WebLink)
}
