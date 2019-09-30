package com.justcode.hxl.viewutil.recycleview_util.viewpagerlayoutactivity.circlescale

import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.viewpagerlayoutmanager.CircleScaleLayoutManager
import com.justcode.hxl.viewutil.recycleview_util.viewpagerlayoutactivity.BaseViewPagerActivity

class CircleScaleLayoutActivity :BaseViewPagerActivity<CircleScaleLayoutManager,CircleScalePopUpWindow>(){
    override fun createLayoutManager(): CircleScaleLayoutManager {
        return CircleScaleLayoutManager(this)
    }

    override fun createSettingPopUpWindow(): CircleScalePopUpWindow {
      return CircleScalePopUpWindow(this, viewPagerLayoutManager, recyclerView)
    }

}