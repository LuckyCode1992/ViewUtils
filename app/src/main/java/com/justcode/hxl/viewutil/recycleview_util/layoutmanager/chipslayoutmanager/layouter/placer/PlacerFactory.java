package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.layouter.placer;

import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.ChipsLayoutManager;

public class PlacerFactory {

    private ChipsLayoutManager lm;

    public PlacerFactory(ChipsLayoutManager lm) {
        this.lm = lm;
    }

    public IPlacerFactory createRealPlacerFactory() {
        return new RealPlacerFactory(lm);
    }

    public IPlacerFactory createDisappearingPlacerFactory() {
        return new DisappearingPlacerFactory(lm);
    }

}
