package com.justcode.hxl.viewutil.自定义控件.混合模式

import android.content.Context
import android.graphics.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.justcode.hxl.viewutil.R
import java.util.function.BiFunction


class MixedModeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mixed_mode)
        /**
         *  混合模式 是paint的重要部分
         *  能够实现两张图的无缝结合，和ps类似
         *
         *  Xfermode 是一个空类，只要使用其子类
         *  子类有 AvoidXfermode,PixelXorXfermode，PorterDuffXermode
         *
         *  PixelXorXfermode和AvoidXfermode已经过时，基本不在使用
         *
         *  使用混合模式时，关闭硬件加速
         *
         *  使用混合模式，要离屏绘制
         */

    }
}

class PorterViewADD @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    /**
     * ADD :饱和度相加
     */
    val width0 = 200
    val height0 = 200
    lateinit var dstBmp: Bitmap
    lateinit var srcBmp: Bitmap
    val paint = Paint()

    init {
        dstBmp = makeDst(width0, height0)
        srcBmp = makeSrc(width0, height0)
    }

    private fun makeSrc(width0: Int, height0: Int): Bitmap {
        val bm = Bitmap.createBitmap(width0, height0, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = 0xffffcc44.toInt()
        canvas.drawOval(RectF(0f, 0f, width0.toFloat(), height0.toFloat()), paint)
        return bm
    }

    private fun makeDst(width0: Int, height0: Int): Bitmap {
        val bm = Bitmap.createBitmap(width0, height0, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = 0xff66aaff.toInt()
        canvas.drawRect(Rect(0, 0, width0, height0), paint)
        return bm
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val layerId = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)
        canvas.drawBitmap(dstBmp, 0f, 0f, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.ADD)
        canvas.drawBitmap(srcBmp, width0 / 2f, height0 / 2f, paint)
        paint.xfermode = null
        canvas.restoreToCount(layerId)
    }
}

class PorterViewLIGHTEN @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    /**
     * LIGHTEN :变亮
     */

    lateinit var dstBmp: Bitmap
    lateinit var srcBmp: Bitmap
    val paint = Paint()

    init {
        dstBmp = BitmapFactory.decodeResource(resources, R.drawable.book_bg)
        srcBmp = BitmapFactory.decodeResource(resources, R.drawable.book_light)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val layerId = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)
        canvas.drawBitmap(dstBmp, 0f, 0f, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.LIGHTEN)
        canvas.drawBitmap(srcBmp, 0f, 0f, paint)
        paint.xfermode = null
        canvas.restoreToCount(layerId)
    }
}

class PorterViewDARKEN @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    /**
     * 变暗效果
     */
    val width0 = 200
    val height0 = 200
    lateinit var dstBmp: Bitmap
    lateinit var srcBmp: Bitmap
    val paint = Paint()

    init {
        dstBmp = makeDst(width0, height0)
        srcBmp = makeSrc(width0, height0)
    }

    private fun makeSrc(width0: Int, height0: Int): Bitmap {
        val bm = Bitmap.createBitmap(width0, height0, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = 0xffffcc44.toInt()
        canvas.drawOval(RectF(0f, 0f, width0.toFloat(), height0.toFloat()), paint)
        return bm
    }

    private fun makeDst(width0: Int, height0: Int): Bitmap {
        val bm = Bitmap.createBitmap(width0, height0, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = 0xff66aaff.toInt()
        canvas.drawRect(Rect(0, 0, width0, height0), paint)
        return bm
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val layerId = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)
        canvas.drawBitmap(dstBmp, 0f, 0f, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DARKEN)
        canvas.drawBitmap(srcBmp, width0 / 2f, height0 / 2f, paint)
        paint.xfermode = null
        canvas.restoreToCount(layerId)
    }
}

class PorterViewMUTIPLY @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    /**
     * 正片叠底效果
     */
    val width0 = 200
    val height0 = 200
    lateinit var dstBmp: Bitmap
    lateinit var srcBmp: Bitmap
    val paint = Paint()

    init {
        dstBmp = makeDst(width0, height0)
        srcBmp = makeSrc(width0, height0)
    }

    private fun makeDst(width0: Int, height0: Int): Bitmap {
        val bm = Bitmap.createBitmap(width0, height0, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = 0xffffcc44.toInt()
        canvas.drawOval(RectF(0f, 0f, width0.toFloat(), height0.toFloat()), paint)
        return bm
    }

    private fun makeSrc(width0: Int, height0: Int): Bitmap {
        val bm = Bitmap.createBitmap(width0, height0, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = 0xff66aaff.toInt()
        canvas.drawRect(Rect(0, 0, width0, height0), paint)
        return bm
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val layerId = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)
        canvas.drawBitmap(dstBmp, 0f, 0f, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.MULTIPLY)
        canvas.drawBitmap(srcBmp, width0 / 2f, height0 / 2f, paint)
        paint.xfermode = null
        canvas.restoreToCount(layerId)
    }
}


class PorterViewOVERLAY @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    /**
     * 叠加效果
     */
    val width0 = 200
    val height0 = 200
    lateinit var dstBmp: Bitmap
    lateinit var srcBmp: Bitmap
    val paint = Paint()

    init {
        dstBmp = makeDst(width0, height0)
        srcBmp = makeSrc(width0, height0)
    }

    private fun makeSrc(width0: Int, height0: Int): Bitmap {
        val bm = Bitmap.createBitmap(width0, height0, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = 0xffffcc44.toInt()
        canvas.drawOval(RectF(0f, 0f, width0.toFloat(), height0.toFloat()), paint)
        return bm
    }

    private fun makeDst(width0: Int, height0: Int): Bitmap {
        val bm = Bitmap.createBitmap(width0, height0, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = 0xff66aaff.toInt()
        canvas.drawRect(Rect(0, 0, width0, height0), paint)
        return bm
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val layerId = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)
        canvas.drawBitmap(dstBmp, 0f, 0f, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.OVERLAY)
        canvas.drawBitmap(srcBmp, width0 / 2f, height0 / 2f, paint)
        paint.xfermode = null
        canvas.restoreToCount(layerId)
    }
}

class PorterViewSCREEN @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    /**
     * 滤色
     */
    val width0 = 200
    val height0 = 200
    lateinit var dstBmp: Bitmap
    lateinit var srcBmp: Bitmap
    val paint = Paint()

    init {
        dstBmp = makeDst(width0, height0)
        srcBmp = makeSrc(width0, height0)
    }

    private fun makeSrc(width0: Int, height0: Int): Bitmap {
        val bm = Bitmap.createBitmap(width0, height0, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = 0xffffcc44.toInt()
        canvas.drawOval(RectF(0f, 0f, width0.toFloat(), height0.toFloat()), paint)
        return bm
    }

    private fun makeDst(width0: Int, height0: Int): Bitmap {
        val bm = Bitmap.createBitmap(width0, height0, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = 0xff66aaff.toInt()
        canvas.drawRect(Rect(0, 0, width0, height0), paint)
        return bm
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val layerId = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)
        canvas.drawBitmap(dstBmp, 0f, 0f, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SCREEN)
        canvas.drawBitmap(srcBmp, width0 / 2f, height0 / 2f, paint)
        paint.xfermode = null
        canvas.restoreToCount(layerId)
    }
}

class TwitterView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    lateinit var srcBmp: Bitmap
    lateinit var dstBmp: Bitmap
    val paint = Paint()

    init {
        srcBmp = BitmapFactory.decodeResource(resources, R.drawable.twiter_light)
        dstBmp = BitmapFactory.decodeResource(resources, R.drawable.twiter_bg)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val layerId = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)
        canvas.drawBitmap(dstBmp, 0f, 0f, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.MULTIPLY)
        canvas.drawBitmap(srcBmp, 0f, 0f, paint)

        paint.setXfermode(null)
        canvas.restoreToCount(layerId)

    }
}

class PorterViewSRC @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    /**
     * src
     */
    val width0 = 200
    val height0 = 200
    lateinit var dstBmp: Bitmap
    lateinit var srcBmp: Bitmap
    val paint = Paint()

    init {
        dstBmp = makeDst(width0, height0)
        srcBmp = makeSrc(width0, height0)
    }

    private fun makeDst(width0: Int, height0: Int): Bitmap {
        val bm = Bitmap.createBitmap(width0, height0, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = 0xffffcc44.toInt()
        canvas.drawOval(RectF(0f, 0f, width0.toFloat(), height0.toFloat()), paint)
        return bm
    }

    private fun makeSrc(width0: Int, height0: Int): Bitmap {
        val bm = Bitmap.createBitmap(width0, height0, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = 0xff66aaff.toInt()
        canvas.drawRect(Rect(0, 0, width0, height0), paint)
        return bm
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val layerId = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)
        canvas.drawBitmap(dstBmp, 0f, 0f, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC)
        canvas.drawBitmap(srcBmp, width0 / 2f, height0 / 2f, paint)
        paint.xfermode = null
        canvas.restoreToCount(layerId)
    }
}

class PorterViewSRCIN @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    /**
     * src_in
     */
    val width0 = 200
    val height0 = 200
    lateinit var dstBmp: Bitmap
    lateinit var srcBmp: Bitmap
    val paint = Paint()

    init {
        dstBmp = makeDst(width0, height0)
        srcBmp = makeSrc(width0, height0)
    }

    private fun makeDst(width0: Int, height0: Int): Bitmap {
        val bm = Bitmap.createBitmap(width0, height0, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = 0xffffcc44.toInt()
        canvas.drawOval(RectF(0f, 0f, width0.toFloat(), height0.toFloat()), paint)
        return bm
    }

    private fun makeSrc(width0: Int, height0: Int): Bitmap {
        val bm = Bitmap.createBitmap(width0, height0, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = 0xff66aaff.toInt()
        canvas.drawRect(Rect(0, 0, width0, height0), paint)
        return bm
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val layerId = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)
        canvas.drawBitmap(dstBmp, 0f, 0f, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(srcBmp, width0 / 2f, height0 / 2f, paint)
        paint.xfermode = null
        canvas.restoreToCount(layerId)
    }
}

//倒影效果
class InvertedImageView_SRCIN @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint()
    lateinit var dstBmp: Bitmap
    lateinit var srcBmp: Bitmap
    lateinit var revertBmp: Bitmap

    init {
        dstBmp = BitmapFactory.decodeResource(resources, R.drawable.dog_invert_shade)
        srcBmp = BitmapFactory.decodeResource(resources, R.drawable.dog)
        val matrix = Matrix()
        matrix.setScale(1f, -1f)
        revertBmp = Bitmap.createBitmap(srcBmp, 0, 0, srcBmp.width, srcBmp.height, matrix, true)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val w = width / 2
        val h = w * dstBmp.height / dstBmp.width
        //先画小狗
        canvas.drawBitmap(srcBmp, null, RectF(0f, 0f, w.toFloat(), h.toFloat()), paint)

        //画布向下移
        val layerId = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)
        canvas.translate(0f, h.toFloat())
        //画倒影
        canvas.drawBitmap(dstBmp, null, RectF(0f, 0f, w.toFloat(), h.toFloat()), paint)
        //使用SRC_IN
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(revertBmp, null, RectF(0f, 0f, w.toFloat(), h.toFloat()), paint)

        paint.setXfermode(null)
        canvas.restoreToCount(layerId)
    }
}

class PorterViewSRCOUT @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    /**
     * src_OUT
     */
    val width0 = 200
    val height0 = 200
    lateinit var dstBmp: Bitmap
    lateinit var srcBmp: Bitmap
    val paint = Paint()

    init {
        dstBmp = makeDst(width0, height0)
        srcBmp = makeSrc(width0, height0)
    }

    private fun makeDst(width0: Int, height0: Int): Bitmap {
        val bm = Bitmap.createBitmap(width0, height0, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = 0xffffcc44.toInt()
        canvas.drawOval(RectF(0f, 0f, width0.toFloat(), height0.toFloat()), paint)
        return bm
    }

    private fun makeSrc(width0: Int, height0: Int): Bitmap {
        val bm = Bitmap.createBitmap(width0, height0, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = 0xff66aaff.toInt()
        canvas.drawRect(Rect(0, 0, width0, height0), paint)
        return bm
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val layerId = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)
        canvas.drawBitmap(dstBmp, 0f, 0f, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OUT)
        canvas.drawBitmap(srcBmp, width0 / 2f, height0 / 2f, paint)
        paint.xfermode = null
        canvas.restoreToCount(layerId)
    }
}

class EraserView_SRCOUT @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint()
    lateinit var dstBmp: Bitmap
    lateinit var srcBmp: Bitmap
    val path = Path()
    var mPreX: Float = 0f
    var mPreY: Float = 0f

    init {
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 45f

        val options = BitmapFactory.Options()
        options.inSampleSize = 2

        srcBmp = BitmapFactory.decodeResource(resources, R.drawable.dog, options)

        dstBmp = Bitmap.createBitmap(srcBmp.width, srcBmp.height, Bitmap.Config.ARGB_8888)
    }

    //拦截父view的事件，所有touch事件，都有本view处理
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        parent.requestDisallowInterceptTouchEvent(true)
        return super.dispatchTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val layerId = canvas.saveLayer(0f,0f,width.toFloat(),height.toFloat(),null,Canvas.ALL_SAVE_FLAG)
        //手势轨迹画到目标图像上
        val c = Canvas(dstBmp)
        c.drawPath(path,paint)

        //把目标图像画到画布上
        canvas.drawBitmap(dstBmp,0f,0f,paint)

        //计算源图像区域
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OUT)
        canvas.drawBitmap(srcBmp,0f,0f,paint)
        paint.setXfermode(null)
        canvas.restoreToCount(layerId)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when(event.action){
            MotionEvent.ACTION_DOWN->{
                path.moveTo(event.x,event.y)
                mPreX = event.x
                mPreY = event.y
                return true
            }
            MotionEvent.ACTION_MOVE->{
                val endx = (mPreX+event.x)/2
                val endy = (mPreY+event.y)/2
                path.quadTo(mPreX,mPreY,endx,endy)
                mPreX = event.x
                mPreY=event.y
            }
        }
        postInvalidate()
        return super.onTouchEvent(event)
    }

}

class PorterViewSRCOVER @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    /**
     * src_OVER
     */
    val width0 = 200
    val height0 = 200
    lateinit var dstBmp: Bitmap
    lateinit var srcBmp: Bitmap
    val paint = Paint()

    init {
        dstBmp = makeDst(width0, height0)
        srcBmp = makeSrc(width0, height0)
    }

    private fun makeDst(width0: Int, height0: Int): Bitmap {
        val bm = Bitmap.createBitmap(width0, height0, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = 0xffffcc44.toInt()
        canvas.drawOval(RectF(0f, 0f, width0.toFloat(), height0.toFloat()), paint)
        return bm
    }

    private fun makeSrc(width0: Int, height0: Int): Bitmap {
        val bm = Bitmap.createBitmap(width0, height0, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = 0xff66aaff.toInt()
        canvas.drawRect(Rect(0, 0, width0, height0), paint)
        return bm
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val layerId = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)
        canvas.drawBitmap(dstBmp, 0f, 0f, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)
        canvas.drawBitmap(srcBmp, width0 / 2f, height0 / 2f, paint)
        paint.xfermode = null
        canvas.restoreToCount(layerId)
    }
}

class PorterViewSRCATOP @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    /**
     * src_ATOP
     */
    val width0 = 200
    val height0 = 200
    lateinit var dstBmp: Bitmap
    lateinit var srcBmp: Bitmap
    val paint = Paint()

    init {
        dstBmp = makeDst(width0, height0)
        srcBmp = makeSrc(width0, height0)
    }

    private fun makeDst(width0: Int, height0: Int): Bitmap {
        val bm = Bitmap.createBitmap(width0, height0, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = 0xffffcc44.toInt()
        canvas.drawOval(RectF(0f, 0f, width0.toFloat(), height0.toFloat()), paint)
        return bm
    }

    private fun makeSrc(width0: Int, height0: Int): Bitmap {
        val bm = Bitmap.createBitmap(width0, height0, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = 0xff66aaff.toInt()
        canvas.drawRect(Rect(0, 0, width0, height0), paint)
        return bm
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val layerId = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)
        canvas.drawBitmap(dstBmp, 0f, 0f, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP)
        canvas.drawBitmap(srcBmp, width0 / 2f, height0 / 2f, paint)
        paint.xfermode = null
        canvas.restoreToCount(layerId)
    }
}
