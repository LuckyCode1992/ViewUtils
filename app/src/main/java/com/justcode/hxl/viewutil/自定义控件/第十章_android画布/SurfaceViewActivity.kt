package com.justcode.hxl.viewutil.自定义控件.第十章_android画布

import android.content.Context
import android.graphics.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import com.justcode.hxl.viewutil.R
import java.lang.Exception

class SurfaceViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surface_view)
        /**
         *  android 所有的控件基本都是派生自View或者ViewGroup
         *  Android的屏幕刷新的时间间隔是16ms
         *  如果View 能够在16ms内完成所需执行的绘图操作，那么视觉上，界面就是流畅的。
         *  但是，在某些情况下，我们的需要的时间超过了16ms，就会出现卡顿的情况
         *  为了解决这个问题，就出现了surfaceView
         *
         *  surfaceView 在两个方面改进了View的绘图操作：
         *  - 使用双缓冲技术
         *  - 自带画布，支持在子线程更新画布内容
         */

    }
}

class ViewGesturePath @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint()
    val path = Path()

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        paint.color = Color.RED
    }

    //拦截父view的事件，所有touch事件，都有本view处理
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        parent.requestDisallowInterceptTouchEvent(true)
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x, y)
            }
            MotionEvent.ACTION_UP -> {

            }
        }
        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

}

class SurfaceGesturePath @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr) {

    val paint = Paint()
    val path = Path()

    init {
        //只有执行了这句话，才会 onDraw
        setWillNotDraw(false)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        paint.color = Color.RED
    }

    //拦截父view的事件，所有touch事件，都有本view处理
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        parent.requestDisallowInterceptTouchEvent(true)
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x, y)
            }
        }
//        postInvalidate()
        //使用双缓冲刷新界面
        drawCanvas()
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

    /**
     * 用双缓冲canvas绘图
     */
    fun drawCanvas() {
        Thread {
            //获得自带的缓冲画布并加锁，加锁是为了防止多线程混乱
            val surfaceHolder = holder
            val canvas = surfaceHolder.lockCanvas()

            /**
             *  surfaceView surface，surfaceHolder 其实就是 MVC模式
             *
             */
            //监听surface生命周期
            surfaceHolder.addCallback(object : SurfaceHolder.Callback {
                override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
                    Log.d("surface_view", "surfaceChanged")
                    //surface 任何结构发生变化（格式或者大小）时，该函数调用
                }

                override fun surfaceDestroyed(holder: SurfaceHolder?) {
                    Log.d("surface_view", "surfaceDestroyed")
                    //surface 将要销毁时 调用
                }

                override fun surfaceCreated(holder: SurfaceHolder?) {
                    Log.d("surface_view", "surfaceCreated")
                    // surface创建后，调用
                }

            })

            //这里是绘制操作
            canvas.drawPath(path, paint)
            //结束时，解锁
            surfaceHolder.unlockCanvasAndPost(canvas)
        }.start()

    }
}

class AnimationSurfaceView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr) {
    lateinit var surfaceHolder: SurfaceHolder
    var flag = false // 线程标识
    lateinit var bitmap_bg: Bitmap //背景图

    var surfaceWidth: Float = 0.0f
    var surfaceHeight: Float = 0.0f
    var bitposX = 0
    lateinit var canvas: Canvas
    var thread: Thread? = null

    //背景移动状态
    enum class State {
        LEFT, RIGHT
    }

    var state = State.LEFT
    val BITMAP_STEP = 1//背景画布移动的步伐

    init {
        surfaceHolder = holder
        surfaceHolder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {

            }

            override fun surfaceDestroyed(holder: SurfaceHolder?) {
                flag = false
            }

            override fun surfaceCreated(holder: SurfaceHolder?) {
                flag = true
                startAnim()
            }

        })
    }

    private fun startAnim() {
        surfaceWidth = width.toFloat()
        surfaceHeight = height.toFloat()
        val mWindth = surfaceWidth * 3 / 2
        // 将图片宽度缩放到屏幕的3/2 高度充满屏幕
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.scenery)
        bitmap_bg = Bitmap.createScaledBitmap(bitmap, mWindth.toInt(), surfaceHeight.toInt(), true)

        //开始绘图
        thread = Thread {
            while (flag) {
                canvas = surfaceHolder.lockCanvas()
                drawView()
                surfaceHolder.unlockCanvasAndPost(canvas)
                try {
                    Thread.sleep(50)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        thread?.start()
    }

    private fun drawView() {
        //绘制背景
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)//清空屏幕
        canvas.drawBitmap(bitmap_bg, bitposX.toFloat(), 0f, null)
        //图片滚动效果
        when (state) {
            State.LEFT -> {
                bitposX -= BITMAP_STEP
            }
            State.RIGHT -> {
                bitposX += BITMAP_STEP
            }
        }
        if (bitposX <= -surfaceWidth / 2) {
            state = State.RIGHT
        }
        if (bitposX>=0){
            state = State.LEFT
        }
    }
}