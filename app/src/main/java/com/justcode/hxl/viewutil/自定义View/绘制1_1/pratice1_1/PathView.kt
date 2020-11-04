package com.justcode.hxl.viewutil.自定义View.绘制1_1.pratice1_1

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class PathView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val paint = Paint()

    val rectF1 = RectF()
    val rectF2 = RectF()

    val path = Path()

    init {
        paint.isAntiAlias = true
        paint.color = Color.RED
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        rectF1.set(340f, 200f, 540f, 400f)
        rectF2.set(540f, 200f, 740f, 400f)

//        canvas.drawArc(rectF1, 105f, 255f, true, paint)
//        canvas.drawArc(rectF2, -180f, 255f, true, paint)

        path.addArc(rectF1,130f,255f)
        path.arcTo(rectF2,180f,230f,false)
        path.lineTo(540f, 542f)

        canvas.drawPath(path,paint)

    }
}