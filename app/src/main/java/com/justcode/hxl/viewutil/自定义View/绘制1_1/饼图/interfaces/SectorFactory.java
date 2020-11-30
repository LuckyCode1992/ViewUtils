package com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.interfaces;

import android.support.annotation.NonNull;

import com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.drawable.BaseSectorDrawable;
import com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.entry.PieEntry;


public interface SectorFactory <T extends PieEntry,K extends BaseSectorDrawable> {
    K createSector(@NonNull T pieEntry, int position);
}
