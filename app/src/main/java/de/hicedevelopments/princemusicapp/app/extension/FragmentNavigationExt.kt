package de.hicedevelopments.princemusicapp.app.extension

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

fun Fragment.navigateUsingAction(@IdRes action: Int) = view?.let {
    findNavController().navigate(action)
}

fun Fragment.navigateUsingDirections(directions: NavDirections) = view?.let {
    findNavController().navigate(directions)
}