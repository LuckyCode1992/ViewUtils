package com.justcode.hxl.viewutil.自定义控件.绘图进阶.shader

import android.content.Context
import android.graphics.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
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
         * 填充顺序： 先竖向再横向
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
//望远镜效果
class TelescopeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint()
    var bitmap: Bitmap
    var bitmapBG: Bitmap? = null
    var dx: Int = -1
    var dy = -1


    init {
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.scenery)
    }

    //拦截父view的事件，所有touch事件，都有本view处理
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        parent.requestDisallowInterceptTouchEvent(true)
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                dx = event.x.toInt()
                dy = event.y.toInt()
                postInvalidate()
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                dx = event.x.toInt()
                dy = event.y.toInt()
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                dx = -1
                dy = -1
            }

        }
        postInvalidate()
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (bitmapBG == null) {
            bitmapBG = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvasBG = Canvas(bitmapBG)
            canvasBG.drawBitmap(bitmap, null, Rect(0, 0, width, height), paint)
        }
        if (dx != -1 && dy != -1) {
            paint.shader = BitmapShader(bitmapBG, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
            canvas.drawCircle(dx.toFloat(), dy.toFloat(), 150f, paint)
        }
    }
}

//生成不规则头像
class AvatorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint()
    lateinit var bitmap: Bitmap
    lateinit var bitmapShader: BitmapShader
    init {
        bitmap = BitmapFactory.decodeResource(resources,R.drawable.avator)
        bitmapShader = BitmapShader(bitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val matrix = Matrix()
        val scale = width/bitmap.width.toFloat()
        matrix.setScale(scale,scale)
        bitmapShader.setLocalMatrix(matrix)
        paint.shader = bitmapShader

        val half = width/2.toFloat()
        canvas.drawCircle(half,half, (width/2).toFloat(),paint)
    }
}