package com.wonjoong.android.sopthub.util

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.view.forEach
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wonjoong.android.sopthub.ui.home.GithubFragmentType

class GithubRecyclerViewItemDecoration(
    private val padding: Int,
    @ColorInt
    private val divColor: Int,
    private val strokeWidth: Float = 8f,
    private val fragmentType: GithubFragmentType
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        with(outRect) {
            left = padding
            top = padding
            right = padding
            bottom = padding
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        if (fragmentType == GithubFragmentType.Follower) {
            val paint = Paint()
            paint.color = divColor
            val left = parent.paddingStart
            val right = parent.width - parent.paddingEnd
            parent.forEach { view ->
                val top = (view.bottom + padding).toFloat()
                val bottom = top + strokeWidth
                c.drawRect(left.toFloat(), top, right.toFloat(), bottom, paint)
            }
        }
    }

}