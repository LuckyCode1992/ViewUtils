package com.justcode.hxl.viewutil

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.justcode.hxl.viewutil.extend.bundleOf
import com.justcode.hxl.viewutil.extend.start
import com.justcode.hxl.viewutil.recycleview_util.CarouseLayoutManagerActivity
import com.justcode.hxl.viewutil.recycleview_util.ChipsLayoutManagerActivity
import com.justcode.hxl.viewutil.recycleview_util.FanLayoutManagerActivity
import com.justcode.hxl.viewutil.recycleview_util.NormolActivity
import com.justcode.hxl.viewutil.shape_selector_util.ShapeAndSelectorActivity
import com.justcode.hxl.viewutil.自定义控件.属性动画.ValueAnimatorActivity
import com.justcode.hxl.viewutil.自定义控件.绘图基础.DrawingBasisActivity
import com.justcode.hxl.viewutil.自定义控件.绘图基础.DrawingBasisDemoActivity
import com.justcode.hxl.viewutil.自定义控件.视图动画.ViewAnimationActivity
import kotlinx.android.synthetic.main.activity_main.*


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

    }
}
