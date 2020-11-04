package com.justcode.hxl.viewutil.自定义View.绘制1_1.pratice1_1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View

class PointView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val point1 = PointF()
    val point2 = PointF()
    val point3 = PointF()

    val paint1 = Paint()
    val paint2 = Paint()
    val paint3 = Paint()

    init {
        paint1.color = Color.BLUE
        paint1.isAntiAlias = true
        paint1.strokeCap = Paint.Cap.ROUND
        paint1.strokeWidth = 20f

        paint2.color = Color.BLUE
        paint2.isAntiAlias = true
        paint2.strokeCap = Paint.Cap.BUTT
        paint2.strokeWidth = 20f

        paint3.color = Color.BLUE
        paint3.isAntiAlias = true
        paint3.strokeCap = Paint.Cap.SQUARE
        paint3.strokeWidth = 20f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        point1.x = 100f
        point1.y = 100f

        point2.x = 200f
        point2.y = 100f

        point3.x = 300f
        point3.y = 100f

        canvas.drawPoint(point1.x, point1.y, paint1)
        canvas.drawPoint(point2.x, point2.y, paint2)
        canvas.drawPoint(point3.x, point3.y, paint3)

    }
}