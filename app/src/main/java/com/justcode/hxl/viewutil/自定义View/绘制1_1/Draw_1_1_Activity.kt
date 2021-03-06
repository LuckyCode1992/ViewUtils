package com.justcode.hxl.viewutil.自定义View.绘制1_1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.justcode.hxl.viewutil.R
import kotlinx.android.synthetic.main.activity_draw_1_1_.*

class Draw_1_1_Activity : AppCompatActivity() {

    val pageModels = arrayListOf(
        PageModel(R.layout.sample_color, R.string.title_draw_color, R.layout.practice_color),
        PageModel(R.layout.sample_circle, R.string.title_draw_circle, R.layout.practice_circle),
        PageModel(R.layout.sample_rect, R.string.title_draw_rect, R.layout.practice_rect),
        PageModel(R.layout.sample_point, R.string.title_draw_point, R.layout.practice_point),
        PageModel(R.layout.sample_oval, R.string.title_draw_oval, R.layout.practice_oval),
        PageModel(R.layout.sample_line, R.string.title_draw_line, R.layout.practice_line),
        PageModel(
            R.layout.sample_round_rect,
            R.string.title_draw_round_rect,
            R.layout.practice_round_rect
        ),
        PageModel(R.layout.sample_arc, R.string.title_draw_arc, R.layout.practice_arc),
        PageModel(R.layout.sample_path, R.string.title_draw_path, R.layout.practice_path),
        PageModel(
            R.layout.sample_histogram,
            R.string.title_draw_histogram,
            R.layout.practice_histogram
        ),
        PageModel(
            R.layout.sample_pie_chart,
            R.string.title_draw_pie_chart,
            R.layout.practice_pie_chart
        )

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_1_1_)
        pager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                val pageModel = pageModels[position]
                return  PageFragment.buildFragment(pageModel.sampleLayoutRes,pageModel.practiceLayoutRes)
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


