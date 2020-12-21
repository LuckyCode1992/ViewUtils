package com.justcode.hxl.viewutil.自定义View.绘制1_2.practice1_2

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.justcode.hxl.viewutil.R

class Sample06LightingColorFilterView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val bitmap = BitmapFactory.decodeResource(resources,R.drawable.batman0)

    init {

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }
}