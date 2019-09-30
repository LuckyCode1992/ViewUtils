package com.justcode.hxl.viewutil.recycleview_util.viewpagerlayoutactivity.scale

import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.viewpagerlayoutmanager.ScaleLayoutManager
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.viewpagerlayoutmanager.Util
import com.justcode.hxl.viewutil.recycleview_util.viewpagerlayoutactivity.BaseViewPagerActivity

class ScaleLayoutActivity :BaseViewPagerActivity<ScaleLayoutManager,ScalePopUpWindow>(){
    override fun createLayoutManager(): ScaleLayoutManager {
        return ScaleLayoutManager(this, Util.Dp2px(this, 10f))
    }

    override fun createSettingPopUpWindow(): ScalePopUpWindow {
        return ScalePopUpWindow(this, viewPagerLayoutManager, recyclerView)
    }

}