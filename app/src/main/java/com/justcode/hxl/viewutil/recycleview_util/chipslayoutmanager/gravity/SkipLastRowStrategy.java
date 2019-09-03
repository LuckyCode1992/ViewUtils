package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.gravity;

import android.support.annotation.NonNull;
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.AbstractLayouter;
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.Item;

import java.util.List;

public class SkipLastRowStrategy extends StrategyDecorator {

    private boolean skipLastRow;

    public SkipLastRowStrategy(@NonNull IRowStrategy rowStrategy, boolean skipLastRow) {
        super(rowStrategy);
        this.skipLastRow = skipLastRow;
    }

    @Override
    public void applyStrategy(AbstractLayouter abstractLayouter, List<Item> row) {
        //if !canNotBePlacedInCurrentRow and apply strategy called probably it is last row
        //so skip applying strategy
        if (skipLastRow && !abstractLayouter.isRowCompleted()) return;
        super.applyStrategy(abstractLayouter, row);
    }
}
