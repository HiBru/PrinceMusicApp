package de.hicedevelopments.princemusicapp.app.extension

import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import de.hicedevelopments.princemusicapp.util.AlertUtil

fun Fragment.showToast(msg: String) = context?.let { ctx ->
    Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).apply {
        view?.findViewById<TextView>(android.R.id.message)?.gravity = Gravity.CENTER
        show()
    }
}

fun Fragment.showAlert(
    title: String? = null,
    message: String,
    @StringRes positiveBtn: Int? = null,
    cancelable: Boolean = false) = AlertUtil.showAlert(requireContext(), title, message, positiveBtn, cancelable)

fun Fragment.showAlert(
    @StringRes title: Int? = null,
    @StringRes message: Int,
    @StringRes positiveBtn: Int? = null,
    cancelable: Boolean = false) = AlertUtil.showAlert(context!!, title, message, positiveBtn, cancelable)