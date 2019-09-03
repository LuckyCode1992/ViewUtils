package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter;

import android.view.View;
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.IScrollingController;
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.anchor.AnchorViewState;
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.anchor.IAnchorFactory;
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.criteria.AbstractCriteriaFactory;
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.criteria.ICriteriaFactory;
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.placer.IPlacerFactory;

public interface IStateFactory {
    @SuppressWarnings("UnnecessaryLocalVariable")
    LayouterFactory createLayouterFactory(ICriteriaFactory criteriaFactory, IPlacerFactory placerFactory);

    AbstractCriteriaFactory createDefaultFinishingCriteriaFactory();

    IAnchorFactory anchorFactory();

    IScrollingController scrollingController();

    ICanvas createCanvas();

    int getSizeMode();

    int getStart();

    int getStart(View view);

    int getStart(AnchorViewState anchor);

    int getStartAfterPadding();

    int getStartViewPosition();

    int getStartViewBound();

    int getEnd();

    int getEnd(View view);

    int getEndAfterPadding();

    int getEnd(AnchorViewState anchor);

    int getEndViewPosition();

    int getEndViewBound();

    int getTotalSpace();
}
