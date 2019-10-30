package com.justcode.hxl.viewutil.自定义控件.绘图进阶.shader

import android.content.Context
import android.graphics.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.justcode.hxl.viewutil.R

class BitmapShaderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shader_and_bitmap_shader)
        /**
         * shader 着色器。
         * 但它只是一个基类，实际使用的是他的子类
         *
         * 这里是使用  BitmapShader
         *
         * BitmapShader(@NonNull Bitmap bitmap, @NonNull TileMode tileX, @NonNull TileMode tileY)
         *
         * tileX: x轴方向的重复策略
         *
         * tileY：y轴方向的重复策略
         *
         * TileMode.REPEAT：重复原图像填充剩余空间
         * TileMode.MIRROR：重复使用原图的镜像填充
         * TileMode.MIRROR：用边缘色彩色彩填充
         *
         */
    }
}

class BitmapShaderView1 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val paint = Paint()
    lateinit var bitmap: Bitmap

    init {
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.dog_edge)
        paint.shader = BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)

    }
}
class BitmapShaderView2 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val paint = Paint()
    lateinit var bitmap: Bitmap

    init {
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.dog_edge)
        paint.shader = BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)

    }
}

class BitmapShaderView3 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val paint = Paint()
    lateinit var bitmap: Bitmap

    init {
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.dog_edge)
        paint.shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)

    }
}