package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.breaker;

import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.AbstractLayouter;

public class LTRBackwardColumnBreaker implements ILayoutRowBreaker {

    @Override
    public boolean isRowBroke(AbstractLayouter al) {
        return al.getViewBottom() - al.getCurrentViewHeight() < al.getCanvasTopBorder()
                && al.getViewBottom() < al.getCanvasBottomBorder();
    }
}
