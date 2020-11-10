package com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.`interface`

import com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.entry.PieEntry

interface PieTextVisibleFilter<T : PieEntry> {
    fun isShowText(pieEntry: T): Boolean
}