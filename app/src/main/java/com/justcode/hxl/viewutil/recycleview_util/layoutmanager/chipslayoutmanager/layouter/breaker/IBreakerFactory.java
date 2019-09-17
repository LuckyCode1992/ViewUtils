package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter.breaker;

public interface IBreakerFactory {
    ILayoutRowBreaker createBackwardRowBreaker();

    ILayoutRowBreaker createForwardRowBreaker();
}
