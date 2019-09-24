package com.justcode.hxl.viewutil.recycleview_util

import android.content.Context
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.recycleview_util.core.BaseItemViewBinder
import com.justcode.hxl.viewutil.recycleview_util.core.ItemViewBinder
import com.justcode.hxl.viewutil.recycleview_util.core.MultiTypeAdapter
import com.justcode.hxl.viewutil.recycleview_util.core.MyViewHolder
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.greedolayout.GreedoLayoutManager
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.greedolayout.GreedoLayoutSizeCalculator
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.greedolayout.GreedoSpacingItemDecoration
import kotlinx.android.synthetic.main.activity_greedo_layout_manager.*

class GreedoLayoutManagerActivity : AppCompatActivity() {
    val list: MutableList<Int> = ArrayList()
    val adapter: MultiTypeAdapter = MultiTypeAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greedo_layout_manager)

        initList()

        val greedoItemViewBinder = GreeDoItemBinder(this, list)
        val greedoLayoutManager = GreedoLayoutManager(greedoItemViewBinder)
        greedoLayoutManager.setMaxRowHeight(dpToPx(150f, this))

        val spacing = dpToPx(4f, this)
        recycler_gree_do.addItemDecoration(GreedoSpacingItemDecoration(spacing))

        recycler_gree_do.layoutManager = greedoLayoutManager



        adapter.register(greedoItemViewBinder)
        recycler_gree_do.adapter = adapter
        adapter.items = list
        adapter.notifyItems()

        toggle_fixed_height.setOnClickListener {
            greedoLayoutManager.setFixedHeight(toggle_fixed_height.isChecked)
            greedoLayoutManager.requestLayout()
        }
    }

    fun dpToPx(dp: Float, context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp,
            context.resources.displayMetrics
        ).toInt()
    }

    private fun initList() {
        list.add(R.drawable.photo_1)
        list.add(R.drawable.photo_2)
        list.add(R.drawable.photo_3)
        list.add(R.drawable.photo_4)
        list.add(R.drawable.photo_5)
        list.add(R.drawable.photo_6)
        list.add(R.drawable.photo_7)
        list.add(R.drawable.photo_8)
        list.add(R.drawable.photo_9)
        list.add(R.drawable.photo_10)
        list.add(R.drawable.photo_11)
        list.add(R.drawable.photo_12)
        list.add(R.drawable.photo_13)
        list.add(R.drawable.photo_14)
        list.add(R.drawable.photo_15)
        list.add(R.drawable.photo_16)
        list.add(R.drawable.photo_17)
        list.add(R.drawable.photo_18)
        list.add(R.drawable.photo_1)
        list.add(R.drawable.photo_2)
        list.add(R.drawable.photo_3)
        list.add(R.drawable.photo_4)
        list.add(R.drawable.photo_5)
        list.add(R.drawable.photo_6)
        list.add(R.drawable.photo_7)
        list.add(R.drawable.photo_8)
        list.add(R.drawable.photo_9)
        list.add(R.drawable.photo_10)
        list.add(R.drawable.photo_11)
        list.add(R.drawable.photo_12)
        list.add(R.drawable.photo_13)
        list.add(R.drawable.photo_14)
        list.add(R.drawable.photo_15)
        list.add(R.drawable.photo_16)
        list.add(R.drawable.photo_17)
        list.add(R.drawable.photo_18)
    }
}

class GreeDoItemBinder(var context: Context, var list: MutableList<Int>) : ItemViewBinder<Int, GreedoHolder>(),
    GreedoLayoutSizeCalculator.SizeCalculatorDelegate {
    val mImageAspectRatios = DoubleArray(18)

    init {
        calculateImageAspectRatios()
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): GreedoHolder {
        val imageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP

        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        return GreedoHolder(imageView)
    }

    override fun onBindViewHolder(holder: GreedoHolder, item: Int) {
        (holder.itemView as ImageView).setImageResource(item)
    }

    override fun aspectRatioForIndex(index: Int): Double {
        if (index >= adapter.itemCount) return 1.0
        return mImageAspectRatios[getLoopedIndex(index)]
    }

    private fun getLoopedIndex(index: Int): Int {
        return index % 18// wrap around
    }

    private fun calculateImageAspectRatios() {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true

        for (i in 0..17) {
            BitmapFactory.decodeResource(context.getResources(), list[i], options)
            mImageAspectRatios[i] = options.outWidth / options.outHeight.toDouble()
        }
    }
}

class GreedoHolder(itemView: ImageView) : RecyclerView.ViewHolder(itemView)