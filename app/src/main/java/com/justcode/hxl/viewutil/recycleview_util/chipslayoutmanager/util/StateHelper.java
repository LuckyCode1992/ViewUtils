package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.util;

import android.view.View;
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.IStateFactory;

public class StateHelper {
    public static boolean isInfinite(IStateFactory stateFactory) {
        return stateFactory.getSizeMode() == View.MeasureSpec.UNSPECIFIED
                && stateFactory.getEnd() == 0;
    }
}
