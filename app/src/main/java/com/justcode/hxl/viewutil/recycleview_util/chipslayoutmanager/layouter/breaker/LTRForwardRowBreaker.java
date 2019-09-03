package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.breaker;

import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.AbstractLayouter;

/** this is basis row breaker for {@link com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.LTRDownLayouter} */
class LTRForwardRowBreaker implements ILayoutRowBreaker {

    @Override
    public boolean isRowBroke(AbstractLayouter al) {
        return al.getViewLeft() > al.getCanvasLeftBorder()
                && al.getViewLeft() + al.getCurrentViewWidth() > al.getCanvasRightBorder();
    }
}
