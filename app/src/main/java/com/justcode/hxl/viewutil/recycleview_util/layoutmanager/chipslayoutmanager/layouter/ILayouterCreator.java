package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.anchor.AnchorViewState;

interface ILayouterCreator {
    //---- up layouter below
    Rect createOffsetRectForBackwardLayouter(@NonNull AnchorViewState anchorRect);

    AbstractLayouter.Builder createBackwardBuilder();

    AbstractLayouter.Builder createForwardBuilder();

    Rect createOffsetRectForForwardLayouter(AnchorViewState anchorRect);
}
