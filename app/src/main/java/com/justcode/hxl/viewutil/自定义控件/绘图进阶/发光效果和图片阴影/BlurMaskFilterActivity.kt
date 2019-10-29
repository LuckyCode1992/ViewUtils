package com.justcode.hxl.viewutil.自定义控件.绘图进阶.发光效果和图片阴影

import android.content.Context
import android.graphics.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.justcode.hxl.viewutil.R

class BlurMaskFilterActivity : AppCompatActivity() {

    /**
     *  只有一个方法，就是setMaskFilter
     *  这个方法，有两个实现 BlurMaskFilter和EmbossMaskFilter
     *  分别是发光效果和浮雕效果
     *  很少使用浮雕效果
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blur_mask_filter)
    }
}

class BlurMaskFilterView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint()

    init {
        paint.color = Color.BLUE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //内发光
        paint.maskFilter = BlurMaskFilter(50f, BlurMaskFilter.Blur.INNER)
        canvas.drawCircle(80f, 100f, 50f, paint)

        //外发光
        paint.maskFilter = BlurMaskFilter(50f, BlurMaskFilter.Blur.SOLID)
        canvas.drawCircle(200f, 100f, 50f, paint)

        //内外发光
        paint.maskFilter = BlurMaskFilter(50f, BlurMaskFilter.Blur.NORMAL)
        canvas.drawCircle(340f, 100f, 50f, paint)

        //仅显示发光效果
        paint.maskFilter = BlurMaskFilter(50f, BlurMaskFilter.Blur.OUTER)
        canvas.drawCircle(480f, 100f, 50f, paint)


    }
}

class BitmapShadowView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint()
    var bitmap: Bitmap
    var alpthaBmp: Bitmap

    init {
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.cat_dog)
        alpthaBmp = bitmap.extractAlpha()

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = 200
        val height = width * alpthaBmp.width / alpthaBmp.height
        //绘制灰色阴影
        paint.color = Color.GRAY
        //添加内外发光
        paint.maskFilter = BlurMaskFilter(10f, BlurMaskFilter.Blur.NORMAL)
        canvas.drawBitmap(alpthaBmp, null, Rect(10, 10, width, height), paint)

        //绘制原图像
        canvas.translate(-5f, -5f)
        paint.setMaskFilter(null)
        canvas.drawBitmap(bitmap, null, Rect(10, 10, width, height), paint)



//        //绘制黑色阴影
//        canvas.translate(width.toFloat(),0f)
//        paint.color = Color.BLACK
//        //添加内外发光
//        paint.maskFilter = BlurMaskFilter(10f,BlurMaskFilter.Blur.NORMAL)
//        canvas.drawBitmap(alpthaBmp,null, Rect(10,10,width,height),paint)

    }
}