package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.gravity;

import android.view.Gravity;
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.SpanLayoutChildGravity;

public class CenterChildGravity implements IChildGravityResolver {
    @Override
    @SpanLayoutChildGravity
    public int getItemGravity(int position) {
        return Gravity.CENTER_VERTICAL;
    }
}
