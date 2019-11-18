package com.justcode.hxl.viewutil.自定义控件.第六章_paint基本使用

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.justcode.hxl.viewutil.R

class PaintSimpleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paint_simple)
        /**
         *  硬件加速：
         *
         *  cpu主导绘图：
         *  1.让view层次结构失效
         *  2.绘制view层次结构
         *
         *  GPU主导绘图：
         *  1.让view层次结构失效
         *  2.记录、更新显示列表
         *  3.绘制显示列表
         *
         *  gpu 加速实际是使用OpenGl 的相关函数绘制
         *
         *  优点：提高显示和刷新速度
         *  缺点：兼容问题，内存消耗问题，电量消耗问题
         */

        /**
         * 关闭（禁用）GPU 硬件加速的方法
         *
         * 1.<application android:hardwareAccelerated="false"/>
         * 2.<activity android:hardwareAccelerated="false"/>
         * 3.在view层级     setLayerType(LAYER_TYPE_SOFTWARE, null)
         * 4.在xml中，layerType ="software"
         */

    }
}
