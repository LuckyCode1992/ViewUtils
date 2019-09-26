package com.justcode.hxl.viewutil.recycleview_util

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.recycleview_util.core.BaseItemViewBinder
import com.justcode.hxl.viewutil.recycleview_util.core.MultiTypeAdapter
import com.justcode.hxl.viewutil.recycleview_util.core.MyViewHolder
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.tantancardlayoutManager.CardConfig
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.tantancardlayoutManager.RenRenCallback

import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.tantancardlayoutManager.TantanCardLayoutManager
import kotlinx.android.synthetic.main.activity_tantan_card_layout.*
import kotlinx.android.synthetic.main.item_tantan_card_layout.view.*

class TantanCardLayoutActivity : AppCompatActivity() {

    lateinit var adapter: TanTanAdapter
    val list = ArrayList<TanTanData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tantan_card_layout)

        initList()

        adapter = TanTanAdapter(context = this, list = list)

        val layoutManager = TantanCardLayoutManager()
        recycler_tantan.layoutManager = layoutManager
        recycler_tantan.adapter = adapter

        CardConfig.initConfig(this)
        //测试竖直滑动是否已经不会被移除屏幕
        //callback.setHorizontalDeviation(Integer.MAX_VALUE);
        val callback = RenRenCallback(recycler_tantan, adapter, list)
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recycler_tantan)
    }

    private fun initList() {
        list.add(TanTanData(1,"美女Beauty_1号", R.drawable.beauty_1))
        list.add(TanTanData(2,"123444324", R.drawable.beauty_2))
        list.add(TanTanData(3,"美女Beautdsfsdy_1号", R.drawable.beauty_3))
        list.add(TanTanData(4,"asd", R.drawable.beauty_4))
        list.add(TanTanData(5,"美女Beausadasty_1号", R.drawable.beauty_5))
        list.add(TanTanData(6,"ffffff", R.drawable.beauty_6))
        list.add(TanTanData(7,"美女Beausssty_1号", R.drawable.beauty_7))
        list.add(TanTanData(8,"美女B22eauty_1号", R.drawable.beauty_8))
        list.add(TanTanData(9,"22", R.drawable.beauty_9))
        list.add(TanTanData(10,"美女Be3aut22y_1号", R.drawable.beauty_10))
        list.add(TanTanData(11,"美女Bea333uty_1号", R.drawable.beauty_11))
        list.add(TanTanData(12,"222", R.drawable.beauty_12))
        list.add(TanTanData(13,"美女Beauty_1号", R.drawable.beauty_13))
        list.add(TanTanData(14,"3344", R.drawable.beauty_15))
        list.add(TanTanData(15,"45224", R.drawable.beauty_16))

    }
}

data class TanTanData(var position: Int = 0,var content: String? = null, var resId: Int = 0)

class TanTanAdapter(var context: Context, var list: MutableList<TanTanData>) : RecyclerView.Adapter<TanHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TanHolder {
        return TanHolder(LayoutInflater.from(context).inflate(R.layout.item_tantan_card_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TanHolder, position: Int) {
        with(holder.itemView) {
            tvName.text = list[position].content
            iv.setImageResource(list[position].resId)
            tvPrecent.text = list[position].position.toString() + "/" + list.size
        }
    }

}

class TanHolder(view: View) : RecyclerView.ViewHolder(view)




