package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager;

interface IPositionsContract {
    int findFirstVisibleItemPosition();
    int findFirstCompletelyVisibleItemPosition();
    int findLastVisibleItemPosition();
    int findLastCompletelyVisibleItemPosition();
}
