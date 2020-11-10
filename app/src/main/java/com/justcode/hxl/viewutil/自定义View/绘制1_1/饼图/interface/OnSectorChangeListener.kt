package com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.`interface`

import com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.entry.PieEntry

interface OnSectorChangeListener<T : PieEntry> {
    /**
     *
     * @param pieEntry
     * @param cx
     * @param cy
     * @param radius
     * @param source  BaseSectorDrawable.Source
     */
    fun onSectorChange(pieEntry: T?, cx: Int, cy: Int, radius: Int, source: Int)
}