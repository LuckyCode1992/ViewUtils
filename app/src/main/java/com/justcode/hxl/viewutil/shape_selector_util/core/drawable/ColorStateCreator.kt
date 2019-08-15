package com.justcode.hxl.viewutil.shape_selector_util.core.drawable

import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.support.annotation.AttrRes
import com.justcode.hxl.viewutil.R

class ColorStateCreator(var textTa: TypedArray){

    private var states = arrayOf<IntArray>()
    private var colors = intArrayOf()
    private var index: Int = 0
    @Throws(Exception::class)
    fun create(): ColorStateList{
        states = arrayOf(IntArray(textTa.indexCount))
        colors = IntArray(textTa.indexCount)
        for (i in 0 until textTa.indexCount) {
            val attr = textTa.getIndex(i)
            if (attr == R.styleable.text_selector_bl_checkable_textColor) {
                setStateColor(textTa, attr, android.R.attr.state_checkable)
            } else if (attr == R.styleable.text_selector_bl_unCheckable_textColor) {
                setStateColor(textTa, attr, -android.R.attr.state_checkable)
            } else if (attr == R.styleable.text_selector_bl_checked_textColor) {
                setStateColor(textTa, attr, android.R.attr.state_checked)
            } else if (attr == R.styleable.text_selector_bl_unChecked_textColor) {
                setStateColor(textTa, attr, -android.R.attr.state_checked)
            } else if (attr == R.styleable.text_selector_bl_enabled_textColor) {
                setStateColor(textTa, attr, android.R.attr.state_enabled)
            } else if (attr == R.styleable.text_selector_bl_unEnabled_textColor) {
                setStateColor(textTa, attr, -android.R.attr.state_enabled)
            } else if (attr == R.styleable.text_selector_bl_selected_textColor) {
                setStateColor(textTa, attr, android.R.attr.state_selected)
            } else if (attr == R.styleable.text_selector_bl_unSelected_textColor) {
                setStateColor(textTa, attr, -android.R.attr.state_selected)
            } else if (attr == R.styleable.text_selector_bl_pressed_textColor) {
                setStateColor(textTa, attr, android.R.attr.state_pressed)
            } else if (attr == R.styleable.text_selector_bl_unPressed_textColor) {
                setStateColor(textTa, attr, -android.R.attr.state_pressed)
            } else if (attr == R.styleable.text_selector_bl_focused_textColor) {
                setStateColor(textTa, attr, android.R.attr.state_focused)
            } else if (attr == R.styleable.text_selector_bl_unFocused_textColor) {
                setStateColor(textTa, attr, -android.R.attr.state_focused)
            } else if (attr == R.styleable.text_selector_bl_activated_textColor) {
                setStateColor(textTa, attr, android.R.attr.state_activated)
            } else if (attr == R.styleable.text_selector_bl_unActivated_textColor) {
                setStateColor(textTa, attr, -android.R.attr.state_active)
            } else if (attr == R.styleable.text_selector_bl_active_textColor) {
                setStateColor(textTa, attr, android.R.attr.state_active)
            } else if (attr == R.styleable.text_selector_bl_unActive_textColor) {
                setStateColor(textTa, attr, -android.R.attr.state_activated)
            } else if (attr == R.styleable.text_selector_bl_expanded_textColor) {
                setStateColor(textTa, attr, android.R.attr.state_expanded)
            } else if (attr == R.styleable.text_selector_bl_unExpanded_textColor) {
                setStateColor(textTa, attr, -android.R.attr.state_expanded)
            }

        }
        return ColorStateList(states, colors)
    }
    @Throws(Exception::class)
    private fun setStateColor(selectorTa: TypedArray, attr: Int, @AttrRes functionId: Int) {
        states[index] = intArrayOf(functionId)
        colors[index] = selectorTa.getColor(attr, 0)
        index++
    }
}