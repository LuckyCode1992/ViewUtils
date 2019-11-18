package com.justcode.hxl.viewutil.自定义控件.第七章_绘图进阶.贝塞尔曲线

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.justcode.hxl.viewutil.R
import kotlinx.android.synthetic.main.activity_bezier.*


class BezierActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bezier)
        btn_bezier_2.setOnClickListener {
            bezier_2.startAnim()
        }

        btn_bezier_3.setOnClickListener {
            bezier_3.startAnim()
        }
        btn_bezier_4.setOnClickListener {
            bezier_4.startAnim()
        }
        btn_bezier_5.setOnClickListener {
            bezier_5.startAnim()
        }
    }
}
