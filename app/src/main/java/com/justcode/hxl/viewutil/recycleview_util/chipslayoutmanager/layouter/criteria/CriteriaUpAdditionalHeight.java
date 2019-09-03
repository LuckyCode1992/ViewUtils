package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.criteria;

import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.AbstractLayouter;

class CriteriaUpAdditionalHeight extends FinishingCriteriaDecorator {

    private int additionalHeight;

    CriteriaUpAdditionalHeight(IFinishingCriteria finishingCriteria, int additionalHeight) {
        super(finishingCriteria);
        this.additionalHeight = additionalHeight;
    }

    @Override
    public boolean isFinishedLayouting(AbstractLayouter abstractLayouter) {
        int topBorder = abstractLayouter.getCanvasTopBorder();
        return super.isFinishedLayouting(abstractLayouter) &&
                //if additional height filled
                abstractLayouter.getViewBottom() < topBorder - additionalHeight;
    }

}
