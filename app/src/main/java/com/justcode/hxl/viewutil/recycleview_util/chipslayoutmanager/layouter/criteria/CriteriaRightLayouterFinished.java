package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.criteria;

import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.AbstractLayouter;

class CriteriaRightLayouterFinished implements IFinishingCriteria {

    @Override
    public boolean isFinishedLayouting(AbstractLayouter abstractLayouter) {
        return abstractLayouter.getViewLeft() >= abstractLayouter.getCanvasRightBorder();
    }
}
