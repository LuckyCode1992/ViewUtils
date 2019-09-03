package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.breaker;

public class ColumnBreakerFactory implements IBreakerFactory {
    @Override
    public ILayoutRowBreaker createBackwardRowBreaker() {
        return new LTRBackwardColumnBreaker();
    }

    @Override
    public ILayoutRowBreaker createForwardRowBreaker() {
        return new LTRForwardColumnBreaker();
    }
}
