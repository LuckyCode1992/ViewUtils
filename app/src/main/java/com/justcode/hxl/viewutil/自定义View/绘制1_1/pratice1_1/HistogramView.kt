package com.justcode.hxl.viewutil.自定义View.绘制1_1.pratice1_1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class HistogramView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val paint1 = Paint()
    val paint2 = Paint()
    val paint3 = Paint()

    val widthHistory = 100f

    init {
        paint1.color = Color.WHITE
        paint1.alpha = 125
        paint1.isAntiAlias = true
        paint1.style = Paint.Style.STROKE
        paint1.strokeWidth = 4f

        paint2.color = Color.GREEN
        paint2.alpha = 200
        paint2.isAntiAlias = true
        paint2.strokeCap = Paint.Cap.SQUARE
        paint2.style = Paint.Style.FILL

        paint3.color = Color.WHITE
        paint3.isAntiAlias = true
        paint3.style = Paint.Style.STROKE
        paint3.textAlign = Paint.Align.CENTER
        paint3.textSize = 30f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawLine(300f, 200f, 300f, 800f, paint1)
        canvas.drawLine(300f, 800f, 1300f, 800f, paint1)

        canvas.drawRect(300f + 20f, 790f, 300f + 20f + widthHistory, 800f, paint2)
        val path1 = Path()
        path1.moveTo(300f + 20f, 800f)
        path1.lineTo(300f + 20f + widthHistory, 800f)
        canvas.drawTextOnPath("Faer", path1, 0f, 50f, paint3)
        canvas.drawRect(
            300f + 20f * 2 + widthHistory,
            700f,
            300f + 20f * 2 + widthHistory * 2,
            800f,
            paint2
        )
        val path2 = Path()
        path2.moveTo(300f + 20f * 2 + widthHistory, 800f)
        path2.lineTo(300f + 20f * 2 + widthHistory * 2, 800f)
        canvas.drawTextOnPath("GB", path2, 0f, 50f, paint3)
        canvas.drawRect(
            300f + 20f * 3 + widthHistory * 2,
            600f,
            300f + 20f * 3 + widthHistory * 3,
            800f,
            paint2
        )
        val path3 = Path()
        path3.moveTo(300f + 20f * 3 + widthHistory * 2, 800f)
        path3.lineTo(300f + 20f * 3 + widthHistory * 3, 800f)
        canvas.drawTextOnPath("IC8", path3, 0f, 50f, paint3)
        canvas.drawRect(
            300f + 20f * 4 + widthHistory * 3,
            500f,
            300f + 20f * 4 + widthHistory * 4,
            800f,
            paint2
        )
        val path4 = Path()
        path4.moveTo(300f + 20f * 4 + widthHistory * 3, 800f)
        path4.lineTo(300f + 20f * 4 + widthHistory * 4, 800f)
        canvas.drawTextOnPath("JB", path4, 0f, 50f, paint3)
        canvas.drawRect(
            300f + 20f * 5 + widthHistory * 4,
            400f,
            300f + 20f * 5 + widthHistory * 5,
            800f,
            paint2
        )
        canvas.drawRect(
            300f + 20f * 6 + widthHistory * 5,
            300f,
            300f + 20f * 6 + widthHistory * 6,
            800f,
            paint2
        )
        canvas.drawRect(
            300f + 20f * 7 + widthHistory * 6,
            200f,
            300f + 20f * 7 + widthHistory * 7,
            800f,
            paint2
        )


    }

}