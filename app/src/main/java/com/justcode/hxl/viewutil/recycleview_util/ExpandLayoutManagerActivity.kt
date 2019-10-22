package com.justcode.hxl.viewutil.recycleview_util

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.recycleview_util.core.BaseAdapter
import com.justcode.hxl.viewutil.recycleview_util.core.BaseViewHolder
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.expandlayoutmanager.ExpandLayoutManager
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.expandlayoutmanager.view.SimpleAnimationView
import kotlinx.android.synthetic.main.activity_expand_layout_manager.*
import kotlinx.android.synthetic.main.item_expand_layout.view.*
import java.io.InputStreamReader
import java.util.*

class ExpandLayoutManagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expand_layout_manager)

        try {
            val input = assets.open("cities.json")
            val reader = InputStreamReader(input, "UTF-8")
            val citiesResponse = Gson().fromJson(reader, CitiesResponse::class.java)
            recycler_expand.adapter = ExandAdapter(citiesResponse.cities)
            recycler_expand.layoutManager = ExpandLayoutManager()
// 跳转到某个item处打开
//            layoutManager.actionItem(1000)


        } catch (e: Exception) {
            Log.e(javaClass.getName(), e.message, e)
        }


    }
}


data class CitiesResponse(
    var cities: MutableList<City>
)

data class City(
    var id: Int? = null,
    var name: String? = null,
    var description: String? = null,
    var image_url: String? = null

)

class ExandAdapter(var list: MutableList<City>) : BaseAdapter() {
    override var layoutId: Int = R.layout.item_expand_layout
    var mColors: IntArray = IntArray(itemCount)
    val mRandom = Random()

    init {
        for (i in list.indices) {
            mColors[i] = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256))
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        with(holder.itemView) {
            tv_name_id.text = list[position].name
            tv_text_content.text = list[position].description
            iv_icon_id.setBackgroundColor(mColors[position])
            setOnClickListener {
                (it as SimpleAnimationView).actionItem()
            }

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}