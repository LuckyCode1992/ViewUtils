package com.justcode.hxl.viewutil.shape_selector_util.core.drawable

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.shape_selector_util.core.DrawableFactory
import com.justcode.hxl.viewutil.shape_selector_util.core.util.MultiSelector
import com.justcode.hxl.viewutil.shape_selector_util.core.util.ResourceUtils
import org.xmlpull.v1.XmlPullParserException

class MultiSelectorDrawableCreator(

    var context: Context,
    var selectorTa: TypedArray,
    var typedArray: TypedArray

) {
    var gradientDrawable: GradientDrawable? = null
    @Throws(Exception::class)
    fun create(): Drawable {
        val stateListDrawable = StateListDrawable()

        for (i in 0 until selectorTa.indexCount) {
            val attr = selectorTa.getIndex(i)

            if (attr == R.styleable.background_multi_selector_bl_multi_selector1) {
                addState(stateListDrawable, attr)
            } else if (attr == R.styleable.background_multi_selector_bl_multi_selector2) {
                addState(stateListDrawable, attr)
            } else if (attr == R.styleable.background_multi_selector_bl_multi_selector3) {
                addState(stateListDrawable, attr)
            } else if (attr == R.styleable.background_multi_selector_bl_multi_selector4) {
                addState(stateListDrawable, attr)
            } else if (attr == R.styleable.background_multi_selector_bl_multi_selector5) {
                addState(stateListDrawable, attr)
            } else if (attr == R.styleable.background_multi_selector_bl_multi_selector6) {
                addState(stateListDrawable, attr)
            }
        }
        return stateListDrawable
    }
    @Throws(Exception::class)
    private fun addState(stateListDrawable: StateListDrawable, attr: Int) {
        val value = selectorTa.getString(attr)
        if (value != null) {
            val vArray = value.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (vArray.size < 2) {
                throw IllegalArgumentException("Attributes and drawable must be set at the same time")
            }
            var drawable: Drawable? = null
            val attrId = IntArray(vArray.size - 1)
            for (p in vArray.indices) {
                val attrStr = vArray[p]
                //取出资源id
                if (p == vArray.size - 1) {
                    val color = ResourceUtils.getColor(context, attrStr)
                    if (typedArray.indexCount > 0) {
                        try {
                            gradientDrawable = DrawableFactory.getDrawable(typedArray)
                        } catch (e: XmlPullParserException) {
                            e.printStackTrace()
                        }

                    }
                    if (gradientDrawable != null && color != -1) {
                        gradientDrawable!!.setColor(color)
                        drawable = gradientDrawable
                    } else {
                        drawable = ResourceUtils.getDrawable(context, attrStr)
                    }
                    if (drawable == null) {
                        throw IllegalArgumentException("cannot find drawable from the last attribute")
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
            stateListDrawable.addState(attrId, drawable)
        }
    }
}