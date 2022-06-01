package de.hicedevelopments.princemusicapp.app.extension

import android.graphics.Rect
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import de.hicedevelopments.princemusicapp.R
import kotlin.math.abs

@BindingAdapter("usePageTransformer")
fun ViewPager2.addPageTransformer(usePageTransformer: Boolean = false) {
    if(usePageTransformer) {
        setPageTransformer { page: View, position: Float ->
            page.translationX = -page.translationX * position
            //scale items height
            page.scaleY = 1 - (.25f * abs(position))
            //add fading effect
            page.alpha = .25f + (1-abs(position))
        }
        addItemDecoration(object : RecyclerView.ItemDecoration() {
            private val horizontalMargin: Int = context.resources.getDimensionPixelSize(R.dimen.margin_tiny)

            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.right = horizontalMargin
                outRect.left = horizontalMargin
            }
        })
    }
}