package com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.drawable

import android.content.Context
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.PixelFormat
import android.graphics.PointF
import android.graphics.drawable.Drawable
import com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.`interface`.OnSectorChangeListener
import com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.entry.PieEntry

abstract class BaseTextDrawable<T : PieEntry> : Drawable, OnSectorChangeListener<T> {
    private var mContext: Context? = null
    private var pieEntry: T? = null
    protected var mTextPoint: PointF? = null

    constructor(mContext: Context?) : super() {
        this.mContext = mContext
    }


    abstract fun setTextPoint(pieEntry: T?, cx: Int, cy: Int, radius: Int, source: Int)
    override fun onSectorChange(pieEntry: T?, cx: Int, cy: Int, radius: Int, source: Int) {
        this.pieEntry = pieEntry
        setTextPoint(pieEntry, cx, cy, radius, source)
    }

    fun getContext(): Context? {
        return mContext
    }

    fun getTextPoint(): PointF? {
        return mTextPoint
    }

    fun getPieEntry(): T? {
        return pieEntry
    }

    override fun draw(canvas: Canvas?) {}

    override fun setAlpha(alpha: Int) {}

    override fun setColorFilter(colorFilter: ColorFilter?) {}

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

}