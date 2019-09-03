package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.gravity;

import android.graphics.Rect;
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.AbstractLayouter;
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.Item;

import java.util.List;

class RTLRowFillSpaceCenterDenseStrategy implements IRowStrategy {

    @Override
    public void applyStrategy(AbstractLayouter abstractLayouter, List<Item> row) {
        int difference = GravityUtil.getHorizontalDifference(abstractLayouter) / 2;

        for (Item item : row) {
            Rect childRect = item.getViewRect();
            childRect.left -= difference;
            childRect.right -= difference;
        }
    }
}
