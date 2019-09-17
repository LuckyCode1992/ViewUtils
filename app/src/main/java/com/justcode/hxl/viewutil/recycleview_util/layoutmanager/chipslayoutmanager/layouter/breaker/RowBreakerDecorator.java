package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter.breaker;

import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter.AbstractLayouter;

class RowBreakerDecorator implements ILayoutRowBreaker {

    private ILayoutRowBreaker decorate;

    RowBreakerDecorator(ILayoutRowBreaker decorate) {
        this.decorate = decorate;
    }

    @Override
    public boolean isRowBroke(AbstractLayouter al) {
        return decorate.isRowBroke(al);
    }
}
