package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.criteria;

import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.AbstractLayouter;
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.ILayouter;
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.ILayouterListener;
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.Item;

public class CriteriaPolitePositionReached extends FinishingCriteriaDecorator implements IFinishingCriteria, ILayouterListener {

    private boolean isPositionReached;
    private int reachedPosition;

    CriteriaPolitePositionReached(AbstractLayouter abstractLayouter, IFinishingCriteria finishingCriteria, int reachedPosition) {
        super(finishingCriteria);
        this.reachedPosition = reachedPosition;
        abstractLayouter.addLayouterListener(this);
    }

    @Override
    public boolean isFinishedLayouting(AbstractLayouter abstractLayouter) {
        boolean isFinishedFlow = super.isFinishedLayouting(abstractLayouter);
        return isFinishedFlow || isPositionReached;
    }

    @Override
    public void onLayoutRow(ILayouter layouter) {
        if (isPositionReached) return;
        if (layouter.getRowSize() == 0) return;
        for (Item item : layouter.getCurrentRowItems()) {
            if (item.getViewPosition() == reachedPosition) {
                isPositionReached = true;
                return;
            }
        }
    }
}
