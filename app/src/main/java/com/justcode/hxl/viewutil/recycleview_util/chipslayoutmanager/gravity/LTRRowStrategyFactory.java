package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.gravity;

import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.ChipsLayoutManager;
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.RowStrategy;

public class LTRRowStrategyFactory implements IRowStrategyFactory {

    @Override
    public IRowStrategy createRowStrategy(@RowStrategy int rowStrategy) {
        switch (rowStrategy) {
            case ChipsLayoutManager.STRATEGY_CENTER:
                return new LTRRowFillSpaceCenterStrategy();
            case ChipsLayoutManager.STRATEGY_CENTER_DENSE:
                return new LTRRowFillSpaceCenterDenseStrategy();
            case ChipsLayoutManager.STRATEGY_FILL_SPACE:
                return new LTRRowFillSpaceStrategy();
            case ChipsLayoutManager.STRATEGY_FILL_VIEW:
                return new LTRRowFillStrategy();
            case ChipsLayoutManager.STRATEGY_DEFAULT:
            default:
                return new EmptyRowStrategy();
        }
    }
}
