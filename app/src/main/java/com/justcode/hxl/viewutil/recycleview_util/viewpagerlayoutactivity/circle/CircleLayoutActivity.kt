package com.justcode.hxl.viewutil.recycleview_util.viewpagerlayoutactivity.circle

import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.viewpagerlayoutmanager.CircleLayoutManager
import com.justcode.hxl.viewutil.recycleview_util.viewpagerlayoutactivity.BaseViewPagerActivity

class CircleLayoutActivity:BaseViewPagerActivity<CircleLayoutManager,CirclePopUpWindow>(){
    override fun createLayoutManager(): CircleLayoutManager {
        return CircleLayoutManager(this)
    }

    override fun createSettingPopUpWindow(): CirclePopUpWindow {
       return  CirclePopUpWindow(this,viewPagerLayoutManager,recyclerView)
    }

}