package com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.interfaces;


import com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.drawable.BaseTextDrawable;

public interface PieTextFactory<T extends BaseTextDrawable> {
    T createPieText();
}
