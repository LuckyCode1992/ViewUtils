package com.justcode.hxl.viewutil.recycleview_util

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.extend.start

import kotlinx.android.synthetic.main.activity_discreate_scroll_layout_manger.*


class DiscreateScrollLayoutMangerActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discreate_scroll_layout_manger)





        btn_shop.setOnClickListener {
            start<ShopActivity>()
        }
        btn_weather.setOnClickListener {
            start<WeatherActivity>()
        }

    }


}


