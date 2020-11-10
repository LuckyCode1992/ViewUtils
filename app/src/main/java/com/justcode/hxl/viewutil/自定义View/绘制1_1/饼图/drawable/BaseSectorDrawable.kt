package com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.drawable

import android.graphics.ColorFilter
import android.graphics.PixelFormat
import com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.`interface`.OnSectorChangeListener
import com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.entry.PieEntry
import java.util.*
import kotlin.collections.ArrayList

abstract class BaseSectorDrawable<T : PieEntry> {

    //圆心
    private var originCx = 0  //圆心
    private var originCy = 0  //圆心
    private var originRadius = 0
    private var isHighlighting = false
    protected var mPieEntry: T? = null
    private var mOnSectorChangeListeners: MutableList<OnSectorChangeListener<T>>? = ArrayList()

    fun BaseSectorDrawable(pieEntry: T?) {
        mPieEntry = pieEntry
    }

    /**
     * 按下扇形区,进入高亮状态isHighlighting=true
     */
    abstract fun press()

    /**
     * 收回扇形区,退出高亮状态isHighlighting=false
     */
    abstract fun unPress()

    fun notifyDataChanged() {
        if (null != getPieEntry() && originRadius > 0) {
            notifySectorChange(getPieEntry(), originCx, originCy, originRadius, Source.INIT)
        }
    }

    protected fun notifySectorChange(
        pieEntry: T?,
        cx: Int,
        cy: Int,
        radius: Int,
        source: Int
    ) {
        if (source == Source.INIT) {
            originCx = cx
            originCy = cy
            originRadius = radius
        }
        if (null != mOnSectorChangeListeners && !mOnSectorChangeListeners!!.isEmpty()) {
            for (onSectorChangeListener in mOnSectorChangeListeners!!) {
                onSectorChangeListener.onSectorChange(pieEntry, cx, cy, radius, source)
            }
        }
    }

    /**
     * 设置是否在高亮状态
     *
     * @param isHighlighting
     */
    protected fun setHighlighting(isHighlighting: Boolean) {
        this.isHighlighting = isHighlighting
    }

    fun isHighlighting(): Boolean {
        return isHighlighting
    }

    fun setPieEntry(pieEntry: T) {
        mPieEntry = pieEntry
    }

    fun getPieEntry(): T? {
        return mPieEntry
    }

    fun getOriginCx(): Int {
        return originCx
    }

    fun getOriginCy(): Int {
        return originCy
    }

    fun getOriginRadius(): Int {
        return originRadius
    }

    /**
     * 触摸区域是否在此扇形区内
     *
     * @param clickX
     * @param clickY
     * @return
     */
    abstract fun containAngle(clickX: Float, clickY: Float): Boolean

    abstract fun offsetAngle(offsetAngle: Float)

    fun <K : OnSectorChangeListener<T>> addOnSectorChangeListener(onSectorChangeListener: K) {
        if (null == mOnSectorChangeListeners) mOnSectorChangeListeners =
            ArrayList<OnSectorChangeListener<T>>()
        mOnSectorChangeListeners!!.add(onSectorChangeListener)
    }

    fun removeOnSectorChangeListener(onSectorChangeListener: OnSectorChangeListener<T>) {
        if (mOnSectorChangeListeners == null) return
        mOnSectorChangeListeners?.remove(onSectorChangeListener)
        if (mOnSectorChangeListeners!!.isEmpty()) mOnSectorChangeListeners = null
    }

    fun removeAllOnSectorChangeListener() {
        if (mOnSectorChangeListeners == null) return
        mOnSectorChangeListeners!!.clear()
        mOnSectorChangeListeners = null
    }

    fun setAlpha(alpha: Int) {}

    fun setColorFilter(colorFilter: ColorFilter?) {}

    fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    object Source {
        const val INIT = 0
        const val ROTATE = 1
        const val HIGHLIGHT = 2
    }
}