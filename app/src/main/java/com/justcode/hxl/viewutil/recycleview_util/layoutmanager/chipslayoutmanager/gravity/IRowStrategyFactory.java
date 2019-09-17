package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.gravity;

import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.RowStrategy;

public interface IRowStrategyFactory {
    IRowStrategy createRowStrategy(@RowStrategy int rowStrategy);
}
