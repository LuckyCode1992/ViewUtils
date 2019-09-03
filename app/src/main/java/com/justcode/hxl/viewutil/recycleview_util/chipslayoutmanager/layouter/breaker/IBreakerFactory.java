package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.breaker;

public interface IBreakerFactory {
    ILayoutRowBreaker createBackwardRowBreaker();

    ILayoutRowBreaker createForwardRowBreaker();
}
