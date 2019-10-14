package com.justcode.hxl.viewutil.自定义控件.动画进阶

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.Animation
import com.justcode.hxl.viewutil.R

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

/**
 *  length
 */
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

        canvas.translate(200f, 200f)
        path.addRect(-50f, -50f, 50f, 50f, Path.Direction.CW)
        canvas.drawPath(path, paint)
        path.addRect(-100f, -100f, 100f, 100f, Path.Direction.CW)
        canvas.drawPath(path, paint)
        path.addRect(-150f, -150f, 150f, 150f, Path.Direction.CW)
        canvas.drawPath(path, paint)

        // measure1.nextContour() 跳转到下一条曲线的函数 跳转成功返回true，跳转失败，返回false

        measure.setPath(path, false)
        do {
            Log.d("PathMeasureSimpleView", "nextContour--${measure.length}")
        } while (measure.nextContour())

    }
}


/**
 * getSegment 介绍
 */
class PathMeasureSimpleView3 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val path = Path()
    val path1 = Path()
    val path2 = Path()
    val paint = Paint()
    val measure = PathMeasure()
    val measure1 = PathMeasure()
    val measure2 = PathMeasure()

    init {
        paint.color = Color.BLACK
        paint.strokeWidth = 8f
        paint.style = Paint.Style.STROKE

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        setLayerType(LAYER_TYPE_SOFTWARE, null)

        /**
         *  getSegment 函数
         *  截取 path的某个片段
         */
//        measure.getSegment(float startD, float stopD, Path dst, boolean startWithMoveTo)
        // 通过 startD 和 stopD 来控制截取的长度
        // 截取后的path保存到 dst中，
        // startWithMoveTo 表示 起点是否使用 moveTo,将路径的新起点地道结果path的起始点， 通常是true
        // 以保证每次截取的path都是正常，完成的。

        canvas.translate(100f, 100f)
        //保存一下当前画布
        canvas.save()

        // Path.Direction.CW 这个是顺时针，可以改为逆时针，结果就是逆时针截取了
        path.addRect(-50f, -50f, 50f, 50f, Path.Direction.CW)
        canvas.drawPath(path, paint)
        val dst = Path()
        measure.setPath(path, false)
        measure.getSegment(0f, 150f, dst, true)
        canvas.translate(150f, 0f)
        canvas.drawPath(dst, paint)


        // dst 不为空时 会绘制截取的和原本的path 但是需要关闭硬件加速，才能正常显示
        canvas.translate(150f, 0f)
        path1.addRect(-50f, -50f, 50f, 50f, Path.Direction.CW)
        //dst 路径有路径的情况
        val dst1 = Path()
        dst1.lineTo(10f, 100f)
        measure1.setPath(path1, false)
        measure1.getSegment(0f, 150f, dst1, true)
        canvas.drawPath(dst1, paint)

        //  startWithMoveTo 为false  和上一个相比，其实就是首位相连之后的结果  lineTo(10f,100f) 的尾部和  measure2.getSegment 的头相连了
        canvas.translate(0f, 200f)
        path2.addRect(-50f, -50f, 50f, 50f, Path.Direction.CW)
        val dst2 = Path()
        dst2.lineTo(10f, 100f)
        measure2.setPath(path2, false)
        measure2.getSegment(0f, 150f, dst2, false)
        canvas.drawPath(dst2, paint)

    }
}

/**
 *  getSegment 函数运用
 */
class PathMeasureSimpleView4 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val dstPath = Path()
    val dstPath1 = Path()
    val circlePath = Path()
    val circlePath1 = Path()
    val paint = Paint()
    val measure = PathMeasure()
    val measure1 = PathMeasure()
    var curAnimValue = 0f

    init {
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 4f
        paint.color = Color.BLACK
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        circlePath.addCircle(80f, 80f, 50f, Path.Direction.CW)
        circlePath1.addCircle(200f, 80f, 50f, Path.Direction.CW)
        measure.setPath(circlePath, true)
        measure1.setPath(circlePath1, true)


        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.repeatCount = ValueAnimator.INFINITE
        animator.addUpdateListener {
            curAnimValue = it.animatedValue as Float
            invalidate()

        }
        animator.duration = 2000
        animator.start()

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawColor(Color.WHITE)
        val stop = measure.length * curAnimValue
        //清除路径
        dstPath.reset()
        measure.getSegment(0f, stop, dstPath, true)
        canvas.drawPath(dstPath, paint)

        //更好得效果
        //清除路径
        dstPath1.reset()
        val length = measure1.length
        val stop1 = length * curAnimValue
        val start1 = (stop1 - ((0.5f - Math.abs(curAnimValue - 0.5f)) * length))
        measure1.getSegment(start1, stop1, dstPath1, true)
        canvas.drawPath(dstPath1, paint)


    }
}

class GetPosTanView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val circlePath = Path()
    val dstPath = Path()
    val paint = Paint()
    val measure = PathMeasure()
    var curAnimaValue = 0f
    lateinit var arrawBitmap: Bitmap
    private val pos = FloatArray(2)
    private val tan = FloatArray(2)

    init {
        paint.isAntiAlias = true
        paint.color = Color.BLACK
        paint.strokeWidth = 4f
        paint.style = Paint.Style.STROKE
        circlePath.addCircle(100f, 100f, 50f, Path.Direction.CW)
        measure.setPath(circlePath, true)
        arrawBitmap = BitmapFactory.decodeResource(resources, R.drawable.arraw)

        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.addUpdateListener {
            curAnimaValue = it.animatedValue as Float
            invalidate()
        }
        animator.duration = 2000
        animator.repeatCount = Animation.INFINITE
        animator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawColor(Color.WHITE)
        val lenth = measure.length
        val stop = lenth * curAnimaValue

        dstPath.reset()
        measure.getSegment(0f, stop, dstPath, true)
        canvas.drawPath(dstPath, paint)

        //旋转箭头 但是，会发现有一个bug
        measure.getPosTan(stop, pos, tan)
        val degrees = Math.toDegrees(Math.atan2(tan[1].toDouble(), tan[0].toDouble()))
        val matrix = Matrix()
//        matrix.postRotate(degrees.toFloat(), (arrawBitmap.width/2).toFloat(), (arrawBitmap.height/2).toFloat())
//        matrix.postTranslate(pos[0],pos[1])
//        canvas.drawBitmap(arrawBitmap,matrix,paint)

        //修改bug之后
//        matrix.postRotate(degrees.toFloat(), (arrawBitmap.width / 2).toFloat(), (arrawBitmap.height / 2).toFloat())
//        matrix.postTranslate(pos[0] - arrawBitmap.width / 2, pos[1] - arrawBitmap.height / 2)
//        canvas.drawBitmap(arrawBitmap, matrix, paint)

        //通过计算方位角实现
        measure.getMatrix(stop, matrix, PathMeasure.POSITION_MATRIX_FLAG or PathMeasure.TANGENT_MATRIX_FLAG)
        matrix.preTranslate((-arrawBitmap.width / 2).toFloat(), (-arrawBitmap.height / 2).toFloat())
        canvas.drawBitmap(arrawBitmap, matrix, paint)

        //   measure.getMatrix(stop, matrix, PathMeasure.POSITION_MATRIX_FLAG or PathMeasure.TANGENT_MATRIX_FLAG)
        // distance: 距离path起点的长度
        // matrix: 根据 flags封装好的matrix会根据flags的设置而存入不同的内容
        // flags: 用于指定哪些内容存入matrix中

    }

}

class AliPayView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var next = false
    val circlePath = Path()
    val dstPath = Path()
    val paint = Paint()
    val measure = PathMeasure()
    var curAnimaValue = 0f

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        paint.isAntiAlias = true
        paint.color = Color.BLACK
        paint.strokeWidth = 4f
        paint.style = Paint.Style.STROKE

        circlePath.addCircle(100f, 100f, 50f, Path.Direction.CW)


        //圆形内画一个勾
        circlePath.moveTo(100f - 50f / 2, 100f)
        circlePath.lineTo(100f, 100f + 50f / 2)
        circlePath.lineTo(100f + 50f / 2, 100f - 50f / 3)

        measure.setPath(circlePath, false)


        val animator = ValueAnimator.ofFloat(0f, 2f)
        animator.addUpdateListener {
            curAnimaValue = it.animatedValue as Float
            invalidate()
        }
        animator.duration = 4000
        animator.repeatCount = Animation.INFINITE
        animator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (curAnimaValue < 1f) {
            val stop = measure.length * curAnimaValue
            Log.d("AliPayView_", "stop:${stop}--lenth:${measure.length}")
            measure.getSegment(0f, stop, dstPath, true)
        } else {
            if (!next) {
                next = true
                measure.getSegment(0f, measure.length, dstPath, true)
                measure.nextContour()
            }
            val stop = measure.length * (curAnimaValue - 1f)
            measure.getSegment(0f, stop, dstPath, true)
        }


        canvas.drawPath(dstPath, paint)
    }
}