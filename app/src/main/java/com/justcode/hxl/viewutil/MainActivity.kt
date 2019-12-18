package com.justcode.hxl.viewutil

import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.justcode.hxl.viewutil.extend.bundleOf
import com.justcode.hxl.viewutil.extend.start
import com.justcode.hxl.viewutil.recycleview_util.*
import com.justcode.hxl.viewutil.recycleview_util.ExpandLayoutManagerActivity
import com.justcode.hxl.viewutil.recycleview_util.StackCardLayoutActivity
import com.justcode.hxl.viewutil.recycleview_util.viewpagerlayoutactivity.ViewPagerActivity
import com.justcode.hxl.viewutil.shape_selector_util.ShapeAndSelectorActivity
import com.justcode.hxl.viewutil.shape_selector_util.core.DrawableFactory
import com.justcode.hxl.viewutil.自定义控件.第十章_android画布.HuabuMainActivity
import com.justcode.hxl.viewutil.自定义控件.第九章_canvas与图层.CanvasMainActivity
import com.justcode.hxl.viewutil.自定义控件.第六章_paint基本使用.PaintSimpleActivity
import com.justcode.hxl.viewutil.自定义控件.第五章_动画进阶.PathMeasureActivity
import com.justcode.hxl.viewutil.自定义控件.第五章_动画进阶.SVGdemoActivity
import com.justcode.hxl.viewutil.自定义控件.第三章_属性动画.ValueAnimatorActivity
import com.justcode.hxl.viewutil.自定义控件.第四章_属性动画进阶.PropertyActivity
import com.justcode.hxl.viewutil.自定义控件.第八章_混合模式.MixedModeActivity
import com.justcode.hxl.viewutil.自定义控件.第一章_绘图基础.DrawingBasisActivity
import com.justcode.hxl.viewutil.自定义控件.第一章_绘图基础.DrawingBasisDemoActivity
import com.justcode.hxl.viewutil.自定义控件.第七章_绘图进阶.DrawAdvancedActivity
import com.justcode.hxl.viewutil.自定义控件.第二章_视图动画.ViewAnimationActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.R.layout
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.MeasureSpec
import com.justcode.hxl.viewutil.自定义控件.第七章_绘图进阶.shader.LinearGradient2View
import com.justcode.hxl.viewutil.自定义控件.第十三章_控件高级属性.ControlActivity
import com.justcode.hxl.viewutil.自定义控件.第十二章_封装控件.PackageActivity


/**
 * gradlew compileDebugSources 这个命令可以查找错误资源文件
 *
 *  gradlew processDebugManifest --stacktrace 可以查看 Manifest merger failed with multiple errors, see logs的问题
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_shape.setOnClickListener {
            start<ShapeAndSelectorActivity>()
        }
        btn_recyclerview.setOnClickListener {
            start<NormolActivity>(bundleOf(NormolActivity.LAYOUTMANAGER to NormolActivity.Linear_LayoutManager_V))
        }
        btn_fanlayoutmanager.setOnClickListener {
            start<FanLayoutManagerActivity>()
        }
        btn_carouselayoutmananger.setOnClickListener {
            start<CarouseLayoutManagerActivity>()
        }
        btn_chipslayoutmanager.setOnClickListener {
            start<ChipsLayoutManagerActivity>()
        }
        btn_hivelayoutmanager.setOnClickListener {
            start<HiveLayoutmanagerActivity>()
        }
        btn_discrete_layout.setOnClickListener {
            start<DiscreateScrollLayoutMangerActivity>()
        }

        btn_greedo_layout.setOnClickListener {
            start<GreedoLayoutManagerActivity>()
        }
        btn_tantan_layout.setOnClickListener {
            start<TantanCardLayoutActivity>()
        }
        btn_view_pager_layout.setOnClickListener {
            start<ViewPagerActivity>()
        }
        btn_flow_drag_layout.setOnClickListener {
            start<FlowDragLayoutManagerActivity>()
        }
        btn_expand_layout.setOnClickListener {
            start<ExpandLayoutManagerActivity>()
        }
        btn_circular_layout.setOnClickListener {
            start<CircularLayoutActivity>()
        }
        btn_stack_card_layout.setOnClickListener {
            start<StackCardLayoutActivity>()
        }
        btn_sticky_layout.setOnClickListener {
            start<StickyLayoutActivity>()
        }
        btn_turn_layout.setOnClickListener {
            start<TurnLayoutManagerActivity>()
        }

        btn_vega_layout.setOnClickListener {
            start<VegaLayoutActivity>()
        }


        btn_basis_view.setOnClickListener {
            start<DrawingBasisActivity>()
        }
        btn_basis_demo_view.setOnClickListener {
            start<DrawingBasisDemoActivity>()
        }
        btn_view_animation.setOnClickListener {
            start<ViewAnimationActivity>()
        }
        btn_value_animator.setOnClickListener {
            start<ValueAnimatorActivity>()
        }
        btn_value_animator_jinjie.setOnClickListener {
            start<PropertyActivity>()
        }
        btn_path_measure.setOnClickListener {
            start<PathMeasureActivity>()
        }
        btn_svg.setOnClickListener {
            start<SVGdemoActivity>()
        }
        btn_paint_simple.setOnClickListener {
            start<PaintSimpleActivity>()
        }
        btn_draw_advanced.setOnClickListener {
            start<DrawAdvancedActivity>()
        }
        btn_hunhe_mode.setOnClickListener {
            start<MixedModeActivity>()
        }
        btn_canvas_main.setOnClickListener {
            start<CanvasMainActivity>()
        }
        btn_huabu_main.setOnClickListener {
            start<HuabuMainActivity>()
        }
        btn_fengzhuang_main.setOnClickListener {
            start<PackageActivity>()
        }
        btn_kongjian_gaoji.setOnClickListener {
            start<ControlActivity>()
        }


    }


}

class LinearGradient2Progress @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint()
    //颜色数值
    val colors =
        intArrayOf(0xffff0000.toInt(), 0xff00ff00.toInt(), 0xff0000ff.toInt(), 0xffffff00.toInt(), 0xff00ffff.toInt())
    //比例分配 必须和颜色数组相匹配
    val pos: FloatArray = floatArrayOf(0f, 0.2f, 0.4f, 0.6f, 1f)

    var width0 = 10f

    //拦截父view的事件，所有touch事件，都有本view处理
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        parent.requestDisallowInterceptTouchEvent(true)
        return super.dispatchTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.shader = LinearGradient(
            0f, height / 2.toFloat(), width.toFloat(), height / 2.toFloat(), colors, pos, Shader.TileMode.CLAMP
        )
        canvas.drawRect(0f, 0f, width0.toFloat(), height.toFloat(), paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                width0 = event.x
                postInvalidate()
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                width0 = event.x
                Log.d("move_", "x:${event.x}-width0:${width0}")
                postInvalidate()
            }
            MotionEvent.ACTION_UP -> {

            }
        }

        return super.onTouchEvent(event)
    }
}