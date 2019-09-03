package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.gravity;

import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.SpanLayoutChildGravity;

public class CustomGravityResolver implements IChildGravityResolver {

    @SpanLayoutChildGravity
    private int gravity;

    public CustomGravityResolver(int gravity) {
        this.gravity = gravity;
    }

    @Override
    @SpanLayoutChildGravity
    public int getItemGravity(int position) {
        return gravity;
    }
}
