package com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.interfaces;


import com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.entry.PieEntry;

public interface PieTextVisibleFilter<T extends PieEntry> {
    boolean isShowText(T pieEntry);
}
