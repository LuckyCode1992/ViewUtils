package com.justcode.hxl.viewutil.自定义控件.绘图进阶.shader

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import com.justcode.hxl.viewutil.R

class LinearGradientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear_gradient)
    }
}

class LinearGradient1View @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint()


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.shader = LinearGradient(
            0f,
            height / 2.toFloat(),
            width.toFloat(),
            height / 2.toFloat(),
            0xffff0000.toInt(),
            0xff00ff00.toInt(),
            Shader.TileMode.CLAMP
        )

        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)

    }
}

class LinearGradient2View @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint()
    //颜色数值
    val colors =
        intArrayOf(0xffff0000.toInt(), 0xff00ff00.toInt(), 0xff0000ff.toInt(), 0xffffff00.toInt(), 0xff00ffff.toInt())
    //比例分配 必须和颜色数组相匹配
    val pos: FloatArray = floatArrayOf(0f, 0.2f, 0.4f, 0.6f, 1f)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.shader = LinearGradient(
            0f, height / 2.toFloat(), width.toFloat(), height / 2.toFloat(), colors, pos, Shader.TileMode.CLAMP
        )
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
    }
}


class LinearGradient2ViewClamp @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint()
    //颜色数值
    val colors =
        intArrayOf(0xffff0000.toInt(), 0xff00ff00.toInt(), 0xff0000ff.toInt(), 0xffffff00.toInt(), 0xff00ffff.toInt())
    //比例分配 必须和颜色数组相匹配
    val pos: FloatArray = floatArrayOf(0f, 0.2f, 0.4f, 0.6f, 1f)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.shader = LinearGradient(
            0f, height / 2.toFloat(), width.toFloat() / 2, height / 2.toFloat(), colors, pos, Shader.TileMode.CLAMP
        )
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
    }
}


class LinearGradient2ViewMirror @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint()
    //颜色数值
    val colors =
        intArrayOf(0xffff0000.toInt(), 0xff00ff00.toInt(), 0xff0000ff.toInt(), 0xffffff00.toInt(), 0xff00ffff.toInt())
    //比例分配 必须和颜色数组相匹配
    val pos: FloatArray = floatArrayOf(0f, 0.2f, 0.4f, 0.6f, 1f)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.shader = LinearGradient(
            0f, height / 2.toFloat(), width.toFloat() / 2, height / 2.toFloat(), colors, pos, Shader.TileMode.MIRROR
        )
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
    }
}

class LinearGradient2ViewRepeat @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint()
    //颜色数值
    val colors =
        intArrayOf(0xffff0000.toInt(), 0xff00ff00.toInt(), 0xff0000ff.toInt(), 0xffffff00.toInt(), 0xff00ffff.toInt())
    //比例分配 必须和颜色数组相匹配
    val pos: FloatArray = floatArrayOf(0f, 0.2f, 0.4f, 0.6f, 1f)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.shader = LinearGradient(
            0f, height / 2.toFloat(), width.toFloat() / 2, height / 2.toFloat(), colors, pos, Shader.TileMode.REPEAT
        )
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
    }
}

class ShaderTextView1 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    //颜色数值
    val colors =
        intArrayOf(0xffff0000.toInt(), 0xff00ff00.toInt(), 0xff0000ff.toInt(), 0xffffff00.toInt(), 0xff00ffff.toInt())
    //比例分配 必须和颜色数组相匹配
    val pos: FloatArray = floatArrayOf(0f, 0.2f, 0.4f, 0.6f, 1f)
    val paint = Paint()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val multiGradient =
            LinearGradient(0f, 0f, width / 2.toFloat(), height / 2.toFloat(), colors, pos, Shader.TileMode.MIRROR)
        paint.shader = multiGradient
        paint.textSize = 50f
        canvas.drawText("欢迎光临我的独占神话", 0f, 200f, paint)
    }
}

class ShimmerTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {
    var dx = 0
    lateinit var linearGradient: LinearGradient
    lateinit var paint: Paint
    val colors = intArrayOf(currentTextColor, 0xff00ff00.toInt(), currentTextColor)
    val pos = floatArrayOf(0f, 0.5f, 1f)

    init {

        paint = getPaint()
        val length = paint.measureText(text.toString())

        linearGradient = LinearGradient(-length, 0f, 0f, 0f, colors, pos, Shader.TileMode.CLAMP)

        val animtor = ValueAnimator.ofInt(0, 2 * length.toInt())
        animtor.addUpdateListener {
            dx = animtor.animatedValue as Int
            postInvalidate()
        }
        animtor.repeatMode = ValueAnimator.RESTART
        animtor.repeatCount = ValueAnimator.INFINITE
        animtor.duration = 2000
        animtor.start()
    }

    override fun onDraw(canvas: Canvas) {
        val matrix = Matrix()
        matrix.setTranslate(dx.toFloat(), 0f)
        linearGradient.setLocalMatrix(matrix)
        paint.shader = linearGradient

        super.onDraw(canvas)

    }
}


