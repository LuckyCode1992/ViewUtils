package com.justcode.hxl.viewutil.shape_selector_util.core.drawable

import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.support.annotation.AttrRes
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.shape_selector_util.core.DrawableFactory

class ButtonDrawableCreator(var typedArray: TypedArray, var buttonTa: TypedArray) {
    @Throws(Exception::class)
    fun create(): Drawable {
        val stateListDrawable = StateListDrawable()

        for (i in 0 until buttonTa.indexCount) {
            val attr = buttonTa.getIndex(i)

            if (attr == R.styleable.background_button_drawable_bl_checked_button_drawable) {
                setSelectorDrawable(typedArray, buttonTa, stateListDrawable, attr, android.R.attr.state_checked)
            } else if (attr == R.styleable.background_button_drawable_bl_unChecked_button_drawable) {
                setSelectorDrawable(typedArray, buttonTa, stateListDrawable, attr, -android.R.attr.state_checked)
            }
        }
        return stateListDrawable
    }

    @Throws(Exception::class)
    private fun setSelectorDrawable(
        typedArray: TypedArray, buttonTa: TypedArray,
        stateListDrawable: StateListDrawable, attr: Int, @AttrRes functionId: Int
    ) {
        var color = 0
        var resDrawable: Drawable? = null

        //这里用try catch先判断是否是颜色而不是直接调用getDrawable，为了方便填入的是颜色时可以沿用其他属性,
        //否则如果是其他资源会覆盖app:corners_radius等其他shape属性设置的效果
        try {
            color = buttonTa.getColor(attr, 0)
            if (color == 0) {
                resDrawable = buttonTa.getDrawable(attr)
            }
        } catch (e: Exception) {
            resDrawable = buttonTa.getDrawable(attr)
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