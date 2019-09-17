package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.gravity;

import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.SpanLayoutChildGravity;

public interface IGravityModifiersFactory {
    IGravityModifier getGravityModifier(@SpanLayoutChildGravity int gravity);
}
