package com.justcode.hxl.viewutil.自定义控件.第一章_绘图基础

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class BasisDemoTextView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val paint = Paint()

    init {
        //普通设置
        paint.strokeWidth = 5f
        paint.isAntiAlias = true //抗据此，没有毛边，但是绘制速度会变慢
//        paint.style = Paint.Style.FILL
        //文字对齐方式
//        paint.textAlign = Paint.Align.CENTER
        paint.textSize = 30f
        paint.color = Color.BLACK

        //文字样式设置
        paint.isFakeBoldText = true //加粗
        paint.isUnderlineText = true//下划线
        paint.textSkewX = -0.25f//斜体，普通斜体，就是-0.25
        paint.isStrikeThruText = true//删除线

        //其他设置
        paint.textScaleX = 1.5f//水平拉伸


    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.style = Paint.Style.FILL
        paint.textAlign = Paint.Align.CENTER
        canvas.drawText("窗前明月光", 200f, 30f, paint)

        paint.reset()
        paint.textScaleX = 1.5f//水平拉伸
        paint.strokeWidth = 1f
        paint.isAntiAlias = true //抗据此，没有毛边，但是绘制速度会变慢
        paint.isFakeBoldText = true //加粗
        paint.textSize = 30f
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.textAlign = Paint.Align.LEFT
        canvas.drawText("窗前明月光", 200f, 130f, paint)

        paint.reset()
        paint.textScaleX = 1.5f//水平拉伸
        paint.strokeWidth = 2f
        paint.isAntiAlias = true //抗据此，没有毛边，但是绘制速度会变慢
        paint.isFakeBoldText = true //加粗
        paint.textSize = 30f
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.textAlign = Paint.Align.RIGHT
        canvas.drawText("窗前明月光", 200f, 230f, paint)

        /**
         * 逐个指定位置的文字
         */

        paint.reset()
        paint.style = Paint.Style.FILL
        paint.textAlign = Paint.Align.LEFT
        paint.textSize = 30f
        paint.color = Color.RED
        val pos = floatArrayOf(200f, 330f,
                230f, 360f,
                260f, 390f,
                290f, 420f,
                320f, 450f)
        canvas.drawPosText("窗前明月光", pos, paint)

        /**
         * 沿着路径绘制文字
         */
        paint.reset()
        paint.color = Color.BLUE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        paint.textSize = 45f


        //画两条路径
        val path1 = Path()
        path1.addCircle(300f, 700f, 150f, Path.Direction.CCW) //Path.Direction.CCW 逆时针
        canvas.drawPath(path1, paint)

        val path2 = Path()
        path2.addCircle(300f, 1200f, 150f, Path.Direction.CCW) //Path.Direction.CW 顺时针
        canvas.drawPath(path2, paint)

        val test = "窗前明月光，疑是地上霜。"

        paint.color = Color.GREEN

        canvas.drawTextOnPath(test,path1,0f,0f,paint)
        canvas.drawTextOnPath(test,path2,50f,50f,paint)


    }
}