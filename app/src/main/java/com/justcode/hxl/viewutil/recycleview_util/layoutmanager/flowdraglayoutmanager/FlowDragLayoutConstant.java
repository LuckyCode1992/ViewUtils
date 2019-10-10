package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.flowdraglayoutmanager;

import android.support.annotation.IntDef;



public interface FlowDragLayoutConstant {

    /**
     * 设置每一行的对齐模式
     */
    int TWO_SIDE = 0;
    int LEFT = 1;
    int RIGHT = 2;
    int CENTER = 3;

    @IntDef({TWO_SIDE, LEFT, RIGHT, CENTER})
    @interface AlignMode {

    }
}
