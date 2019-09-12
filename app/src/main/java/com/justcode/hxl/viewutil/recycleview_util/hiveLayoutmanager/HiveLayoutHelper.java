package com.justcode.hxl.viewutil.recycleview_util.hiveLayoutmanager;

import android.support.v7.widget.RecyclerView;


public abstract class HiveLayoutHelper {

    static final int HORIZONTAL = 0;

    static final int VERTICAL = 1;

    RecyclerView.LayoutManager mLayoutManager;

    public HiveLayoutHelper(RecyclerView.LayoutManager layoutManager) {
        mLayoutManager = layoutManager;
    }


    public static HiveLayoutHelper getInstance(final RecyclerView.LayoutManager layoutManager) {
        return new HiveLayoutHelper(layoutManager) {

            @Override
            public void getChildBounds(int position) {

            }
        };
    }

    public abstract void getChildBounds(int position);


}
