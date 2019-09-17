package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.util.log;

import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.anchor.AnchorViewState;

public interface IFillLogger {

    void onStartLayouter(int startPosition);

    void onItemRequested();

    void onItemRecycled();

    void onFinishedLayouter();

    void onAfterLayouter();

    void onRemovedAndRecycled(int position);

    void onAfterRemovingViews();

    void onBeforeLayouter(AnchorViewState state);
}

