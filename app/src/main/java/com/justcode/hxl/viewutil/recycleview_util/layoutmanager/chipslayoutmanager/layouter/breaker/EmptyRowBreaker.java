package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter.breaker;

public class EmptyRowBreaker implements IRowBreaker {
    @Override
    public boolean isItemBreakRow(int position) {
        return false;
    }
}
