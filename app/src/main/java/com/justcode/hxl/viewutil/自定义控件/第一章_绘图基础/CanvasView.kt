package com.justcode.hxl.viewutil.自定义控件.第一章_绘图基础

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class CanvasView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        /**
         *  canvas 画布。显示绘图的屏幕不是canvas，这一点很重要。
         *  canvas 与屏幕显示不是一个概念，canvas 是一个虚幻的概念，canva可以理解为一个透明图层
         *  每次在canvas上画图时，都会产生一个透明图层，然后在这个图层上画图，画图完成后，覆盖在屏幕上显示
         *  （如果，有PS基础，应该就很好理解）
         *  需要注意两点，1.如果canvas的部分超出了屏幕，那么在屏幕上，只会显示屏幕内的内容
         *  2.canvas平移，旋转等是不可逆的，因为每一次绘制都是产生了一个新的canvas
         *
         *  canvas 可以平移，旋转，扭曲，缩放，裁剪
         */

        /**
         * 画布，还有两个重要的方法，就是save和restore
         * save 保存当前画布的状态，压入一个栈中
         * restore 取出栈顶的画布状态 ，将当前画布状态变为 所取出的画布状态，换言之，将当前画布状态恢复为取出画布状态
         *
         * 注意 restore的数量要小于等于 save的数量。
         */

        val paintGreen = Paint()
        paintGreen.color = Color.GREEN
        paintGreen.style = Paint.Style.STROKE
        paintGreen.strokeWidth = 5f

        val paintRed = Paint()
        paintRed.color = Color.RED
        paintRed.style = Paint.Style.STROKE
        paintRed.strokeWidth = 5f


        val paintYellow = Paint()
        paintYellow.color = Color.YELLOW
        paintYellow.style = Paint.Style.STROKE
        paintYellow.strokeWidth = 5f


        val rect = Rect(0, 0, 400, 200)

        canvas.save() //存下，初始状态 当前画布栈（自己杜撰的名字），目前为1

        //平移画布前画矩形
        canvas.drawRect(rect, paintGreen)

        //平移画布
        canvas.translate(100f, 100f)
        //平移后画矩形
        canvas.drawRect(rect, paintRed)

        //恢复到初始状态  当前画布栈（自己杜撰的名字），目前为0
        canvas.restore()

        //旋转画布
        canvas.rotate(20f)
        canvas.drawRect(rect, paintYellow)

    }
}