package com.justcode.hxl.viewutil.自定义控件.第十三章_控件高级属性

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.extend.start
import kotlinx.android.synthetic.main.activity_control.*

class ControlActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control)
        btn_gesture_detector.setOnClickListener {
            start<GestureDectorActivity>()
        }
    }
}
