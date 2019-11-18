package com.justcode.hxl.viewutil.自定义控件.第一章_绘图基础

import android.content.Context
import android.graphics.*
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

        //画点
        val paint1 = Paint()
        paint1.strokeWidth = 20f
        paint1.style = Paint.Style.STROKE
        paint1.color = Color.BLACK
        canvas.drawPoint(200f, 200f, paint1)

        //画线
        val paint2 = Paint()
        paint2.strokeWidth
        paint2.style = Paint.Style.STROKE
        paint2.color = Color.WHITE
        canvas.drawLine(200f, 200f, 300f, 200f, paint2)

        //路径
        val paint3 = Paint()
        paint3.color = Color.GREEN
        paint3.style = Paint.Style.STROKE
        paint3.strokeWidth = 5f
        val path = Path()
        //设置路径起始点
        path.moveTo(10f, 400f)
        //画线
        path.lineTo(10f, 500f)
        //画线
        path.lineTo(300f, 500f)
        //闭环  自动连接成闭环
        path.close()
        canvas.drawPath(path, paint3)

        //弧线路径
        val path2 = Path()
        path.moveTo(400f, 400f)
        val recf = RectF(500f, 400f, 700f, 490f)
        path2.arcTo(recf, 0f, 90f)
//        canvas.drawRect(recf, paint3)
        canvas.drawPath(path2, paint3)


    }

}