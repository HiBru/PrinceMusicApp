package de.hicedevelopments.princemusicapp.util

import android.content.Context
import android.content.DialogInterface
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import de.hicedevelopments.princemusicapp.R

object AlertUtil {

    @StringRes
    private var defaultPositiveBtn = R.string.alert_default_positive_btn

    @StringRes
    private var defaultNegativeBtn = R.string.alert_default_negative_btn

    fun showAlert(
        context: Context,
        title: String?,
        message: String,
        @StringRes positiveBtn: Int? = null,
        cancelable: Boolean = false,
        onButtonClick: () -> Unit = {}) {
        AlertDialog.Builder(context, R.style.DialogTheme)
            .apply { title?.let { setTitle(title) } }
            .setMessage(message)
            .setCancelable(cancelable)
            .setPositiveButton(positiveBtn ?: defaultPositiveBtn) { _, _ -> onButtonClick() }
            .create()
            .show()
    }

    fun showAlert(
        context: Context,
        @StringRes title: Int?,
        @StringRes message: Int,
        @StringRes positiveBtn: Int? = null,
        cancelable: Boolean = false,
        onButtonClick: (DialogInterface) -> Unit = {}) {
        AlertDialog.Builder(context, R.style.DialogTheme)
            .apply { title?.let { setTitle(it) } }
            .setMessage(message)
            .setCancelable(cancelable)
            .setPositiveButton(positiveBtn ?: defaultPositiveBtn) { dialog, _ -> onButtonClick(dialog) }
            .create()
            .show()
    }
}