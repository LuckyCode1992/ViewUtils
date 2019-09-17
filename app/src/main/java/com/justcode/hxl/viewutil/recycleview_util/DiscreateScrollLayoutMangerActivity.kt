package com.justcode.hxl.viewutil.recycleview_util

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.recycleview_util.core.MultiTypeAdapter
import kotlinx.android.synthetic.main.activity_discreate_scroll_layout_manger.*

class DiscreateScrollLayoutMangerActivity : AppCompatActivity() {
    var acIndex = 0

    val list:MutableList<Any> = ArrayList()
    val adapter:MultiTypeAdapter = MultiTypeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discreate_scroll_layout_manger)



        btn_shop.setOnClickListener {
            hideButton()
        }
        btn_weather.setOnClickListener {
            hideButton()
        }
        btn_pic.setOnClickListener {
            hideButton()
        }
    }

    fun hideButton() {
        acIndex++
        btn_shop.visibility = View.GONE
        btn_weather.visibility = View.GONE
        btn_pic.visibility = View.GONE
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (acIndex == 0) {
            finish()
        } else {
            btn_shop.visibility = View.VISIBLE
            btn_weather.visibility = View.VISIBLE
            btn_pic.visibility = View.VISIBLE
        }

    }
}
