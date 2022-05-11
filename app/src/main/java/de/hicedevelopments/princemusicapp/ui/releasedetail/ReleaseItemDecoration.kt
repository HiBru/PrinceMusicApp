package de.hicedevelopments.princemusicapp.ui.releasedetail

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import de.hicedevelopments.princemusicapp.R
import kotlin.math.roundToInt

class ReleaseItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val context = view.context
        val padding = (context.resources.getDimensionPixelSize(R.dimen.padding_small) * Resources.getSystem().displayMetrics.density).roundToInt()
        val position = parent.getChildLayoutPosition(view)
        val isFirstItem = position == 0
        val isLastItem = position == state.itemCount-1

        when {
            isFirstItem -> {
                outRect.left = padding
                outRect.right = padding
            }
            isLastItem -> {
                outRect.left = padding
                outRect.right = padding
                outRect.top = padding
                outRect.bottom = padding
            }
            else -> {
                outRect.left = padding
                outRect.right = padding
                outRect.top = padding
            }
        }
    }

}