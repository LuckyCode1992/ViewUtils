package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter.breaker;

public class LTRRowBreakerFactory implements IBreakerFactory {
    @Override
    public ILayoutRowBreaker createBackwardRowBreaker() {
        return new LTRBackwardRowBreaker();
    }

    @Override
    public ILayoutRowBreaker createForwardRowBreaker() {
        return new LTRForwardRowBreaker();
    }
}
