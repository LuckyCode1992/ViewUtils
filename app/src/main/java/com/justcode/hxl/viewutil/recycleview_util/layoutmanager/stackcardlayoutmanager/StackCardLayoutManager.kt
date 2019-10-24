

package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.stackcardlayoutmanager

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.View
import com.justcode.hxl.viewutil.R


class StackCardLayoutManager(
        private val maxItemCount: Int
) : RecyclerView.LayoutManager() {
    private val addedChildren: List<View>
        get() = (0 until childCount).map { getChildAt(it) ?: throw NullPointerException() }

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams =
            RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.MATCH_PARENT)

    override fun isAutoMeasureEnabled(): Boolean = true

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onLayoutChildren(
            recycler: RecyclerView.Recycler,
            state: RecyclerView.State
    ) {
        if (state.itemCount == 0) {
            return
        }
        if (state.itemCount > maxItemCount) {
            throw RuntimeException("Can not set more Item than maxItemCount")
        }

        detachAndScrapAttachedViews(recycler)

        for (i in 0 until state.itemCount) {
            val view = recycler.getViewForPosition(i)
            measureChild(view, 0, 0)
            addView(view)
            val layoutParams = view.layoutParams as RecyclerView.LayoutParams
            val left = layoutParams.marginStart
            val top = (view.measuredHeight * i * 0.35).toInt()
            val right = view.measuredWidth + layoutParams.marginEnd
            val bottom = top + view.measuredHeight
            layoutDecorated(view, left, top, right, bottom)
            view.setTag(InitializedPosition.TOP.key, top)
        }
    }

    override fun canScrollVertically(): Boolean = true

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun scrollVerticallyBy(
            dy: Int,
            recycler: RecyclerView.Recycler,
            state: RecyclerView.State
    ): Int = dy.also { deltaY ->
        if (childCount == 0) {
            return@also
        }

        addedChildren.forEachIndexed { index, view ->
            val initializedTop = view.getTag(InitializedPosition.TOP.key) as Int
            val layoutParams = view.layoutParams as RecyclerView.LayoutParams
            val left = layoutParams.marginStart
            val top = Math.min(Math.max((view.top - deltaY), index * 30), initializedTop)
            val right = view.measuredWidth + layoutParams.marginEnd
            val bottom = top + view.measuredHeight
            layoutDecorated(view, left, top, right, bottom)
        }
    }

    private enum class InitializedPosition(val key: Int) {
        TOP(R.integer.top)
    }
}