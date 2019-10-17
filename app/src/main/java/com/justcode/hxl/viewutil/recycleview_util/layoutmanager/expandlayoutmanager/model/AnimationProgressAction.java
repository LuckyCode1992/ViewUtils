package com.justcode.hxl.viewutil.recycleview_util.layoutmanager.expandlayoutmanager.model;

import android.support.annotation.NonNull;
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.expandlayoutmanager.AnimationAction;
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.expandlayoutmanager.ExpandLayoutManager;


import java.io.Serializable;
import java.util.*;

class AnimationProgressAction {

    private AnimationProgress mExpandItem;
    private final Set<AnimationProgress> mCollapseItems;

    private final List<Integer> mTmpList;

    AnimationProgressAction() {
        mCollapseItems = new HashSet<>();
        mTmpList = new ArrayList<>();
    }

    boolean mergeActions(@NonNull final AnimationAction animationAction) {
        boolean hasChanges = false;
        if (ExpandLayoutManager.INVALID_POSITION != animationAction.mExpandPosition) {
            AnimationProgress newExpandItem = getCollapseItem(animationAction.mExpandPosition);
            if (null == newExpandItem) {
                newExpandItem = new AnimationProgress(animationAction.mExpandPosition);
            } else {
                newExpandItem = revertProgress(newExpandItem);
                mCollapseItems.remove(newExpandItem);
            }

            if (null == mExpandItem) {
                mExpandItem = newExpandItem;
                hasChanges = true;
            } else {
                if (mExpandItem.mAdapterPosition != animationAction.mExpandPosition) {
                    mCollapseItems.add(revertProgress(mExpandItem));
                    mExpandItem = newExpandItem;
                    hasChanges = true;
                }
            }
        }

        if (ExpandLayoutManager.INVALID_POSITION != animationAction.mCollapsePosition) {
            if (null != mExpandItem && mExpandItem.mAdapterPosition == animationAction.mCollapsePosition) {
                mCollapseItems.add(revertProgress(mExpandItem));
                mExpandItem = null;
                hasChanges = true;
            } else {
                hasChanges = mCollapseItems.add(new AnimationProgress(animationAction.mCollapsePosition)) || hasChanges;
            }
        }
        return hasChanges;
    }

    private AnimationProgress revertProgress(final AnimationProgress animationProgress) {
        animationProgress.mProgress = 1 - animationProgress.mProgress;
        return animationProgress;
    }

    List<Integer> getCollapseItems() {
        if (mCollapseItems.isEmpty()) {
            return Collections.emptyList();
        }
        mTmpList.clear();
        for (final AnimationProgress animationProgress : mCollapseItems) {
            mTmpList.add(animationProgress.mAdapterPosition);
        }
        return mTmpList;
    }

    int getExpandItem() {
        if (null == mExpandItem) {
            return ExpandLayoutManager.INVALID_POSITION;
        }
        return mExpandItem.mAdapterPosition;
    }

    Float getExpandProgress(final int adapterPosition) {
        if (null == mExpandItem) {
            return null;
        }
        if (mExpandItem.mAdapterPosition == adapterPosition) {
            return mExpandItem.mProgress;
        }
        return null;
    }

    Float getCollapseProgress(final int adapterPosition) {
        final AnimationProgress animationProgress = getCollapseItem(adapterPosition);
        return null == animationProgress ? null : animationProgress.mProgress;
    }

    private AnimationProgress getCollapseItem(final int adapterPosition) {
        for (final AnimationProgress animationProgress : mCollapseItems) {
            if (animationProgress.mAdapterPosition == adapterPosition) {
                return animationProgress;
            }
        }
        return null;
    }

    void addProgress(final float progress) {
        if (null != mExpandItem) {
            addProgressInner(mExpandItem, progress);
        }
        for (final AnimationProgress animationProgress : mCollapseItems) {
            addProgressInner(animationProgress, progress);
        }
    }

    private static void addProgressInner(final AnimationProgress expandItem, final float progress) {
        expandItem.mProgress = Math.min(1, expandItem.mProgress + progress);
    }

    void clearData() {
        mExpandItem = null;
        mCollapseItems.clear();
    }

    boolean checkEnds() {
        if (null != mExpandItem && mExpandItem.mProgress < 0.9999f) {
            return false;
        }
        for (final AnimationProgress animationProgress : mCollapseItems) {
            if (animationProgress.mProgress < 0.9999f) {
                return false;
            }
        }
        return true;
    }

    boolean hasCollapseItems() {
        return !mCollapseItems.isEmpty();
    }

    private static class AnimationProgress implements Serializable {

        private static final long serialVersionUID = 8091777646155824257L;

        private final int mAdapterPosition;
        private float mProgress;

        AnimationProgress(final int adapterPosition) {
            mAdapterPosition = adapterPosition;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof AnimationProgress)) {
                return false;
            }

            final AnimationProgress animationProgress = (AnimationProgress) o;

            return mAdapterPosition == animationProgress.mAdapterPosition;
        }

        @Override
        public int hashCode() {
            return mAdapterPosition;
        }
    }
}
