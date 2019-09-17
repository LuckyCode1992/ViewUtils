package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter.criteria;

import android.support.annotation.NonNull;

public class InfiniteCriteriaFactory extends AbstractCriteriaFactory implements ICriteriaFactory {
    @NonNull
    @Override
    public IFinishingCriteria getBackwardFinishingCriteria() {
        return new InfiniteCriteria();
    }

    @NonNull
    @Override
    public IFinishingCriteria getForwardFinishingCriteria() {
        return new InfiniteCriteria();
    }
}
