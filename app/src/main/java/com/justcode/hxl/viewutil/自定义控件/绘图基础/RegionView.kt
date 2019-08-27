package com.justcode.hxl.viewutil.自定义控件.绘图基础

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * 区域 任意形状的封闭图形
 */
class RegionView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.BLACK)

        val paint1 = Paint()
        paint1.style = Paint.Style.FILL
        paint1.color = Color.GREEN
        val rect = Rect(0, 0, 100, 100)
        val region = Region(rect)
        //绘制区域
        drawRegison(canvas, region, paint1)


        //半椭圆区域
        val paint2 = Paint()
        paint2.style = Paint.Style.FILL
        paint2.color = Color.YELLOW
        //准备一个矩形区域
        val region1 = Region(200, 10, 300, 110)
        //准备一个矩形，为后面的椭圆作辅助
        val rectf = RectF(200f, 10f, 300f, 500f)
        val ovalPath = Path()
        ovalPath.addOval(rectf, Path.Direction.CCW)
        //setPath 传入一个矩形区域和一个椭圆区域，取交集
        val reg = Region()
        reg.setPath(ovalPath, region1)
        drawRegison(canvas, reg, paint2)

        /**
         * 区域的各种操作，交集，并集等
         */
        val regionPaint = Paint()
        regionPaint.style = Paint.Style.STROKE
        regionPaint.color = Color.WHITE
        regionPaint.strokeWidth = 2f

        //intersect 交集
        val rect1 = Rect(100, 300, 200, 600)
        canvas.drawRect(rect1, regionPaint)
        val rect2 = Rect(0, 400, 300, 500)
        canvas.drawRect(rect2, regionPaint)
        val intersect1 = Region(rect1)
        val intersect2 = Region(rect2)
        intersect1.op(intersect2, Region.Op.INTERSECT)
        drawRegison(canvas, intersect1)


        //difference 补集
        val rect3 = Rect(500, 300, 600, 600)
        val rect4 = Rect(400, 400, 700, 500)
        canvas.drawRect(rect3, regionPaint)
        canvas.drawRect(rect4, regionPaint)
        val difference1 = Region(rect3)
        val difference2 = Region(rect4)
        difference1.op(difference2, Region.Op.DIFFERENCE)
        drawRegison(canvas, difference1)


        //replace 替换
        val rect5 = Rect(100, 700, 200, 1000)
        val rect6 = Rect(0, 800, 300, 900)
        canvas.drawRect(rect5, regionPaint)
        canvas.drawRect(rect6, regionPaint)
        val replace1 = Region(rect5)
        val replace2 = Region(rect6)
        replace1.op(replace2, Region.Op.REPLACE)
        drawRegison(canvas, replace1)


        //reverse_difference 翻转补集
        val rect7 = Rect(500, 700, 600, 1000)
        val rect8 = Rect(400, 800, 700, 900)
        canvas.drawRect(rect7, regionPaint)
        canvas.drawRect(rect8, regionPaint)
        val reverse_difference1 = Region(rect7)
        val reverse_difference2 = Region(rect8)
        reverse_difference1.op(reverse_difference2, Region.Op.REVERSE_DIFFERENCE)
        drawRegison(canvas, reverse_difference1)


        //union 并集
        val rect9 = Rect(100, 1100, 200, 1400)
        val rect10 = Rect(0, 1200, 300, 1300)
        canvas.drawRect(rect9, regionPaint)
        canvas.drawRect(rect10, regionPaint)
        val union1 = Region(rect9)
        val union2 = Region(rect10)
        union1.op(union2, Region.Op.UNION)
        drawRegison(canvas, union1)

        //xor 异并集
        val rect11 = Rect(500, 1100, 600, 1400)
        val rect12 = Rect(400, 1200, 700, 1300)
        canvas.drawRect(rect11, regionPaint)
        canvas.drawRect(rect12, regionPaint)
        val xor1 = Region(rect11)
        val xor2 = Region(rect12)
        xor1.op(xor2, Region.Op.XOR)
        drawRegison(canvas, xor1)

    }

    private fun drawRegison(canvas: Canvas, region: Region, paint: Paint) {
        val iter = RegionIterator(region)
        val rect = Rect()
        while (iter.next(rect)) {
            canvas.drawRect(rect, paint)
        }
    }

    private fun drawRegison(canvas: Canvas, region: Region) {
        val paint = Paint()
        paint.color = Color.YELLOW
        paint.style = Paint.Style.FILL
        val iter = RegionIterator(region)
        val rect = Rect()
        while (iter.next(rect)) {
            canvas.drawRect(rect, paint)
        }
    }

}