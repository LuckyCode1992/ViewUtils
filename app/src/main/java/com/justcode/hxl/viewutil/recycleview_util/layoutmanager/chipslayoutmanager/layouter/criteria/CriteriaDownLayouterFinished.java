package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter.criteria;

import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter.AbstractLayouter;

class CriteriaDownLayouterFinished implements IFinishingCriteria {

    private boolean isFinished;

    @Override
    public boolean isFinishedLayouting(AbstractLayouter abstractLayouter) {
        isFinished = isFinished || abstractLayouter.getViewTop() >= abstractLayouter.getCanvasBottomBorder();
        return isFinished;
    }

}
