package com.justcode.hxl.viewutil.recycleview_util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.LruCache
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.extend.toast
import com.justcode.hxl.viewutil.recycleview_util.core.BaseItemViewBinder
import com.justcode.hxl.viewutil.recycleview_util.core.MultiTypeAdapter
import com.justcode.hxl.viewutil.recycleview_util.core.MyViewHolder
import com.justcode.hxl.viewutil.recycleview_util.hiveLayoutmanager.HiveDrawable
import com.justcode.hxl.viewutil.recycleview_util.hiveLayoutmanager.HiveLayoutManager
import kotlinx.android.synthetic.main.activity_hive_layoutmanager.*
import kotlinx.android.synthetic.main.itembinder_hive.view.*
import java.util.*
import kotlin.collections.ArrayList

class HiveLayoutmanagerActivity : AppCompatActivity() {
    val adapter = MultiTypeAdapter()
    val list: MutableList<Int> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hive_layoutmanager)
        btn_insert.setOnClickListener {
            list.add(R.drawable.pic_card_2)
            adapter.items = list
            adapter.notifyItems()
        }
        btn_delete.setOnClickListener {
            if (adapter.itemCount != 0) {
                list.removeAt(adapter.itemCount - 1)
                adapter.items = list
                adapter.notifyItems()
            }
        }
        btn_move.setOnClickListener {
            val one = getRandomPosition()
            val two = getRandomPosition()
            val temp = list[one]
            list.removeAt(one)
            list.add(two, temp)
            adapter.notifyItemMoved(one, two)
        }
        initList()

        adapter.register(HiveItemBinder())
        recycle_hive.layoutManager = HiveLayoutManager(HiveLayoutManager.HORIZONTAL)
        recycle_hive.adapter = adapter
        adapter.items = list
        adapter.notifyItems()


    }

    private fun getRandomPosition(): Int {
        val count = adapter.itemCount
        return if (count > 0) {
            Random().nextInt(count)
        } else {
            0
        }
    }

    private fun initList() {
        list.add(R.drawable.img_0)
        list.add(R.drawable.img_1)
        list.add(R.drawable.img_2)
        list.add(R.drawable.img_3)
        list.add(R.drawable.img_4)
        list.add(R.drawable.img_5)
        list.add(R.drawable.img_6)
        list.add(R.drawable.img_7)
        list.add(R.drawable.img_8)
        list.add(R.drawable.img_9)
        list.add(R.drawable.img_10)
        list.add(R.drawable.img_11)
        list.add(R.drawable.img_12)
    }
}

class HiveItemBinder : BaseItemViewBinder<Int>() {
    override val layoutRes: Int
        get() = R.layout.itembinder_hive

    override fun onBindViewHolder(holder: MyViewHolder, item: Int) {
        with(holder.itemView) {

            val bitmap = BitmapFactory.decodeResource(context.resources, item)
            val drawable = HiveDrawable(HiveLayoutManager.HORIZONTAL, bitmap)

            tv_number.text = "${holder.adapterPosition}"
            iv_img_img.setImageDrawable(drawable)
            setOnClickListener {
                "${holder.adapterPosition}".toast(context)
            }
        }
    }


}
