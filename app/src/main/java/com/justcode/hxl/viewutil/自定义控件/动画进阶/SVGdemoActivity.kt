package com.justcode.hxl.viewutil.自定义控件.动画进阶

import android.graphics.drawable.Animatable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.graphics.drawable.AnimatedVectorDrawableCompat
import com.justcode.hxl.viewutil.R
import kotlinx.android.synthetic.main.svg_demo_layout.*

class SVGdemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.svg_demo_layout)

        /**
         * 简单的展示 svg动画
         * 1.创建一个 vector svg_line.xml 画好路径
         * 2.创建一个 动画 anim_trim_start.xml
         * 3.关联动画和path  line_animated_vector.xml
         * 4.如下
         */
        val animatedVectorDrawableCompat = AnimatedVectorDrawableCompat.create(this,R.drawable.line_animated_vector)
        iv_svg1.setImageDrawable(animatedVectorDrawableCompat)
        (iv_svg1.drawable as Animatable).start()
    }
}
