package com.justcode.hxl.viewutil.自定义View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.extend.start
import com.justcode.hxl.viewutil.自定义View.绘制1_1.Draw_1_1_Activity
import kotlinx.android.synthetic.main.activity_draw_main.*

class DrawMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_main)

        btn_1_1.setOnClickListener {
           start<Draw_1_1_Activity>()
        }


    }
}
