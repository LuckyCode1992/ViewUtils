package com.justcode.hxl.viewutil.recycleview_util.viewpagerlayoutactivity.rotate

import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.viewpagerlayoutmanager.RotateLayoutManager
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.viewpagerlayoutmanager.Util
import com.justcode.hxl.viewutil.recycleview_util.viewpagerlayoutactivity.BaseViewPagerActivity

class RotateLayoutActivity: BaseViewPagerActivity<RotateLayoutManager,RotatePopUpWindow>(){
    override fun createLayoutManager(): RotateLayoutManager {
        return RotateLayoutManager(this, Util.Dp2px(this, 10f))
    }

    override fun createSettingPopUpWindow(): RotatePopUpWindow {
        return RotatePopUpWindow(this, viewPagerLayoutManager, recyclerView)
    }

}