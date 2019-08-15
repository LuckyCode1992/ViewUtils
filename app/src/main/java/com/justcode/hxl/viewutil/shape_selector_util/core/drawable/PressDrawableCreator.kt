package com.justcode.hxl.viewutil.shape_selector_util.core.drawable

import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.shape_selector_util.core.DrawableFactory

class PressDrawableCreator(
    var drawable: GradientDrawable,
    var pressTa: TypedArray,
    var typedArray: TypedArray
) {
    @Throws(Exception::class)
    fun create(): Drawable {
        val stateListDrawable = StateListDrawable()
        for (i in 0 until pressTa.indexCount) {
            val attr = pressTa.getIndex(i)

            if (attr == R.styleable.background_press_bl_pressed_color) {
                val color = pressTa.getColor(attr, 0)
                val pressDrawable = DrawableFactory.getDrawable(typedArray)
                pressDrawable.setColor(color)
                stateListDrawable.addState(intArrayOf(android.R.attr.state_pressed), pressDrawable)
            } else if (attr == R.styleable.background_press_bl_unpressed_color) {
                val color = pressTa.getColor(attr, 0)
                drawable.setColor(color)
                stateListDrawable.addState(intArrayOf(-android.R.attr.state_pressed), drawable)
            }
        }
        return stateListDrawable
    }
}