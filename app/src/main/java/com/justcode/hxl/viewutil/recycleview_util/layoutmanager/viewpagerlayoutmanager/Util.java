package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.viewpagerlayoutmanager;

import android.content.Context;

import java.util.Locale;



public class Util {
    public static int Dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static String formatFloat(float value) {
        return String.format(Locale.getDefault(), "%.3f", value);
    }
}
