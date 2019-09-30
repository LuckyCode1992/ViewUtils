package com.justcode.hxl.viewutil.recycleview_util.viewpagerlayoutactivity

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.extend.start
import com.justcode.hxl.viewutil.recycleview_util.viewpagerlayoutactivity.carousel.CarouselLayoutActivity
import com.justcode.hxl.viewutil.recycleview_util.viewpagerlayoutactivity.circle.CircleLayoutActivity
import com.justcode.hxl.viewutil.recycleview_util.viewpagerlayoutactivity.circlescale.CircleScaleLayoutActivity
import com.justcode.hxl.viewutil.recycleview_util.viewpagerlayoutactivity.gallery.GalleryLayoutActivity
import com.justcode.hxl.viewutil.recycleview_util.viewpagerlayoutactivity.rotate.RotateLayoutActivity
import com.justcode.hxl.viewutil.recycleview_util.viewpagerlayoutactivity.scale.ScaleLayoutActivity
import kotlinx.android.synthetic.main.activity_viewpager_layout.*

class ViewPagerActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager_layout)
        bt_circle.setOnClickListener {
start<CircleLayoutActivity>()
        }

        bt_scale.setOnClickListener {
            start<ScaleLayoutActivity>()
        }

        bt_circle_scale.setOnClickListener {
            start<CircleScaleLayoutActivity>()
        }

        bt_elevate_scale.setOnClickListener {
            start<CarouselLayoutActivity>()
        }

        bt_gallery.setOnClickListener {
            start<GalleryLayoutActivity>()
        }

        bt_rotate.setOnClickListener {
            start<RotateLayoutActivity>()
        }
    }
}