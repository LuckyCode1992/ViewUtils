package com.justcode.hxl.viewutil.shape_selector_util.core.drawable

import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.support.annotation.AttrRes
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.shape_selector_util.core.DrawableFactory


class SelectorDrawableCreator(var typedArray: TypedArray, var selectorTa: TypedArray) {
    @Throws(Exception::class)
    fun create():Drawable{
        val stateListDrawable = StateListDrawable()

        for (i in 0 until selectorTa.indexCount) {
            val attr = selectorTa.getIndex(i)

            if (attr == R.styleable.background_selector_bl_checkable_drawable) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, android.R.attr.state_checkable)
            } else if (attr == R.styleable.background_selector_bl_unCheckable_drawable) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, -android.R.attr.state_checkable)
            } else if (attr == R.styleable.background_selector_bl_checked_drawable) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, android.R.attr.state_checked)
            } else if (attr == R.styleable.background_selector_bl_unChecked_drawable) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, -android.R.attr.state_checked)
            } else if (attr == R.styleable.background_selector_bl_enabled_drawable) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, android.R.attr.state_enabled)
            } else if (attr == R.styleable.background_selector_bl_unEnabled_drawable) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, -android.R.attr.state_enabled)
            } else if (attr == R.styleable.background_selector_bl_selected_drawable) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, android.R.attr.state_selected)
            } else if (attr == R.styleable.background_selector_bl_unSelected_drawable) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, -android.R.attr.state_selected)
            } else if (attr == R.styleable.background_selector_bl_pressed_drawable) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, android.R.attr.state_pressed)
            } else if (attr == R.styleable.background_selector_bl_unPressed_drawable) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, -android.R.attr.state_pressed)
            } else if (attr == R.styleable.background_selector_bl_focused_drawable) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, android.R.attr.state_focused)
            } else if (attr == R.styleable.background_selector_bl_unFocused_drawable) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, -android.R.attr.state_focused)
            } else if (attr == R.styleable.background_selector_bl_focused_hovered) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, android.R.attr.state_hovered)
            } else if (attr == R.styleable.background_selector_bl_unFocused_hovered) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, -android.R.attr.state_hovered)
            } else if (attr == R.styleable.background_selector_bl_focused_activated) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, android.R.attr.state_activated)
            } else if (attr == R.styleable.background_selector_bl_unFocused_activated) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, -android.R.attr.state_activated)
            }
        }
        return stateListDrawable
    }
    @Throws(Exception::class)
    private fun setSelectorDrawable(
        typedArray: TypedArray, selectorTa: TypedArray,
        stateListDrawable: StateListDrawable, attr: Int, @AttrRes functionId: Int
    ) {
        var color = 0
        var resDrawable: Drawable? = null

        //这里用try catch先判断是否是颜色而不是直接调用getDrawable，为了方便填入的是颜色时可以沿用其他属性,
        //否则如果是其他资源会覆盖app:corners_radius等其他shape属性设置的效果
        try {
            color = selectorTa.getColor(attr, 0)
            if (color == 0) {
                resDrawable = selectorTa.getDrawable(attr)
            }
        } catch (e: Exception) {
            resDrawable = selectorTa.getDrawable(attr)
        }

        if (resDrawable == null && color != 0) {
            val tmpDrawable = DrawableFactory.getDrawable(typedArray)
            tmpDrawable.setColor(color)
            stateListDrawable.addState(intArrayOf(functionId), tmpDrawable)
        } else {
            stateListDrawable.addState(intArrayOf(functionId), resDrawable)
        }
    }
}