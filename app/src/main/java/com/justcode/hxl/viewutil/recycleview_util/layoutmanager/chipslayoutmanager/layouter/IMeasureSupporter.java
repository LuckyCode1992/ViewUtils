package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter;

import android.support.v7.widget.RecyclerView;

public interface IMeasureSupporter {
    void onItemsRemoved(RecyclerView recyclerView);

    void onSizeChanged();

    void measure(int autoWidth, int autoHeight);

    int getMeasuredWidth();

    int getMeasuredHeight();

    boolean isRegistered();

    void setRegistered(boolean isRegistered);
}
