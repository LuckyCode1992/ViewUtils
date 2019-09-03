package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager;

import android.support.annotation.IntDef;
import android.view.Gravity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({Gravity.TOP,
        Gravity.BOTTOM,
        Gravity.CENTER,
        Gravity.CENTER_VERTICAL,
        Gravity.CENTER_HORIZONTAL,
        Gravity.LEFT,
        Gravity.RIGHT,
        Gravity.FILL
})
@Retention(RetentionPolicy.SOURCE)
public @interface SpanLayoutChildGravity {}
