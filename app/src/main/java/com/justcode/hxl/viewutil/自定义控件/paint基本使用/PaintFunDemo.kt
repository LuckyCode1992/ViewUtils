package com.justcode.hxl.viewutil.自定义控件.paint基本使用

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class PaintFunDemo @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val paint = Paint()
    val path = Path()
    val pathCircle = Path()
    val x1 = 100f
    val y1 = 100f
    val x2 = 150f
    val y2 = 150f
    val x3 = 200f
    val y3 = 120f
    val x4 = 250f
    val y4 = 140f
    val x5 = 300f
    val y5 = 90f

    init {

        paint.isAntiAlias = true
        paint.color = Color.GREEN
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f

        path.moveTo(x1, y1)
        path.lineTo(x2, y2)
        path.lineTo(x3, y3)
        path.lineTo(x4, y4)
        path.lineTo(x5, y5)

        pathCircle.addCircle(5f, 5f, 3f, Path.Direction.CW)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        /**
         * 画不同的路径样式
         */
        //原始路径
        paint.pathEffect = null
        canvas.drawPath(path, paint)


        //cornerPathEffect 圆形拐角效果
        canvas.translate(0f, 100f)
        val cornerPathEffect = CornerPathEffect(10f)
        paint.pathEffect = cornerPathEffect
        canvas.drawPath(path, paint)

        //dashPathEffect 虚线效果
        canvas.translate(0f, 100f)
        val dashPathEffect = DashPathEffect(floatArrayOf(20f, 10f, 5f, 10f), 10f)
        paint.pathEffect = dashPathEffect
        canvas.drawPath(path, paint)

        //DiscretePathEffect 离散路径效果
        canvas.translate(0f, 100f)
        val discretePathEffect = DiscretePathEffect(3f, 5f)
        paint.pathEffect = discretePathEffect
        canvas.drawPath(path, paint)

        //PathDashPathEffect 印章路径效果
        canvas.translate(0f, 100f)
        val pathDashPathEffect = PathDashPathEffect(pathCircle, 12f, 10f, PathDashPathEffect.Style.TRANSLATE)
        paint.pathEffect = pathDashPathEffect
        canvas.drawPath(path, paint)

        //ComposePathEffect 合并路径效果 下面是虚线和拐角
        canvas.translate(0f, 100f)
        val composePathEffect = ComposePathEffect(cornerPathEffect, dashPathEffect)
        paint.pathEffect = composePathEffect
        canvas.drawPath(path, paint)

        //SumPathEffect 合并路径效果
        canvas.translate(0f, 100f)
        val sumPathEffect = SumPathEffect(cornerPathEffect, dashPathEffect)
        paint.pathEffect = sumPathEffect
        canvas.drawPath(path, paint)

        /**
         * 线帽
         */
        paint.pathEffect = null
        //无线帽
        canvas.translate(0f, 100f)
        paint.strokeCap = Paint.Cap.BUTT
        canvas.drawLine(100f, 100f, 300f, 100f, paint)

        //圆形线帽
        canvas.translate(0f, 100f)
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawLine(100f, 100f, 300f, 100f, paint)

        //方形线帽
        canvas.translate(0f, 100f)
        paint.strokeCap = Paint.Cap.SQUARE
        canvas.drawLine(100f, 100f, 300f, 100f, paint)
    }
}