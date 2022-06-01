package de.hicedevelopments.princemusicapp.app.extension

import android.content.ActivityNotFoundException
import android.content.Intent
import android.util.Log
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController

fun Fragment.navigateUsingAction(@IdRes action: Int, navigatorExtras: FragmentNavigator.Extras? = null) = view?.let {
    findNavController().navigate(action, null, null, navigatorExtras)
}

fun Fragment.navigateUsingDirections(directions: NavDirections, navigatorExtras: FragmentNavigator.Extras? = null) = view?.let {
    navigatorExtras?.let {
        findNavController().navigate(directions, navigatorExtras)
    } ?: findNavController().navigate(directions)
}

fun Fragment.startActivitySafely(intent: Intent) {
    try {
        startActivity(intent)
    } catch (exception: ActivityNotFoundException) {
        Log.w("HIGHERSELF", "Could not find activity to handle intent: $intent")
    }
}