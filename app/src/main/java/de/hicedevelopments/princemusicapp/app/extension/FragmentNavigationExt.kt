package de.hicedevelopments.princemusicapp.app.extension

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController

fun Fragment.navigateUsingAction(@IdRes action: Int, navigatorExtras: FragmentNavigator.Extras? = null) = view?.let {
    findNavController().navigate(action, null, null, navigatorExtras)
}

fun Fragment.navigateUsingDirections(directions: NavDirections) = view?.let {
    findNavController().navigate(directions)
}