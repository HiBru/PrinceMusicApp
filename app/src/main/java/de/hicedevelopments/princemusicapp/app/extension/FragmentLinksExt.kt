package de.hicedevelopments.princemusicapp.app.extension

import androidx.fragment.app.Fragment
import de.hicedevelopments.princemusicapp.util.createBrowserIntent

fun Fragment.openWebsite(payload: String?) {
    payload?.let { startActivitySafely(createBrowserIntent(it)) }
}