package com.justcode.hxl.viewutil.自定义View.绘制1_1.pratice1_1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class ArcView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val paint1 = Paint()
    val paint2 = Paint()
    val paint3 = Paint()
    val rectF = RectF()

    init {
        paint1.strokeWidth = 2f
        paint1.color = Color.BLUE
        paint1.isAntiAlias = true
        paint1.style = Paint.Style.FILL

        paint2.strokeWidth = 2f
        paint2.color = Color.BLACK
        paint2.isAntiAlias = true
        paint2.style = Paint.Style.STROKE

        paint3.strokeWidth = 2f
        paint3.color = Color.CYAN
        paint3.isAntiAlias = true
        paint3.style = Paint.Style.FILL

        rectF.left = 100f
        rectF.right = 500f
        rectF.top = 100f
        rectF.bottom = 400f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawArc(rectF, 250f, 100f, true, paint1)
        canvas.drawArc(rectF, 190f, 50f, false, paint2)
        canvas.drawArc(rectF, 20f, 140f, false, paint3)
    }
}