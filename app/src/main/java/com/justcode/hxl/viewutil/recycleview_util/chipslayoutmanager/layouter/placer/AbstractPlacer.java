package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.placer;

import android.support.v7.widget.RecyclerView;

abstract class AbstractPlacer implements IPlacer {

    private RecyclerView.LayoutManager layoutManager;

    AbstractPlacer(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }
}
