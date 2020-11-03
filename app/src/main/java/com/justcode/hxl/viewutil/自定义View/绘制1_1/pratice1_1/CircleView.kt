package com.justcode.hxl.viewutil.自定义View.绘制1_1.pratice1_1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val paint1 = Paint()
    val paint2 = Paint()
    val paint3 = Paint()
    val paint4 = Paint()

    init {
        paint1.color = Color.BLACK
        paint1.isAntiAlias = true
        paint1.style = Paint.Style.FILL

        paint2.color = Color.BLACK
        paint2.isAntiAlias = true
        paint2.style = Paint.Style.STROKE
        paint2.strokeWidth = 2f

        paint3.color = Color.BLUE
        paint3.isAntiAlias = true
        paint3.style = Paint.Style.FILL

        paint4.color = Color.BLACK
        paint4.isAntiAlias = true
        paint4.style = Paint.Style.STROKE
        paint4.strokeWidth = 30f


    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(250f, 110f, 100f, paint1)
        canvas.drawCircle(500f, 110f, 100f, paint2)
        canvas.drawCircle(250f, 330f, 100f, paint3)
        canvas.drawCircle(500f, 330f, 100f, paint4)
    }
}