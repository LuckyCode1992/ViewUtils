package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.fanlayoutmanager;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Collection;


interface AnimationHelper {
    /**
     * Select view animation with start delay.
     *
     * @param view             view to scale (open)
     * @param delay            start delay duration
     * @param animatorListener select view animation listener
     */
    void openItem(@NonNull final View view, int delay, Animator.AnimatorListener animatorListener);

    /**
     * Deselect view animation with start delay
     *
     * @param view             view to scale (close)
     * @param delay            start delay
     * @param animatorListener deselect view animation listener
     */

    void closeItem(@NonNull final View view, int delay, Animator.AnimatorListener animatorListener);

    /**
     * Move views to sides or to the center of the screen.
     *
     * @param views                  view data information for shift animation {@link ViewAnimationInfo}
     * @param delay                  start delay
     * @param layoutManager          the layout manager
     * @param animatorListener       animator listener to check start or end animation
     * @param animatorUpdateListener value animator listener to check updates
     */

    void shiftSideViews(@NonNull final Collection<ViewAnimationInfo> views,
                        int delay,
                        @NonNull RecyclerView.LayoutManager layoutManager,
                        @Nullable Animator.AnimatorListener animatorListener,
                        @Nullable final ValueAnimator.AnimatorUpdateListener animatorUpdateListener);

    /**
     * @return scale factor for select and deselect animation.
     */
    float getViewScaleFactor();

    /**
     * Rotate view from custom angle to 0.
     *
     * @param view     view to rotate
     * @param listener animator listener to check start or end animation
     */
    void straightenView(View view, @Nullable Animator.AnimatorListener listener);

    /**
     * Rotate view from current to custom angle.
     *
     * @param view     view to rotate
     * @param angle   rotate angle
     * @param listener animator listener to check start or end animation
     */
    void rotateView(View view, float angle, @Nullable Animator.AnimatorListener listener);
}
