package com.justcode.hxl.viewutil.shape_selector_util.core.drawable

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.shape_selector_util.core.util.MultiSelector
import com.justcode.hxl.viewutil.shape_selector_util.core.util.ResourceUtils

class MultiTextColorSelectorColorCreator(var context: Context, var selectorTa: TypedArray) {
    private var states = arrayOf<IntArray>()
    private var colors = intArrayOf()
    private var index: Int = 0

    @Throws(Exception::class)
    fun create(): ColorStateList {
        states = arrayOf(IntArray(selectorTa.indexCount))
        colors = IntArray(selectorTa.indexCount)

        for (i in 0 until selectorTa.indexCount) {
            val attr = selectorTa.getIndex(i)

            if (attr == R.styleable.background_multi_selector_text_bl_multi_text_selector1) {
                addState(attr)
            } else if (attr == R.styleable.background_multi_selector_text_bl_multi_text_selector2) {
                addState(attr)
            } else if (attr == R.styleable.background_multi_selector_text_bl_multi_text_selector3) {
                addState(attr)
            } else if (attr == R.styleable.background_multi_selector_text_bl_multi_text_selector4) {
                addState(attr)
            } else if (attr == R.styleable.background_multi_selector_text_bl_multi_text_selector5) {
                addState(attr)
            } else if (attr == R.styleable.background_multi_selector_text_bl_multi_text_selector6) {
                addState(attr)
            }
        }
        return ColorStateList(states, colors)
    }

    private fun addState(attr: Int) {
        val value = selectorTa.getString(attr)
        if (value != null) {
            val vArray = value.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (vArray.size < 2) {
                throw IllegalArgumentException("Attributes and drawable must be set at the same time")
            }
            var color = 0
            val attrId = IntArray(vArray.size - 1)
            for (p in vArray.indices) {
                val attrStr = vArray[p]
                //取出资源id
                if (p == vArray.size - 1) {
                    color = ResourceUtils.getColor(context, attrStr)
                    if (color == -1) {
                        throw IllegalArgumentException("cannot find color from the last attribute")
                    }
                } else {
                    val multiSelector =
                        MultiSelector.getMultiAttr(attrStr.replace("-", "")) ?: throw IllegalArgumentException(
                            "the attribute of bl_multi_selector only support " +
                                    "state_checkable, state_checked, state_enabled, state_selected, state_pressed, state_focused, " +
                                    "state_hovered, state_activated"
                        )
                    if (attrStr.contains("-")) {
                        attrId[p] = -multiSelector!!.id
                    } else {
                        attrId[p] = multiSelector!!.id
                    }
                }
            }
            states[index] = attrId
            colors[index] = color
            index++
        }
    }
}
