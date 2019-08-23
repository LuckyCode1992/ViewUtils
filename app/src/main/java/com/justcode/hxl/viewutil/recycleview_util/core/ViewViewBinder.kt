package com.justcode.hxl.viewutil.recycleview_util.core

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

class ViewViewBinder(private val recyclerView: RecyclerView) : ItemViewBinder<View, ViewViewBinder.HeaderFooterViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): HeaderFooterViewHolder {
        val frameLayout = FrameLayout(parent.context)
        val layoutParams: RecyclerView.LayoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
        frameLayout.layoutParams = layoutParams
        return HeaderFooterViewHolder(frameLayout)
    }

    override fun onBindViewHolder(holder: HeaderFooterViewHolder, item: View) {
        val frameLayout = holder.itemView as FrameLayout
        val parent = item.parent
        if (parent != null) {
            val viewGroup = parent as ViewGroup
            viewGroup.removeView(item)
        }
        frameLayout.removeAllViews()
        frameLayout.addView(item, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewAttachedToWindow(holder: HeaderFooterViewHolder) {
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is StaggeredGridLayoutManager) {
            val lp = holder.itemView.layoutParams
            if (lp != null && lp is StaggeredGridLayoutManager.LayoutParams) {
                lp.isFullSpan = true
            }
        }
    }

    class HeaderFooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}