package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.breaker;

import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.AbstractLayouter;

/** this is basis row breaker for {@link com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.LTRUpLayouter} */
class LTRBackwardRowBreaker implements ILayoutRowBreaker {
    @Override
    public boolean isRowBroke(AbstractLayouter al) {
        return al.getViewRight() - al.getCurrentViewWidth() < al.getCanvasLeftBorder()
                && al.getViewRight() < al.getCanvasRightBorder();
    }
}
