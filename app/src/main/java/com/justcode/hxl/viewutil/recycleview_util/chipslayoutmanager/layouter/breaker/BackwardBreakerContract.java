package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.breaker;


import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.AbstractLayouter;

class BackwardBreakerContract extends RowBreakerDecorator{

    private IRowBreaker breaker;

    BackwardBreakerContract(IRowBreaker breaker, ILayoutRowBreaker decorate) {
        super(decorate);
        this.breaker = breaker;
    }

    @Override
    public boolean isRowBroke(AbstractLayouter al) {
        return super.isRowBroke(al) ||
                breaker.isItemBreakRow(al.getCurrentViewPosition());
    }
}
