package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.gravity;



import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter.AbstractLayouter;
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter.Item;

import java.util.List;

public interface IRowStrategy {
    void applyStrategy(AbstractLayouter abstractLayouter, List<Item> row);
}
