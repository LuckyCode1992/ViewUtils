package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager;

interface IScrollingContract {
    void setScrollingEnabledContract(boolean isEnabled);

    boolean isScrollingEnabledContract();

    void setSmoothScrollbarEnabled(boolean enabled);

    boolean isSmoothScrollbarEnabled();
}
