package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter;

import android.support.v7.widget.RecyclerView;
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.gravity.IRowStrategyFactory;
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.breaker.IBreakerFactory;

interface IOrientationStateFactory {
    ILayouterCreator createLayouterCreator(RecyclerView.LayoutManager lm);
    IRowStrategyFactory createRowStrategyFactory();
    IBreakerFactory createDefaultBreaker();
}
