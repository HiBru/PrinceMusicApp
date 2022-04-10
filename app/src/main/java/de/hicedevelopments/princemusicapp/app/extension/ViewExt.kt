package de.hicedevelopments.princemusicapp.app.extension

import android.app.Activity
import android.content.ContextWrapper
import android.view.View

fun View.findHostingActivity(): Activity? {
    var context = context
    while (context is ContextWrapper) {
        if (context is Activity) {
            return context
        }
        context = context.baseContext
    }
    return null
}