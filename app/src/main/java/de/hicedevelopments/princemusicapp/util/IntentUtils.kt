package de.hicedevelopments.princemusicapp.util

import android.content.Intent
import android.net.Uri

fun createBrowserIntent(webPageUrl: String): Intent {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(webPageUrl)
    return intent
}