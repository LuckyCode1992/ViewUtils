package com.justcode.hxl.viewutil.自定义控件.paint基本使用

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

class Text1 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint()
    val baseLineX = 100f
    val baseLineY = 100f
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.isAntiAlias = true

        /**
         *  drawText 参数y是基线的y值
         *  知道x坐标，基线，文字大小，那么文字的位置就确定了
         *  文字绘制遵循四线格 ，可以看看读书时候的英文作业本
         */
        // 基线
        paint.color = Color.RED
        canvas.drawLine(baseLineX, baseLineY, 3000f, baseLineY, paint)

        //文字
        paint.color = Color.GREEN
        paint.textSize = 30f
        canvas.drawText("hello im lucky 我是lucky", baseLineX, baseLineY, paint)

        /**
         * paint.setTextAlign:
         * left,center,right 三种对齐方式
         * 相对文字绘制起点进行对齐，效果，如下。
         *
         */

        canvas.translate(0f, 100f)
        paint.color = Color.RED
        canvas.drawLine(baseLineX, baseLineY, 3000f, baseLineY, paint)
        paint.color = Color.BLUE
        paint.textSize = 30f
        paint.textAlign = Paint.Align.LEFT
        canvas.drawText("hello im lucky ", baseLineX, baseLineY, paint)

        canvas.translate(0f, 100f)
        paint.color = Color.RED
        canvas.drawLine(baseLineX, baseLineY, 3000f, baseLineY, paint)
        paint.color = Color.BLUE
        paint.textSize = 30f
        paint.textAlign = Paint.Align.CENTER
        canvas.drawText("hello im lucky ", baseLineX, baseLineY, paint)

        canvas.translate(0f, 100f)
        paint.color = Color.RED
        canvas.drawLine(baseLineX, baseLineY, 3000f, baseLineY, paint)
        paint.color = Color.BLUE
        paint.textSize = 30f
        paint.textAlign = Paint.Align.RIGHT
        canvas.drawText("hello im lucky ", baseLineX, baseLineY, paint)


    }


}

class Text2 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint()
    val baseLineX = 0f
    val baseLineY = 100f
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.isAntiAlias = true
        /**
         * 绘图四格，和fontMetrics
         * 四格，其实是4条线：ascent,descent,top,bottom
         *  ascent: 系统推荐，绘制单个字符时，字符应当的最高高度所在线
         *  descent: 系统推荐，字符应当的最低高度所在线
         *  top: 可绘制的最高高度所在线
         *  bottom：可绘制的最低高度所在线
         *
         *  bottom和descent十分接近
         */
        canvas.translate(0f, 100f)
        paint.color = Color.RED
        canvas.drawLine(baseLineX, baseLineY, 3000f, baseLineY, paint)
        paint.color = Color.BLUE
        paint.textSize = 170f
        paint.textAlign = Paint.Align.LEFT
        canvas.drawText("lucky ", baseLineX, baseLineY, paint)

        val fontMetrics = paint.fontMetrics
        //真正的四格线，还需要进行如下计算
        val ascent = baseLineY + fontMetrics.ascent
        val descent = baseLineY + fontMetrics.descent
        val top = baseLineY + fontMetrics.top
        val bottom = baseLineY + fontMetrics.bottom
        paint.color = Color.GREEN
        canvas.drawLine(baseLineX, top, 3000f, top, paint)

        paint.color = Color.parseColor("#ff1234")
        canvas.drawLine(baseLineX, bottom, 3000f, bottom, paint)

        paint.color = Color.YELLOW
        canvas.drawLine(baseLineX, ascent, 3000f, ascent, paint)

        paint.color = Color.BLACK
        canvas.drawLine(baseLineX, descent, 3000f, descent, paint)

//        Log.d("text_demo","2:ascent:${ascent}-descent:${descent}-top:${top}-bottom:${bottom}")
    }

}

class Text3 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint()
    val baseLineX = 0f
    val top = 80f
    val center = 150f
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.textSize = 120f
        paint.textAlign = Paint.Align.LEFT


        /**
         * 指定字的左上角，进行写字
         */

        //画top线
        paint.color = Color.RED
        canvas.drawLine(baseLineX, top, 3000f, top, paint)
        //获取字的度量
        val fm = paint.fontMetricsInt
        //计算处baseLineY
        val baseLineY = top - fm.top
        paint.color = Color.GREEN
        canvas.drawLine(baseLineX, baseLineY, 3000f, baseLineY, paint)
        paint.color = Color.BLACK
        canvas.drawText("lucky", baseLineX, baseLineY, paint)


        /**
         * 给定中间位置，写字
         */
        canvas.translate(0f, 300f)
        //画中心线
        paint.color = Color.RED
        canvas.drawLine(baseLineX, center, 3000f, center, paint)
        //获取字的度量
        val fontM = paint.fontMetrics
        //计算距离
        /**
         *  分析一下
         *  center = (top+bottom)/2
         *  top = baselinY+fontM.top
         *  bottom = baseLineY+fontBottom
         *  推导
         *  center = ( baselinY+fontM.top +baseLineY+fontM.bottom)/2
         *  2*center = 2*baseLineY+fontM.top+ontM.bottom
         *  baseLineY = center-(fontM.top+ontM.bottom)/2
         */
        val baseLineYy = center - (fontM.top + fontM.bottom) / 2
        paint.color = Color.BLACK
        canvas.drawText("Lucky",baseLineX,baseLineYy,paint)

        paint.color = Color.GREEN
        canvas.drawLine(baseLineX,baseLineYy,3000f,baseLineYy,paint)


    }
}