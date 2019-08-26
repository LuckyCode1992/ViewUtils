package com.justcode.hxl.viewutil.自定义控件.绘图基础

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.justcode.hxl.viewutil.R

class DrawingBasisActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawing_basis)
        /**
         *  android 中，paint类是画笔，canvas是画布
         *  和画笔相关的如 大小，粗细，画笔颜色，透明度，字体样式等，都在paint类中设置
         *  和画布相关（成品），形状（矩形），文字，等
         */
    }
}
