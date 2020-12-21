package com.justcode.hxl.viewutil.自定义View.绘制1_2.practice1_2

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.justcode.hxl.viewutil.R

class Sample05ComposeShaderView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        val bitmap1 = BitmapFactory.decodeResource(resources,R.drawable.batman0)
        val bitmap2 = BitmapFactory.decodeResource(resources,R.drawable.batman_logo)

        val shader1 = BitmapShader(bitmap1,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP)
        val shader2 = BitmapShader(bitmap2,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP)

        paint.shader = ComposeShader(shader1,shader2,PorterDuff.Mode.DST_IN)

    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        canvas.drawCircle(300f,300f,350f,paint)
    }
}