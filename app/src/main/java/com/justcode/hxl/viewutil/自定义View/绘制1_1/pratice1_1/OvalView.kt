package com.justcode.hxl.viewutil.自定义View.绘制1_1.pratice1_1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class OvalView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr){

    val paint = Paint()
    init {
        paint.color = Color.BLUE
        paint.isAntiAlias = true
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val rectf = RectF(100f,50f,300f,150f)
        canvas.drawOval(rectf,paint)
    }
}