package com.justcode.hxl.viewutil

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.justcode.hxl.viewutil.extend.bundleOf
import com.justcode.hxl.viewutil.extend.start
import com.justcode.hxl.viewutil.recycleview_util.FanLayoutManagerActivity
import com.justcode.hxl.viewutil.recycleview_util.NormolActivity
import com.justcode.hxl.viewutil.shape_selector_util.ShapeAndSelectorActivity
import com.justcode.hxl.viewutil.自定义控件.绘图基础.DrawingBasisActivity
import com.justcode.hxl.viewutil.自定义控件.绘图基础.DrawingBasisDemoActivity
import kotlinx.android.synthetic.main.activity_main.*

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




        btn_basis_view.setOnClickListener {
            start<DrawingBasisActivity>()
        }
        btn_basis_demo_view.setOnClickListener {
            start<DrawingBasisDemoActivity>()
        }

    }
}
