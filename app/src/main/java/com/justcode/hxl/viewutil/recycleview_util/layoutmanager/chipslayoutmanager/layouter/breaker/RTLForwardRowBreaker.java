package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter.breaker;

import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter.AbstractLayouter;



class RTLForwardRowBreaker implements ILayoutRowBreaker {

    @Override
    public boolean isRowBroke(AbstractLayouter al) {
        return al.getViewRight() < al.getCanvasRightBorder()
                && al.getViewRight() - al.getCurrentViewWidth() < al.getCanvasLeftBorder();

    }
}
