package com.justcode.hxl.viewutil.recycleview_util

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.extend.bundleOf
import com.justcode.hxl.viewutil.extend.start
import com.justcode.hxl.viewutil.recycleview_util.core.BaseItemViewBinder
import com.justcode.hxl.viewutil.recycleview_util.core.ViewViewBinder
import com.justcode.hxl.viewutil.recycleview_util.core.MultiTypeAdapter
import com.justcode.hxl.viewutil.recycleview_util.core.MyViewHolder
import kotlinx.android.synthetic.main.activity_normol.*
import kotlinx.android.synthetic.main.itembinder_data1.view.*
import kotlinx.android.synthetic.main.itembinder_data2.view.*
import kotlinx.android.synthetic.main.itembinder_string.view.*

class NormolActivity : AppCompatActivity() {
    val adapter = MultiTypeAdapter()
    val list: MutableList<Any> = ArrayList()
    var isreverse = false

    val layoutManagerStr by lazy {
        intent.getStringExtra(LAYOUTMANAGER)
    }

    companion object {
        val LAYOUTMANAGER = "LAYOUTMANAGER"
        val Linear_LayoutManager_V = "Linear_LayoutManager_V"
        val Linear_LayoutManager_H = "Linear_LayoutManager_H"
        val Grid_LayoutManager = "Grid_LayoutManager"
        val StaggeredGrid_LayoutManager_V = "StaggeredGrid_LayoutManager_V"
        val StaggeredGrid_LayoutManager_H = "StaggeredGrid_LayoutManager_H"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normol)

        list.add("Linear_LayoutManager_V")
        list.add("Linear_LayoutManager_H")
        list.add("Grid_LayoutManager")
        list.add(StaggeredGrid_LayoutManager_V)
        list.add(StaggeredGrid_LayoutManager_H)
        list.add("你好呀")
        list.add("你好呀")
        list.add("你好呀")
        list.add("你好呀")
        list.add("你好呀")
        list.add(ItemData1())
        list.add(ItemData2())
        list.add("我们")
        list.add(ItemData1())
        list.add(ItemData2())
        list.add("dfjdksf")
        list.add(ItemData2())
        list.add(ItemData1())
        list.add("dfjdksf")
        list.add(ItemData2())
        list.add(ItemData2())
        list.add(ItemData1())
        list.add(ItemData2())
        list.add("dfjdksf")
        list.add(ItemData1())
        list.add("dfjdksf")




        tv_title.text = layoutManagerStr
        btn_nor.setOnClickListener {
            isreverse = false
            recycleLayoutSet()
        }
        btn_reverse.setOnClickListener {
            isreverse = true
            recycleLayoutSet()
        }
        recycleLayoutSet()
        val itemBinderString = ItemBinderString().apply {
            itemPositionDataCallBack = { position, data ->
                Toast.makeText(this@NormolActivity, "position:${position}-data:${data}", Toast.LENGTH_SHORT).show()
                if (data == "Linear_LayoutManager_H") {
                    start<NormolActivity>(bundleOf(NormolActivity.LAYOUTMANAGER to Linear_LayoutManager_H))
                }
                if (data == "Grid_LayoutManager") {
                    start<NormolActivity>(bundleOf(NormolActivity.LAYOUTMANAGER to Grid_LayoutManager))
                }
                if (data == StaggeredGrid_LayoutManager_V) {
                    start<NormolActivity>(bundleOf(LAYOUTMANAGER to StaggeredGrid_LayoutManager_V))
                }
                if (data == StaggeredGrid_LayoutManager_H) {
                    start<NormolActivity>(bundleOf(LAYOUTMANAGER to StaggeredGrid_LayoutManager_H))
                }
            }
        }
        val itemBinderData1 = ItemBinderData1().apply {
            itemPositionDataCallBack = { position, data ->
                Toast.makeText(this@NormolActivity, "position:${position}-data:${data}", Toast.LENGTH_SHORT).show()
            }
        }
        val itemBinderData2 = ItemBinderData2().apply {
            itemPositionDataCallBack = { position, data ->
                Toast.makeText(this@NormolActivity, "position:${position}-data:${data}", Toast.LENGTH_SHORT).show()
            }
        }
        val header: View = LayoutInflater.from(this).inflate(R.layout.recycle_header, not_recyclerview, false)
        val footer: View = LayoutInflater.from(this).inflate(R.layout.recycle_footer, not_recyclerview, false)
        val emp = LayoutInflater.from(this).inflate(R.layout.recycle_emp, not_recyclerview, false)
        adapter.register(View::class.java, ViewViewBinder(not_recyclerview))
        adapter.addHeader(header)
        adapter.addFooter(footer)
        adapter.emptyView = emp
        adapter.register(itemBinderString)
        adapter.register(itemBinderData1)
        adapter.register(itemBinderData2)
        not_recyclerview.adapter = adapter
//        list.clear()
        adapter.items = list
        adapter.notifyItems()

    }

    private fun recycleLayoutSet() {
        when (layoutManagerStr) {
            Linear_LayoutManager_V -> {
                val layoutManager = LinearLayoutManager(this)
                layoutManager.orientation = LinearLayoutManager.VERTICAL
                layoutManager.reverseLayout = isreverse
                not_recyclerview.layoutManager = layoutManager
            }
            Linear_LayoutManager_H -> {
                val layoutManager = LinearLayoutManager(this)
                layoutManager.orientation = LinearLayoutManager.HORIZONTAL
                layoutManager.reverseLayout = isreverse
                not_recyclerview.layoutManager = layoutManager
            }
            Grid_LayoutManager -> {
                val layoutManager = GridLayoutManager(this, 3)
                layoutManager.reverseLayout = isreverse
                not_recyclerview.layoutManager = layoutManager
            }
            StaggeredGrid_LayoutManager_V -> {
                val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                layoutManager.reverseLayout = isreverse
                not_recyclerview.layoutManager = layoutManager
            }
            StaggeredGrid_LayoutManager_H -> {
                val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
                layoutManager.reverseLayout = isreverse
                not_recyclerview.layoutManager = layoutManager
            }
        }
    }
}

data class ItemData1(var name: String = "名字", var gender: String = "男", var age: Int = 20)
data class ItemData2(var image: Int = R.drawable.img_00, var phone: String = "1234567890")

class ItemBinderString : BaseItemViewBinder<String>() {
    override val layoutRes: Int
        get() = R.layout.itembinder_string

    override
    fun onBindViewHolder(holder: MyViewHolder, item: String) {
        with(holder.itemView) {
            tv_string.text = item
            setOnClickListener {
                itemPositionDataCallBack.invoke(holder.adapterPosition, item)
            }
        }
    }

}

class ItemBinderData1 : BaseItemViewBinder<ItemData1>() {
    override val layoutRes: Int
        get() = R.layout.itembinder_data1

    override
    fun onBindViewHolder(holder: MyViewHolder, item: ItemData1) {
        with(holder.itemView) {
            tv_name.text = item.name
            tv_gender.text = item.gender
            tv_age.text = item.age.toString()
            setOnClickListener {
                itemPositionDataCallBack.invoke(holder.adapterPosition, item)
            }
        }
    }

}

class ItemBinderData2 : BaseItemViewBinder<ItemData2>() {
    override val layoutRes: Int
        get() = R.layout.itembinder_data2

    override
    fun onBindViewHolder(holder: MyViewHolder, item: ItemData2) {
        with(holder.itemView) {
            tv_phone.text = item.phone
            iv_icon.setImageResource(item.image)
            setOnClickListener {
                itemPositionDataCallBack.invoke(holder.adapterPosition, item)
            }
        }
    }

}

