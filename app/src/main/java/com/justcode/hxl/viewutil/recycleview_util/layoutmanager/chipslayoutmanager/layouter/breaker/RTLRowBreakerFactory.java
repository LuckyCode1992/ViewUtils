package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter.breaker;

public class RTLRowBreakerFactory implements IBreakerFactory {
    @Override
    public ILayoutRowBreaker createBackwardRowBreaker() {
        return new RTLBackwardRowBreaker();
    }

    @Override
    public ILayoutRowBreaker createForwardRowBreaker() {
        return new RTLForwardRowBreaker();
    }
}
