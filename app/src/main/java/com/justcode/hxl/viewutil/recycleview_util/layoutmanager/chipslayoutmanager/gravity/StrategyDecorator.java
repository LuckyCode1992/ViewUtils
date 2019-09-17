package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.gravity;

import android.support.annotation.NonNull;
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter.AbstractLayouter;
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter.Item;

import java.util.List;

class StrategyDecorator implements IRowStrategy {

    @NonNull
    private IRowStrategy rowStrategy;

    StrategyDecorator(@NonNull IRowStrategy rowStrategy) {
        this.rowStrategy = rowStrategy;
    }

    @Override
    public void applyStrategy(AbstractLayouter abstractLayouter, List<Item> row) {
        rowStrategy.applyStrategy(abstractLayouter, row);
    }
}
