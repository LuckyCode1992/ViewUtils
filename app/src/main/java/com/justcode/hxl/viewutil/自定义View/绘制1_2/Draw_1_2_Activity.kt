package com.justcode.hxl.viewutil.自定义View.绘制1_2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.自定义View.绘制1_1.PageFragment
import kotlinx.android.synthetic.main.activity_draw_1_1_.*

class Draw_1_2_Activity : AppCompatActivity() {

    val pageModels = arrayListOf(
        PageModel(
            R.layout.sample_linear_gradient,
            R.string.title_linear_gradient,
            R.layout.sample_linear_gradient
        ),
        PageModel(
            R.layout.sample_radial_gradient,
            R.string.title_radial_gradient,
            R.layout.sample_radial_gradient
        ),
        PageModel(R.layout.sample_sweep_gradient, R.string.title_sweep_gradient, R.layout.sample_sweep_gradient),
        PageModel(R.layout.sample_bitmap_shader, R.string.title_bitmap_shader, R.layout.sample_bitmap_shader),
        PageModel(R.layout.sample_compose_shader, R.string.title_compose_shader, R.layout.practice_oval),
        PageModel(
            R.layout.sample_lighting_color_filter,
            R.string.title_lighting_color_filter,
            R.layout.practice_lighting_color_filter
        ),
        PageModel(
            R.layout.sample_round_rect,
            R.string.title_color_matrix_color_filter,
            R.layout.practice_round_rect
        ),
        PageModel(R.layout.sample_arc, R.string.title_xfermode, R.layout.practice_arc),
        PageModel(R.layout.sample_path, R.string.title_stroke_cap, R.layout.practice_path),
        PageModel(
            R.layout.sample_histogram,
            R.string.title_stroke_join,
            R.layout.practice_histogram
        ),
        PageModel(
            R.layout.sample_pie_chart,
            R.string.title_stroke_miter,
            R.layout.practice_pie_chart
        ),
        PageModel(
            R.layout.sample_pie_chart,
            R.string.title_path_effect,
            R.layout.practice_pie_chart
        ),
        PageModel(
            R.layout.sample_pie_chart,
            R.string.title_shader_layer,
            R.layout.practice_pie_chart
        ),
        PageModel(
            R.layout.sample_pie_chart,
            R.string.title_mask_filter,
            R.layout.practice_pie_chart
        ),
        PageModel(
            R.layout.sample_pie_chart,
            R.string.title_fill_path,
            R.layout.practice_pie_chart
        ),
        PageModel(
            R.layout.sample_pie_chart,
            R.string.title_text_path,
            R.layout.practice_pie_chart
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_1_1_)
        pager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                val pageModel = pageModels[position]
                return PageFragment.buildFragment(
                    pageModel.sampleLayoutRes,
                    pageModel.practiceLayoutRes
                )
            }


            override fun getCount(): Int {
                return pageModels.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return getString(pageModels[position].titleRes)
            }

        }
        tabLayout.setupWithViewPager(pager)

    }
}

class PageModel(var sampleLayoutRes: Int, var titleRes: Int, var practiceLayoutRes: Int) {

}


