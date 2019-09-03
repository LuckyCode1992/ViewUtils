package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.criteria;

import android.support.annotation.NonNull;

public interface ICriteriaFactory {
    @NonNull
    IFinishingCriteria getBackwardFinishingCriteria();

    @NonNull
    IFinishingCriteria getForwardFinishingCriteria();
}
