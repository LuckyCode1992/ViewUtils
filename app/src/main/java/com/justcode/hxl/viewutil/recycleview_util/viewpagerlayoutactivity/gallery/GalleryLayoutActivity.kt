package com.justcode.hxl.viewutil.recycleview_util.viewpagerlayoutactivity.gallery

import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.viewpagerlayoutmanager.GalleryLayoutManager
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.viewpagerlayoutmanager.Util
import com.justcode.hxl.viewutil.recycleview_util.viewpagerlayoutactivity.BaseViewPagerActivity

class GalleryLayoutActivity : BaseViewPagerActivity<GalleryLayoutManager,GalleryPopUpWindow>(){
    override fun createLayoutManager(): GalleryLayoutManager {
        return GalleryLayoutManager(this, Util.Dp2px(this, 10f))
    }

    override fun createSettingPopUpWindow(): GalleryPopUpWindow {
        return GalleryPopUpWindow(this, viewPagerLayoutManager, recyclerView)
    }

}