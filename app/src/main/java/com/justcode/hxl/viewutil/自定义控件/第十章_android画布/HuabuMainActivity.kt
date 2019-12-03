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

        /**
         *  Drawable和Bitmap对比
         *  1. Bitmap 占用内存高于Drawable
         *  2.Bitmap绘制速度低于Drawable
         *  3.Bitmap绘图方便，Drawable调用Paint方便，但调用canvas不方便
         *  4.Drawable 有一些子类，可以方便低完成一些绘图功能，比如：ShapeDrawable，GradientDrawable
         *
         *  Drawable,Bitmap,自定义View的使用选择
         *  1.Bitmap只有一种情况使用：View种需要自己生成图像，使用Bitmap绘图，绘图后的结果保存在这个Bitmap中，供自定义View使用。
         *  2.当使用Drawable的子类能完成一些固有功能时，优先选择Drawable
         *  3.当需要使用setImageBackground(),setBackgroundDrawable()等直接设置Drawable资源的函数时，只能选择Drawable
         *  4.当在自定义View中指定位置显示图像功能时，既可以使用Drawable，也可以使用Bitmap
         *  5.除了Drawable和Bitmap以为的地方，都可以使用View
         */
        btn_shape_drawable.setOnClickListener {
            start<ShapeDrawableActivity>()
        }
        btn_bitmap.setOnClickListener {
            start<BitmapActivity>()
        }
    }
}
