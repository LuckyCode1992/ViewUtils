package com.justcode.hxl.viewutil.自定义控件.绘图基础

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class BasisView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //画布背景
        canvas.drawColor(Color.CYAN)


        //设置画笔基本属性
        val paint = Paint()
        paint.color = Color.RED
        /**
         * STROKE 是描边
         * FILL_AND_STROKE 描边加填充
         * FILL 填充
         */
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 50f


        //画布，绘制成品
        canvas.drawCircle(200f, 200f, 100f, paint)

        val paint1 = Paint()
        paint1.strokeWidth = 20f
        paint1.style = Paint.Style.STROKE
        paint1.color = Color.BLACK
        canvas.drawPoint(200f,200f,paint1)

        val paint2 = Paint()
        paint2.strokeWidth
        paint2.style = Paint.Style.STROKE
        paint2.color = Color.WHITE
        canvas.drawLine(200f,200f,300f,200f,paint2)

    }

}