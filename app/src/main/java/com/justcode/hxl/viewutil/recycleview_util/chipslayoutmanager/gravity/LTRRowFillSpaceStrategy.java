package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.gravity;

import android.graphics.Rect;
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.AbstractLayouter;
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.Item;

import java.util.List;

class LTRRowFillSpaceStrategy implements IRowStrategy {

    @Override
    public void applyStrategy(AbstractLayouter abstractLayouter, List<Item> row) {
        if (abstractLayouter.getRowSize() == 1) return;
        int difference = GravityUtil.getHorizontalDifference(abstractLayouter) / (abstractLayouter.getRowSize() - 1);
        int offsetDifference = 0;

        for (Item item : row) {
            Rect childRect = item.getViewRect();

            if (childRect.left == abstractLayouter.getCanvasLeftBorder()) {
                //left view of row

                int leftDif = childRect.left - abstractLayouter.getCanvasLeftBorder();
                //press view to left border
                childRect.left = abstractLayouter.getCanvasLeftBorder();
                childRect.right -= leftDif;
                continue;
            }
            offsetDifference += difference;

            childRect.left += offsetDifference;
            childRect.right += offsetDifference;
        }
    }
}
