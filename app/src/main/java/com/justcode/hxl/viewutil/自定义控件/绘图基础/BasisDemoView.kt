package com.justcode.hxl.viewutil.自定义控件.绘图基础

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class BasisDemoView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    var radarPaint = Paint()
    var valuePaint = Paint()

    var radius: Double = 0.0//半径
    var centerX: Int = 0
    var centerY: Int = 0
    val count: Int = 6//六芒星
    //计算出每个夹角的度数
    val angle: Double = Math.PI * 2 / count

    var data = doubleArrayOf(2.0, 3.0, 5.0, 4.0, 1.0, 2.0)
    val maxValue = 6.0

    init {
        radarPaint.style = Paint.Style.STROKE
        radarPaint.color = Color.GREEN
        radarPaint.strokeWidth = 3f

        valuePaint.color = Color.BLUE
        valuePaint.style = Paint.Style.FILL

    }

    /**
     * 获取当前控件大小
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {

        //半径，取长宽中的最小值/2 在两边留点空隙
        radius = Math.min(w, h) / 2 * 0.9
        //中心坐标
        centerX = w / 2
        centerY = h / 2
        postInvalidate()
        super.onSizeChanged(w, h, oldw, oldh)
    }

    /**
     * 绘制一个蜘蛛网状图
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //绘制蜘蛛网格
        drawPolygon(canvas)

        //绘制网格中线
        drawLines(canvas)

        //绘制数据图
        drawRegion(canvas)
    }


    private fun drawRegion(canvas: Canvas) {
        val path = Path()
        valuePaint.alpha = 127
        for (i in 0 until count) {
            val percent = data[i] / maxValue
            val x = centerX + radius * Math.cos(angle * i) * percent
            val y = centerY + radius * Math.sin(angle * i) * percent
            if (i == 0) {
                path.moveTo(x.toFloat(), y.toFloat())
            } else {
                path.lineTo(x.toFloat(), y.toFloat())
            }
            //绘制圆点
            canvas.drawCircle(x.toFloat(), y.toFloat(), 10f, valuePaint)
        }
        valuePaint.style = Paint.Style.FILL_AND_STROKE
        canvas.drawPath(path, valuePaint)

    }

    private fun drawLines(canvas: Canvas) {
        val path = Path()
        for (i in 0 until count) {
            path.reset()
            path.moveTo(centerX.toFloat(), centerY.toFloat())
            val x = centerX + radius * Math.cos(angle * i)
            val y = centerY + radius * Math.sin(angle * i)
            path.lineTo(x.toFloat(), y.toFloat())
            canvas.drawPath(path, radarPaint)
        }


    }

    /**
     *   path.reset() 重置路径
     *
     *   rewind ，reset都是重置路径
     *   rewind清除filltype 保留数据结构，快速复用
     *   reset  相当于新建路径，数据空间回收，重新分配，不清除filltype
     */

    private fun drawPolygon(canvas: Canvas) {
        val path = Path()
        val r = radius / count //每层蜘蛛丝之间的距离
        for (i in 1..count) { //中心点不用绘制
            val curR = r * i //当前半径
            path.reset() //重置路径  每次进行绘制路径前，都重置路径
            for (j in 0 until count) {
                if (j == 0) {
                    //第一个点，将path移动到起点
                    path.moveTo((centerX + curR).toFloat(), centerY.toFloat())
                } else {
                    //根据半径 计算出蜘蛛丝上的坐标点
                    val x = centerX.toDouble() + curR * Math.cos((angle * j).toDouble())
                    val y = centerY.toDouble() + curR * Math.sin((angle * j).toDouble())
                    path.lineTo(x.toFloat(), y.toFloat())
                }
            }
            path.close()

            canvas.drawPath(path, radarPaint)
        }
    }
}