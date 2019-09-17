package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.gravity;

import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter.AbstractLayouter;
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter.Item;

import java.util.List;

class EmptyRowStrategy implements IRowStrategy {
    @Override
    public void applyStrategy(AbstractLayouter abstractLayouter, List<Item> row) {
        //do nothing
    }
}
