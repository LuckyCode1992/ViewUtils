package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.util.testing;

import android.support.v7.widget.RecyclerView;

public interface ISpy {
    void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state);
}
