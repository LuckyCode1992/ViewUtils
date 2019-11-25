package com.justcode.hxl.viewutil.自定义控件.第十章_android画布

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.justcode.hxl.viewutil.R

class ShapeDrawableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shape_drawable)
        /**
         *  shape标签是GradientDrawable 而不是 ShapeDrawable
         *
         *  ShapeDrawable()
         *  ShapeDrawable(Shape s)
         *
         *  ShapeDrawable是一个基类，不能直接使用，只能使用他的子类：
         *      RectShape: 矩形
         *      ArcShape:  扇形
         *      OvalShape:椭圆
         *      RoundRectShape:圆角矩形 可带有镂空效果
         *      PathShape:根据路径绘制shape
         */

    }
}

abstract class BaseShapeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    lateinit var shapeDrawable: ShapeDrawable

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // ShapeDrawable 自带画笔，这句话是说，将ShapeDrawable画到当前控件上，并不是绘制ShapeDrawable
        shapeDrawable.draw(canvas)
        //当我们 改变ShapeDrawable 的paint的内容时，它会立即在ShapeDrawable中重画。然后，就将ShapeDrawable画到了这个view上
    }
}

class RectShapeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseShapeView(context, attrs, defStyleAttr) {
    init {
        //1.生成一个ShapeDrawable实例，通过 RectShape 画出一个矩形
        shapeDrawable = ShapeDrawable(RectShape())
        //2.通过 setBounds 控制控件的显示位置，这里的位置，是当前控件中的位置，不是全屏幕的位置
        shapeDrawable.setBounds(50, 50, 200, 100)
        //3.通过   shapeDrawable.paint.color 填充这个drawable
        shapeDrawable.paint.color = Color.YELLOW
    }
}

class ArcShapeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseShapeView(context, attrs, defStyleAttr) {
    init {
        shapeDrawable = ShapeDrawable(ArcShape(0f, 270f))
        shapeDrawable.setBounds(10, 50, 190, 100)
        shapeDrawable.paint.color = Color.GREEN
    }
}

class OvalShapeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseShapeView(context, attrs, defStyleAttr) {
    init {
        shapeDrawable = ShapeDrawable(OvalShape())
        shapeDrawable.setBounds(10, 50, 190, 100)
        shapeDrawable.paint.color = Color.BLUE
    }
}

class RoundRectShapeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseShapeView(context, attrs, defStyleAttr) {
    init {
        /**
         * outerRadii: 外围矩形各个角度的大小，8个数字 每两个数字 （x,y）对应一个角（左上，右上，右下，左下） 顺时针
         * inset:表示内部矩形与外部矩形的边距，4个值，左，上，右，下，
         * innerRadii：和outerRadii类似，内矩形参数
         */
        val outerRadii = floatArrayOf(12f, 12f, 12f, 12f, 0f, 0f, 0f, 0f)
        val innerRadii = floatArrayOf(50f, 12f, 0f, 0f, 12f, 50f, 0f, 0f)
        val inset = RectF(6f, 6f, 6f, 6f)
        shapeDrawable = ShapeDrawable(RoundRectShape(outerRadii, inset, innerRadii))
        shapeDrawable.setBounds(10, 50, 190, 100)
        shapeDrawable.paint.color = Color.WHITE

    }
}

class PathShapeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseShapeView(context, attrs, defStyleAttr) {

    val path = Path()

    init {
        path.moveTo(0f, 0f)
        path.lineTo(100f, 0f)
        path.lineTo(100f, 100f)
        path.lineTo(0f, 100f)
        //封闭路径
        path.close()
        /**
         * stdWidth: 标准宽度 这个是单位是份数
         * stdHeight:标准高度 这个是单位是份数
         *
         * 和path画的东西的关系是  横向路径100，stdWidth 1000，那么在图上就是，路径展示效果是 100/1000*view的宽度
         */

        shapeDrawable = ShapeDrawable(PathShape(path, 1000f, 100f))
        shapeDrawable.setBounds(0, 0, 250, 150)
        shapeDrawable.paint.color = Color.YELLOW

    }

}

class ShapeShaderView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseShapeView(context, attrs, defStyleAttr) {
    init {
        shapeDrawable = ShapeDrawable(RectShape())
        shapeDrawable.setBounds(100, 100, 300, 300)
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.avator)
        val bitmapShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        shapeDrawable.paint.setShader(bitmapShader)
    }
}

class TelescopeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var bitmap: Bitmap? = null
    var drawable: ShapeDrawable? = null
    //放大镜半径
    val RADIUS = 80
    //放大倍数
    val FACTOR = 3

    val matrix0:Matrix = Matrix()

    //拦截父view的事件，所有touch事件，都有本view处理
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        parent.requestDisallowInterceptTouchEvent(true)
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        //这个位置表示的是绘制shader的起始位置
        matrix0.setTranslate(RADIUS - x * FACTOR, RADIUS - y * FACTOR)
        drawable?.paint?.shader?.setLocalMatrix(matrix0)
        drawable?.setBounds((x - RADIUS).toInt(), (y - RADIUS).toInt(), (x + RADIUS).toInt(), (y + RADIUS).toInt())
        invalidate()

        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (bitmap == null) {
            val bmp = BitmapFactory.decodeResource(resources, R.drawable.scenery)
            bitmap = Bitmap.createScaledBitmap(bmp, width, height, false)
            val with0 = (bitmap as Bitmap).width
            val height0 = (bitmap as Bitmap).height
            val shapeDrawable = BitmapShader(
                Bitmap.createScaledBitmap(bitmap, with0 * FACTOR, height0 * FACTOR, true),
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP
            )
            drawable = ShapeDrawable(OvalShape())
            drawable?.paint?.shader = shapeDrawable
            drawable?.setBounds(0, 0, RADIUS * 2, RADIUS * 2)

        }
        canvas.drawBitmap(bitmap,0f,0f, null)
        drawable?.draw(canvas)
    }

}