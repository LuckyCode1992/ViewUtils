package com.justcode.hxl.viewutil.自定义控件.第十章_android画布

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.ArcShape
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.RectShape
import android.graphics.drawable.shapes.RoundRectShape
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
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
    lateinit var shapeDrawable:ShapeDrawable

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
        shapeDrawable.setBounds(50,50,200,100)
        //3.通过   shapeDrawable.paint.color 填充这个drawable
        shapeDrawable.paint.color = Color.YELLOW
    }
}
class ArcShapeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseShapeView(context, attrs, defStyleAttr) {
    init {
        shapeDrawable = ShapeDrawable(ArcShape(0f,270f))
        shapeDrawable.setBounds(10,50,190,100)
        shapeDrawable.paint.color = Color.GREEN
    }
}
class OvalShapeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseShapeView(context, attrs, defStyleAttr) {
    init {
        shapeDrawable = ShapeDrawable(OvalShape())
        shapeDrawable.setBounds(10,50,190,100)
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
        val outerRadii = floatArrayOf(12f,12f ,12f,12f, 0f,0f, 0f,0f)
        val innerRadii = floatArrayOf(50f,12f ,0f,0f, 12f,50f, 0f,0f)
        val inset = RectF(6f,6f,6f,6f)
        shapeDrawable = ShapeDrawable(RoundRectShape(outerRadii,inset,innerRadii))
        shapeDrawable.setBounds(10,50,190,100)
        shapeDrawable.paint.color = Color.WHITE

    }
}
