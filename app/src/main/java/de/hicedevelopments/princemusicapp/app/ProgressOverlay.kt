package de.hicedevelopments.princemusicapp.app

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.TextView
import androidx.annotation.StringRes
import de.hicedevelopments.princemusicapp.R

class ProgressOverlay(context: Context) {

    private val dialog: Dialog = Dialog(context).apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
        setContentView(R.layout.dialog_progress)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun show(show: Boolean, @StringRes desc: Int? = null) {
        if (show) {
            desc?.let { setDescription(it) }
            dialog.show()
        } else {
            close()
        }
    }

    private fun setDescription(@StringRes desc: Int) = dialog.findViewById<TextView>(R.id.tv_progress).setText(desc)

    private fun close() = dialog.dismiss()
}