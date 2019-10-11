package com.justcode.hxl.viewutil.自定义控件.动画进阶

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

class PathMeasureSimpleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val path = Path()
    val paint = Paint()
    val measure1 = PathMeasure()
    val measure2 = PathMeasure()

    init {
        paint.color = Color.BLACK
        paint.strokeWidth = 8f
        paint.style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.translate(50f, 50f)

        path.moveTo(0f, 0f)
        path.lineTo(0f, 100f)
        path.lineTo(100f, 100f)
        path.lineTo(100f, 0f)

        measure1.setPath(path, false)
        measure2.setPath(path, true)
        //获取 path 长度
        Log.d("PathMeasureSimpleView", "forceClosed=false--${measure1.length}") // 300
        Log.d("PathMeasureSimpleView", "forceClosed=true--${measure2.length}")  //400
        //如果forceClosed==false 测量当前path状态的长度
        //如果forceClosed==true 测量的是path闭合的长度，且不管path是否闭合

        // measure1.isClosed 判断测量Path 是否计算闭合



        canvas.drawPath(path, paint)


    }
}

class PathMeasureSimpleView2 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val path = Path()
    val paint = Paint()
    val measure = PathMeasure()
    init {
        paint.color = Color.BLACK
        paint.strokeWidth = 8f
        paint.style = Paint.Style.STROKE

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.translate(200f,200f)
        path.addRect(-50f,-50f,50f,50f,Path.Direction.CW)
        canvas.drawPath(path,paint)
        path.addRect(-100f,-100f,100f,100f,Path.Direction.CW)
        canvas.drawPath(path,paint)
        path.addRect(-150f,-150f,150f,150f,Path.Direction.CW)
        canvas.drawPath(path,paint)

        // measure1.nextContour() 跳转到下一条曲线的函数 跳转成功返回true，跳转失败，返回false

        measure.setPath(path,false)
        do {
            Log.d("PathMeasureSimpleView", "nextContour--${measure.length}")
        }while (measure.nextContour())

    }
}