package com.justcode.hxl.viewutil.自定义View.绘制1_2.practice1_2

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class Sample01LinearGradientView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    init {
        val gradient = LinearGradient(100f,100f,500f,500f,
            Color.parseColor("#E91E63"),
            Color.parseColor("#2196F3"),Shader.TileMode.CLAMP)
        paint.shader = gradient
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(300f,300f,200f,paint)
    }
}