package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.fanlayoutmanager.callbacks;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.ref.WeakReference;


public class FanChildDrawingOrderCallback implements RecyclerView.ChildDrawingOrderCallback {

    private WeakReference<RecyclerView.LayoutManager> mLayoutManagerWeakReference;

    public FanChildDrawingOrderCallback(RecyclerView.LayoutManager layoutManager) {
        mLayoutManagerWeakReference = new WeakReference<>(layoutManager);
    }

    @Override
    public int onGetChildDrawingOrder(int childCount, int i) {
        RecyclerView.LayoutManager layoutManager = mLayoutManagerWeakReference.get();
        if (layoutManager != null) {
            View startView = layoutManager.getChildAt(0);
            int position = layoutManager.getPosition(startView);

            boolean isStartFromBelow = true;

            if (position % 2 == 0) {
                isStartFromBelow = true;
            } else {
                isStartFromBelow = false;
            }

            int result = 0;
            if (isStartFromBelow) {
                if (i % 2 == 0) {
                    //front
                    result = i == 0 ? 0 : i - 1;
                } else {
                    //bellow
                    result = i + 1 >= childCount ? i : i + 1;
                }
            } else {
                if (i % 2 == 0) {
                    //front
                    result = i + 1 >= childCount ? i : i + 1;
                } else {
                    //bellow
                    result = i - 1;
                }
            }
//                Log.e("ORDER", "Result = " + result + "; CC = " + childCount + "; i = " + i + "; pos = " + position);

            return result;
        }
        return i;
    }
}
