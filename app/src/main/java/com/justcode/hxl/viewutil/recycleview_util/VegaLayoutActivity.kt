package com.justcode.hxl.viewutil.recycleview_util

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.recycleview_util.core.BaseAdapter
import com.justcode.hxl.viewutil.recycleview_util.core.BaseViewHolder
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.vegalayoutmanager.VegaLayoutManager
import kotlinx.android.synthetic.main.activity_vega_layout.*
import kotlinx.android.synthetic.main.item_vega.view.*

class VegaLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vega_layout)

        recycler_vega.layoutManager = VegaLayoutManager()
        recycler_vega.adapter = VegaAdapter()
    }
}

class VegaAdapter : BaseAdapter() {
    val list = ArrayList<StockEntity>()

    init {
        list.add(StockEntity("Google Inc.", 921.59f, 1, "+6.59 (+0.72%)"))
        list.add(StockEntity("Apple Inc.", 158.73f, 1, "+0.06 (+0.04%)"))
        list.add(StockEntity("Vmware Inc.", 109.74f, -1, "-0.24 (-0.22%)"))
        list.add(StockEntity("Microsoft Inc.", 75.44f, 1, "+0.28 (+0.37%)"))
        list.add(StockEntity("Facebook Inc.", 172.52f, 1, "+2.51 (+1.48%)"))
        list.add(StockEntity("IBM Inc.", 144.40f, -1, "-0.15 (-0.10%)"))
        list.add(StockEntity("Alibaba Inc.", 180.04f, 1, "+0.06 (+0.03%)"))
        list.add(StockEntity("Tencent Inc.", 346.400f, 1, "+2.200 (+0.64%)"))
        list.add(StockEntity("Baidu Inc.", 237.92f, -1, "-1.15 (-0.48%)"))
        list.add(StockEntity("Amazon Inc.", 969.47f, -1, "-4.72 (-0.48%)"))
        list.add(StockEntity("Oracle Inc.", 48.03f, -1, "-0.30 (-0.62%)"))
        list.add(StockEntity("Intel Inc.", 37.22f, 1, "+0.22 (+0.61%)"))
        list.add(StockEntity("Cisco Systems Inc.", 32.49f, -1, "-0.03 (-0.08%)"))
        list.add(StockEntity("Qualcomm Inc.", 52.30f, 1, "+0.05 (+0.10%)"))
        list.add(StockEntity("Sony Inc.", 37.65f, -1, "-0.74 (-1.93%)"))
    }

    override var layoutId: Int
        get() = R.layout.item_vega
        set(value) {}

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        with(holder.itemView) {
            item_name_tv.text = list[position].name
            item_current_price.text = "$" + list[position].price

            item_trend_flag.setImageResource(
                if (list[position].flag > 0) {
                    R.drawable.up_red
                } else {
                    R.drawable.down_green
                }
            )
            item_gross.text = list[position].gross

        }
    }
}

data class StockEntity(
    var name: String,
    var price: Float,
    var flag: Int,
    var gross: String
)


