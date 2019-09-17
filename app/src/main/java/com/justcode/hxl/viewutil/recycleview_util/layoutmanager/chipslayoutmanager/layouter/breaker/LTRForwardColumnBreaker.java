package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter.breaker;

import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter.AbstractLayouter;

public class LTRForwardColumnBreaker implements ILayoutRowBreaker {

    @Override
    public boolean isRowBroke(AbstractLayouter al) {
        return al.getViewTop() > al.getCanvasTopBorder()
                && al.getViewTop() + al.getCurrentViewHeight() > al.getCanvasBottomBorder();
    }
}
