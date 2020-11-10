package com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.`interface`

import com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.drawable.BaseSectorDrawable
import com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.entry.PieEntry

interface SectorFactory<T : PieEntry, K : BaseSectorDrawable> {
    fun createSector(pieEntry: T, position: Int): K
}