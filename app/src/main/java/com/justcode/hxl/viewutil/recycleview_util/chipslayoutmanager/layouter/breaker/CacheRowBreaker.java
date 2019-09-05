package com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.breaker;


import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.cache.IViewCacheStorage;
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.layouter.AbstractLayouter;

class CacheRowBreaker extends RowBreakerDecorator {

    private IViewCacheStorage cacheStorage;

    CacheRowBreaker(IViewCacheStorage cacheStorage, ILayoutRowBreaker decorate) {
        super(decorate);
        this.cacheStorage = cacheStorage;
    }

    @Override
    public boolean isRowBroke(AbstractLayouter al) {
        boolean stopDueToCache = cacheStorage.isPositionEndsRow(al.getCurrentViewPosition());
        return super.isRowBroke(al) || stopDueToCache;
    }
}