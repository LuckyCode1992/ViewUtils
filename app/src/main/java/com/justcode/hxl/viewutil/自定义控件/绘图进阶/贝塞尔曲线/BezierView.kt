package com.justcode.hxl.viewutil.自定义控件.绘图进阶.贝塞尔曲线

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


/**
 * Android 中 path只支持 一阶，二阶，三阶
 *
 * B(t) = (1-t)P0+p1,t[0,1]
 *
 * B(t) = (1-t)*(1-t)*P0+2*t*(1-t)P1+t*t* P2 ,t[0,1]
 *
 * B(t) = P0*（1-t)*(1-t)*(1-t)+3*P1*t*(1-t)*(1-t)+3*P2*t*t*(1-t)+P3*t*t*t,t[0,1]
 *
 */

class BezierView2 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val pathCircle = Path()
    val pathLine = Path()
    val path = Path()
    val paint = Paint()
    val x1 = 50f
    val x2 = 200f
    val x3 = 400f
    val y1 = 50f
    val y2 = 200f
    val y3 = 50f

    val pathMeasure = PathMeasure()
    val dstPath = Path()
    var curAnimValue = 0f
    lateinit var animator: ValueAnimator

    init {

        //画点
        pathCircle.addCircle(x1, y1, 5f, Path.Direction.CW)
        pathCircle.addCircle(x2, y2, 5f, Path.Direction.CW)
        pathCircle.addCircle(x3, y3, 5f, Path.Direction.CW)

        //画线
        pathLine.moveTo(x1, y1)
        pathLine.lineTo(x2, y2)
        pathLine.lineTo(x3, y3)

        //画贝塞尔曲线 二阶
        path.moveTo(x1, y1)
        path.quadTo(x2, y2, x3, y3)

        //动画展示
        pathMeasure.setPath(path, false)
        animator = ValueAnimator.ofFloat(0f, 1f)
        animator.addUpdateListener {
            curAnimValue = it.animatedValue as Float
            invalidate()

        }
        animator.duration = 2000

    }

    fun startAnim() {
        animator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true
        paint.color = Color.BLACK
        canvas.drawPath(pathCircle, paint)

        paint.reset()
        paint.strokeWidth = 2f
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        paint.color = Color.GREEN
        canvas.drawPath(pathLine, paint)

        paint.reset()
        paint.strokeWidth = 2f
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        paint.color = Color.YELLOW
        canvas.drawPath(path, paint)


        paint.color = Color.RED
        val stop = pathMeasure.length * curAnimValue
        //清除路径
        dstPath.reset()
        pathMeasure.getSegment(0f, stop, dstPath, true)
        canvas.drawPath(dstPath, paint)


    }

}

class BezierView3 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val pathCircle = Path()
    val pathLine = Path()
    val path = Path()
    val paint = Paint()
    val x1 = 100f
    val y1 = 260f

    val x2 = 80f
    val y2 = 50f

    val x3 = 400f
    val y3 = 80f

    val x4 = 600f
    val y4 = 270f


    val pathMeasure = PathMeasure()
    val dstPath = Path()
    var curAnimValue = 0f
    lateinit var animator: ValueAnimator

    init {

        //画点
        pathCircle.addCircle(x1, y1, 5f, Path.Direction.CW)
        pathCircle.addCircle(x2, y2, 5f, Path.Direction.CW)
        pathCircle.addCircle(x3, y3, 5f, Path.Direction.CW)
        pathCircle.addCircle(x4, y4, 5f, Path.Direction.CW)


        //画线
        pathLine.moveTo(x1, y1)
        pathLine.lineTo(x2, y2)
        pathLine.lineTo(x3, y3)
        pathLine.lineTo(x4, y4)


        //画贝塞尔曲线 三阶
        path.moveTo(x1, y1)
        path.cubicTo(x2, y2, x3, y3, x4, y4)

        //动画展示
        pathMeasure.setPath(path, false)
        animator = ValueAnimator.ofFloat(0f, 1f)
        animator.addUpdateListener {
            curAnimValue = it.animatedValue as Float
            invalidate()

        }
        animator.duration = 2000

    }

    fun startAnim() {
        animator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true
        paint.color = Color.BLACK
        canvas.drawPath(pathCircle, paint)

        paint.reset()
        paint.strokeWidth = 2f
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        paint.color = Color.GREEN
        canvas.drawPath(pathLine, paint)

        paint.reset()
        paint.strokeWidth = 2f
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        paint.color = Color.YELLOW
        canvas.drawPath(path, paint)


        paint.color = Color.RED
        val stop = pathMeasure.length * curAnimValue
        //清除路径
        dstPath.reset()
        pathMeasure.getSegment(0f, stop, dstPath, true)
        canvas.drawPath(dstPath, paint)


    }

}

class BezierViewDemo @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val path = Path()
    val paint = Paint()


    val pathMeasure = PathMeasure()
    val dstPath = Path()
    var curAnimValue = 0f
    lateinit var animator: ValueAnimator

    init {
        paint.strokeWidth = 2f
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        paint.color = Color.YELLOW

        // 画一个波浪线
        path.moveTo(100f, 200f)
        path.quadTo(200f, 100f, 300f, 200f)
        path.quadTo(400f, 300f, 500f, 200f)

        //动画展示
        pathMeasure.setPath(path, false)
        animator = ValueAnimator.ofFloat(0f, 1f)
        animator.addUpdateListener {
            curAnimValue = it.animatedValue as Float
            invalidate()

        }
        animator.duration = 2000

    }

    fun startAnim() {
        animator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        paint.color = Color.YELLOW
        canvas.drawPath(path, paint)

        paint.color = Color.RED
        val stop = pathMeasure.length * curAnimValue
        //清除路径
        dstPath.reset()
        pathMeasure.getSegment(0f, stop, dstPath, true)
        canvas.drawPath(dstPath, paint)


    }

}