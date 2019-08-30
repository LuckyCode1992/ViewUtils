package com.justcode.hxl.viewutil.recycleview_util

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.extend.toast
import com.justcode.hxl.viewutil.recycleview_util.carousellayoutmanager.CarouselLayoutManager
import com.justcode.hxl.viewutil.recycleview_util.carousellayoutmanager.CarouselZoomPostLayoutListener
import com.justcode.hxl.viewutil.recycleview_util.carousellayoutmanager.CenterScrollListener
import com.justcode.hxl.viewutil.recycleview_util.core.BaseItemViewBinder
import com.justcode.hxl.viewutil.recycleview_util.core.MultiTypeAdapter
import com.justcode.hxl.viewutil.recycleview_util.core.MyViewHolder
import kotlinx.android.synthetic.main.activity_carouse_layout_manager.*
import kotlinx.android.synthetic.main.itembinder_carouselayout.view.*
import java.util.*
import kotlin.collections.ArrayList

class CarouseLayoutManagerActivity : AppCompatActivity() {

    val adapterVertiacl = MultiTypeAdapter()
    val adapterHorizontal = MultiTypeAdapter()
    val list: MutableList<Data> = ArrayList()
    val random = Random()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carouse_layout_manager)
        val carouseLayoutmangerV = CarouselLayoutManager(CarouselLayoutManager.VERTICAL, true)
        val carouseLayoutmangerH = CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, false)

        //添加动画效果
        carouseLayoutmangerV.setPostLayoutListener(CarouselZoomPostLayoutListener())
        //设置最大显示的item数量
        carouseLayoutmangerV.maxVisibleItems = 1
        list_vertical.layoutManager = carouseLayoutmangerV
        list_vertical.setHasFixedSize(true)
        //添加滚动效果动画
        list_vertical.addOnScrollListener(CenterScrollListener())

        carouseLayoutmangerH.maxVisibleItems = 5
        carouseLayoutmangerH.setPostLayoutListener(CarouselZoomPostLayoutListener())
        list_horizontal.layoutManager = carouseLayoutmangerH
        list_vertical.setHasFixedSize(true)
        list_horizontal.addOnScrollListener(CenterScrollListener())


        adapterVertiacl.register(ItemBinder())
        adapterHorizontal.register(ItemBinder())

        list_vertical.adapter = adapterVertiacl
        list_horizontal.adapter = adapterHorizontal

        for (i in 0 until 100) {
            val data: Data =
                Data(i, Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255)))
            list.add(data)
        }
        adapterVertiacl.items = list
        adapterVertiacl.notifyItems()
        adapterHorizontal.items = list
        adapterHorizontal.notifyItems()


        btn_left.setOnClickListener {
            list_vertical.smoothScrollToPosition(0)
            list_horizontal.smoothScrollToPosition(0)
        }
        btn_right.setOnClickListener {
            list_vertical.smoothScrollToPosition(adapterVertiacl.itemCount - 1)
            list_horizontal.smoothScrollToPosition(adapterHorizontal.itemCount - 1)
        }
    }
}

class ItemBinder : BaseItemViewBinder<Data>() {
    override val layoutRes: Int
        get() = R.layout.itembinder_carouselayout

    override fun onBindViewHolder(holder: MyViewHolder, item: Data) {
        with(holder.itemView) {
            c_item_1.text = item.position.toString()
            c_item_1.text = item.position.toString()
            item.color?.let { setBackgroundColor(it) }
            setOnClickListener {
                "点击了:${item.position}".toast(context)
            }
        }
    }

}

data class Data(var position: Int? = 0, var color: Int? = 0)