package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter.breaker;

import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter.AbstractLayouter;



class LTRBackwardRowBreaker implements ILayoutRowBreaker {
    @Override
    public boolean isRowBroke(AbstractLayouter al) {
        return al.getViewRight() - al.getCurrentViewWidth() < al.getCanvasLeftBorder()
                && al.getViewRight() < al.getCanvasRightBorder();
    }
}
