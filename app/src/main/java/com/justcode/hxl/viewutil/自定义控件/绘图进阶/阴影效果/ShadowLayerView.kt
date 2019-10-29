package com.justcode.hxl.viewutil.自定义控件.绘图进阶.阴影效果

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.justcode.hxl.viewutil.R

class ShadowLayerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val paint = Paint()
    lateinit var bitmap: Bitmap
    init {
        paint.textSize = 25f

        paint.color = Color.BLACK
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.beauty_8)

    }
    fun addShadow(){
        paint.setShadowLayer(2f,5f,5f,Color.GRAY)
        postInvalidate()
    }
    fun deleteShadow(){
        paint.clearShadowLayer()
        postInvalidate()
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawColor(Color.WHITE)
        canvas.drawText("阴影",50f,100f,paint)

        canvas.drawCircle(200f,100f,50f,paint)

        canvas.drawBitmap(bitmap,null,Rect(300,50,300+bitmap.width,50+bitmap.height),paint)


    }
}