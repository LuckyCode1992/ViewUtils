package com.justcode.hxl.viewutil.自定义控件.第十章_android画布

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.extend.start
import kotlinx.android.synthetic.main.activity_huabu_main.*

class HuabuMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_huabu_main)
        btn_shape_drawable.setOnClickListener {
            start<ShapeDrawableActivity>()
        }
    }
}
