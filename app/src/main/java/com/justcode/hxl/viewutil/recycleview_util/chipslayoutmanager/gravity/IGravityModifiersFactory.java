package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.gravity;

import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.SpanLayoutChildGravity;

public interface IGravityModifiersFactory {
    IGravityModifier getGravityModifier(@SpanLayoutChildGravity int gravity);
}
