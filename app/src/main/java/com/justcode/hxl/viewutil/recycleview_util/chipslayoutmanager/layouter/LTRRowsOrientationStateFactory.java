package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter;

import android.support.v7.widget.RecyclerView;
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.gravity.IRowStrategyFactory;
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.gravity.LTRRowStrategyFactory;
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.breaker.IBreakerFactory;
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.breaker.LTRRowBreakerFactory;

class LTRRowsOrientationStateFactory implements IOrientationStateFactory {

    @Override
    public ILayouterCreator createLayouterCreator(RecyclerView.LayoutManager lm) {
        return new LTRRowsCreator(lm);
    }

    @Override
    public IRowStrategyFactory createRowStrategyFactory() {
        return new LTRRowStrategyFactory();
    }

    @Override
    public IBreakerFactory createDefaultBreaker() {
        return new LTRRowBreakerFactory();
    }
}
