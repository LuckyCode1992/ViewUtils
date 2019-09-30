package com.justcode.hxl.viewutil.recycleview_util.viewpagerlayoutactivity.carousel

import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.viewpagerlayoutmanager.CarouselLayoutManager
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.viewpagerlayoutmanager.Util
import com.justcode.hxl.viewutil.recycleview_util.viewpagerlayoutactivity.BaseViewPagerActivity

class CarouselLayoutActivity :BaseViewPagerActivity<CarouselLayoutManager,CarouselPopUpWindow>(){
    override fun createLayoutManager(): CarouselLayoutManager {
        return  CarouselLayoutManager(this, Util.Dp2px(this, 100f))
    }

    override fun createSettingPopUpWindow(): CarouselPopUpWindow {
        return  CarouselPopUpWindow(this, viewPagerLayoutManager, recyclerView)
    }

}