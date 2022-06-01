package de.hicedevelopments.princemusicapp.ui.releasedetail

import de.hicedevelopments.princemusicapp.data.model.VideoLink

interface VideoLinkListener {
    fun onVideoLinkClick(link: VideoLink)
}