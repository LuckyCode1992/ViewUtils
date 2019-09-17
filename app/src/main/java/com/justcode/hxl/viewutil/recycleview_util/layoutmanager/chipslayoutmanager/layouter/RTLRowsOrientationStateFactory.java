package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter;

import android.support.v7.widget.RecyclerView;
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.gravity.IRowStrategyFactory;
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.gravity.RTLRowStrategyFactory;
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter.breaker.IBreakerFactory;
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter.breaker.RTLRowBreakerFactory;

class RTLRowsOrientationStateFactory implements IOrientationStateFactory {

    @Override
    public ILayouterCreator createLayouterCreator(RecyclerView.LayoutManager lm) {
        return new RTLRowsCreator(lm);
    }

    @Override
    public IRowStrategyFactory createRowStrategyFactory() {
        return new RTLRowStrategyFactory();
    }

    @Override
    public IBreakerFactory createDefaultBreaker() {
        return new RTLRowBreakerFactory();
    }
}
