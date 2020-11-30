package com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.drawable;

import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;


import com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.entry.PieEntry;
import com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.interfaces.OnSectorChangeListener;

import java.util.ArrayList;
import java.util.List;


/**
 * 扇形
 */
public abstract class BaseSectorDrawable<T extends PieEntry> extends Drawable {
    //圆心
    private int originCx, originCy, originRadius;
    private boolean isHighlighting;
    protected T mPieEntry;
    private List<OnSectorChangeListener<T>> mOnSectorChangeListeners;

    public BaseSectorDrawable(T pieEntry) {
        this.mPieEntry = pieEntry;
    }

    /**
     * 按下扇形区,进入高亮状态isHighlighting=true
     */
    public abstract void press();

    /**
     * 收回扇形区,退出高亮状态isHighlighting=false
     */
    public abstract void unPress();

    public void notifyDataChanged() {
        if (null != getPieEntry() && originRadius > 0) {
            notifySectorChange(getPieEntry(), originCx, originCy, originRadius, Source.INIT);
        }
    }

    protected void notifySectorChange(T pieEntry, int cx, int cy, int radius, int source) {
        if (source == Source.INIT) {
            originCx = cx;
            originCy = cy;
            originRadius = radius;
        }
        if (null != mOnSectorChangeListeners && !mOnSectorChangeListeners.isEmpty()) {
            for (OnSectorChangeListener<T> onSectorChangeListener : mOnSectorChangeListeners) {
                onSectorChangeListener.onSectorChange(pieEntry, cx, cy, radius, source);
            }
        }
    }

    /**
     * 设置是否在高亮状态
     *
     * @param isHighlighting
     */
    protected void setHighlighting(boolean isHighlighting) {
        this.isHighlighting = isHighlighting;
    }

    public boolean isHighlighting() {
        return isHighlighting;
    }

    public void setPieEntry(T pieEntry) {
        mPieEntry = pieEntry;
    }

    public T getPieEntry() {
        return mPieEntry;
    }

    public int getOriginCx() {
        return originCx;
    }

    public int getOriginCy() {
        return originCy;
    }

    public int getOriginRadius() {
        return originRadius;
    }

    /**
     * 触摸区域是否在此扇形区内
     *
     * @param clickX
     * @param clickY
     * @return
     */
    public abstract boolean containAngle(float clickX, float clickY);

    public abstract void offsetAngle(float offsetAngle);

    public <K extends OnSectorChangeListener<T>> void addOnSectorChangeListener(K onSectorChangeListener) {
        if (null == mOnSectorChangeListeners)
            mOnSectorChangeListeners = new ArrayList<>();
        mOnSectorChangeListeners.add(onSectorChangeListener);
    }

    public void removeOnSectorChangeListener(OnSectorChangeListener<T> onSectorChangeListener) {
        if (mOnSectorChangeListeners == null)
            return;
        mOnSectorChangeListeners.remove(onSectorChangeListener);
        if (mOnSectorChangeListeners.isEmpty())
            mOnSectorChangeListeners = null;
    }

    public void removeAllOnSectorChangeListener() {
        if (mOnSectorChangeListeners == null)
            return;
        mOnSectorChangeListeners.clear();
        mOnSectorChangeListeners = null;
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    public class Source {
        public static final int INIT = 0;
        public static final int ROTATE = 1;
        public static final int HIGHLIGHT = 2;
    }
}
