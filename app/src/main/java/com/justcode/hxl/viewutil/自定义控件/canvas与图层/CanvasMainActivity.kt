package com.justcode.hxl.viewutil.自定义控件.canvas与图层

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.justcode.hxl.viewutil.R

class CanvasMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canvas_main)

        /**
         *  图层（layer）:每次调用canvas.drawXXX系列函数，都会生成一个透明图层专门来绘制这个图形
         *
         *  画布(Bitmap):每块画布都是一个Bitmap,所有的图像都是画在这个Bitmap上的。
         *  --每次调用drawXXX系列函数就会生成一个专门的透明图层来绘制，然后覆盖在画布上。
         *  --画布有两种：一种是View的原始画布，一种人造画布，通过saveLayer()和new Canvas(bitmap)
         *
         *  Canvas: canvas是画布的表现形式，我们绘制的任何东西都是canvas来实现的。
         */


    }
}

class ViewDemo @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    /**
     * 获取canvas对象的方法
     * -1.重写onDraw()，dispathchDraw()函数
     * -2.使用Bitmap创建
     * -3.调用SurfaceHolder.lockCanvas()函数
     */
    init {
        val bitmap = Bitmap.createBitmap(200,200,Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
    }
}

class BitmapCanvasView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    lateinit var bitmap: Bitmap
    val paint = Paint()
    lateinit var bmpCanvas: Canvas
    init {
        paint.color = Color.BLACK
        bitmap = Bitmap.createBitmap(500,500,Bitmap.Config.ARGB_8888)
        bmpCanvas = Canvas(bitmap)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.textSize = 50f
        //将文字写到bmpCanvas的图层上，这里并不会绘制到界面
        bmpCanvas.drawText("hello canvas",0f,100f,paint)

        //将bmpCanvas的内容放到bitmap中，然后，将bitmap绘制到界面的canvas上
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
    }
}