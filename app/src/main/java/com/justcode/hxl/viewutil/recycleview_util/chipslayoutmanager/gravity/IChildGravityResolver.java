package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.gravity;

import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.SpanLayoutChildGravity;

/** class which determines child gravity inside row from child position */
public interface IChildGravityResolver {
    @SpanLayoutChildGravity
    int getItemGravity(int position);
}
