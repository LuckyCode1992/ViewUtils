package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.criteria;

import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.AbstractLayouter;

public interface IFinishingCriteria {
    /** check if layouting finished by criteria */
    boolean isFinishedLayouting(AbstractLayouter abstractLayouter);
}