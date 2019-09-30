package com.justcode.hxl.viewutil.recycleview_util.viewpagerlayoutactivity

import android.content.Context
import android.view.WindowManager
import android.widget.PopupWindow
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.viewpagerlayoutmanager.Util

abstract class SettingPopUpWindow(context: Context) : PopupWindow(context) {
    init {
        isOutsideTouchable = true
        width = Util.Dp2px(context, 320f)
        height = WindowManager.LayoutParams.WRAP_CONTENT
    }
}