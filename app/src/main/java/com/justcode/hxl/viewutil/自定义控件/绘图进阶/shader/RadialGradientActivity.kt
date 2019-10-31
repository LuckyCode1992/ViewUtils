package com.justcode.hxl.viewutil.自定义控件.绘图进阶.shader

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RadialGradient
import android.graphics.Shader
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.justcode.hxl.viewutil.R

class RadialGradientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gadial_gradient)
    }
}

class RadialGradientView1 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint()
    var radius: Float = 1.0f
    lateinit var radialGradient: RadialGradient

    init {

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        radius = width / 2.toFloat()
        radialGradient = RadialGradient(
            width / 2.toFloat(), height / 2.toFloat(),
            radius, 0xffff0000.toInt(), 0xff00ff00.toInt(), Shader.TileMode.REPEAT
        )
        paint.shader = radialGradient
        canvas.drawCircle(width / 2.toFloat(), height / 2.toFloat(), radius, paint)

    }
}

class RadialGradientView2 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint()
    var radius: Float = 1.0f
    lateinit var radialGradient: RadialGradient
    val colors = intArrayOf(0xffff0000.toInt(), 0xff00ff00.toInt(), 0xff0000ff.toInt(), 0xffffff00.toInt())
    val pos = floatArrayOf(0f, 0.2f, 0.5f, 1f)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        radius = width / 2.toFloat()
        radialGradient = RadialGradient(
            width / 2.toFloat(), height / 2.toFloat(),
            radius/3, colors, pos, Shader.TileMode.CLAMP
        )
        paint.shader = radialGradient
//        canvas.drawCircle(width / 2.toFloat(), height / 2.toFloat(), radius, paint)
        canvas.drawRect(0f,0f, width.toFloat(), height.toFloat(),paint)

    }
}

class RadialGradientView3 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint()
    var radius: Float = 1.0f
    lateinit var radialGradient: RadialGradient
    val colors = intArrayOf(0xffff0000.toInt(), 0xff00ff00.toInt(), 0xff0000ff.toInt(), 0xffffff00.toInt())
    val pos = floatArrayOf(0f, 0.2f, 0.5f, 1f)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        radius = width / 2.toFloat()
        radialGradient = RadialGradient(
            width / 2.toFloat(), height / 2.toFloat(),
            radius/3, colors, pos, Shader.TileMode.MIRROR
        )
        paint.shader = radialGradient
//        canvas.drawCircle(width / 2.toFloat(), height / 2.toFloat(), radius, paint)
        canvas.drawRect(0f,0f, width.toFloat(), height.toFloat(),paint)
    }
}

class RadialGradientView4 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint()
    var radius: Float = 1.0f
    lateinit var radialGradient: RadialGradient
    val colors = intArrayOf(0xffff0000.toInt(), 0xff00ff00.toInt(), 0xff0000ff.toInt(), 0xffffff00.toInt())
    val pos = floatArrayOf(0f, 0.2f, 0.5f, 1f)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        radius = width / 2.toFloat()
        radialGradient = RadialGradient(
            width / 2.toFloat(), height / 2.toFloat(),
            radius/3, colors, pos, Shader.TileMode.REPEAT
        )
        paint.shader = radialGradient
//        canvas.drawCircle(width / 2.toFloat(), height / 2.toFloat(), radius, paint)
        canvas.drawRect(0f,0f, width.toFloat(), height.toFloat(),paint)
    }
}