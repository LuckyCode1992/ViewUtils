package com.justcode.hxl.viewutil.自定义控件.绘图进阶

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.extend.start
import com.justcode.hxl.viewutil.自定义控件.绘图进阶.贝塞尔曲线.BezierActivity
import kotlinx.android.synthetic.main.activity_draw_advanced.*

class DrawAdvancedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_advanced)
        btn_bezier.setOnClickListener {
            start<BezierActivity>()
        }
    }
}
