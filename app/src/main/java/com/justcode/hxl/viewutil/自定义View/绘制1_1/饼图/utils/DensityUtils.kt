package com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.utils

import android.content.Context
import android.util.TypedValue

object DensityUtils{

    /**
     * dp转px
     *
     * @param context
     * @return
     */
    fun dp2px(context: Context, dpVal: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dpVal, context.resources.displayMetrics
        ).toInt()
    }

    /**
     * sp转px
     *
     * @param context
     * @return
     */
    fun sp2px(context: Context, spVal: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            spVal, context.resources.displayMetrics
        ).toInt()
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxVal
     * @return
     */
    fun px2dp(context: Context, pxVal: Float): Float {
        val scale = context.resources.displayMetrics.density
        return pxVal / scale
    }

    /**
     * px转sp
     *
     * @param pxVal
     * @return
     */
    fun px2sp(context: Context, pxVal: Float): Float {
        return pxVal / context.resources.displayMetrics.scaledDensity
    }

}