package com.justcode.hxl.viewutil.shape_selector_util.core

import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.GradientDrawable.LINEAR_GRADIENT
import android.os.Build
import com.justcode.hxl.viewutil.R
import org.xmlpull.v1.XmlPullParserException
import java.util.ArrayList

class GradientDrawableCreator(var typedArray: TypedArray) {

    fun create(): Drawable {
        val drawable = GradientDrawable()
        val cornerRadius = FloatArray(8)
        var sizeWidth = 0f
        var sizeHeight = 0f
        var strokeWidth = -1f
        var strokeDashWidth = 0f
        var strokeColor = 0
        var solidColor = 0
        var strokeGap = 0f
        var centerX = 0f
        var centerY = 0f
        var centerColor = 0
        var startColor = 0
        var endColor = 0
        var gradientType = LINEAR_GRADIENT
        var gradientAngle = 0
        val padding = Rect()
        for (i in 0 until typedArray.indexCount) {
            val attr = typedArray.getIndex(i)
            if (attr == R.styleable.background_bl_shape) {
                drawable.shape = typedArray.getInt(attr, 0)
            } else if (attr == R.styleable.background_bl_solid_color) {
                solidColor = typedArray.getColor(attr, 0)
            } else if (attr == R.styleable.background_bl_corners_radius) {
                drawable.cornerRadius = typedArray.getDimension(attr, 0f)
            } else if (attr == R.styleable.background_bl_corners_bottomLeftRadius) {
                cornerRadius[6] = typedArray.getDimension(attr, 0f)
                cornerRadius[7] = typedArray.getDimension(attr, 0f)
            } else if (attr == R.styleable.background_bl_corners_bottomRightRadius) {
                cornerRadius[4] = typedArray.getDimension(attr, 0f)
                cornerRadius[5] = typedArray.getDimension(attr, 0f)
            } else if (attr == R.styleable.background_bl_corners_topLeftRadius) {
                cornerRadius[0] = typedArray.getDimension(attr, 0f)
                cornerRadius[1] = typedArray.getDimension(attr, 0f)
            } else if (attr == R.styleable.background_bl_corners_topRightRadius) {
                cornerRadius[2] = typedArray.getDimension(attr, 0f)
                cornerRadius[3] = typedArray.getDimension(attr, 0f)
            } else if (attr == R.styleable.background_bl_gradient_angle) {
                gradientAngle = typedArray.getInteger(attr, 0)
            } else if (attr == R.styleable.background_bl_gradient_centerX) {
                centerX = typedArray.getFloat(attr, -1f)
            } else if (attr == R.styleable.background_bl_gradient_centerY) {
                centerY = typedArray.getFloat(attr, -1f)
            } else if (attr == R.styleable.background_bl_gradient_centerColor) {
                centerColor = typedArray.getColor(attr, 0)
            } else if (attr == R.styleable.background_bl_gradient_endColor) {
                endColor = typedArray.getColor(attr, 0)
            } else if (attr == R.styleable.background_bl_gradient_startColor) {
                startColor = typedArray.getColor(attr, 0)
            } else if (attr == R.styleable.background_bl_gradient_gradientRadius) {
                drawable.gradientRadius = typedArray.getDimension(attr, 0f)
            } else if (attr == R.styleable.background_bl_gradient_type) {
                gradientType = typedArray.getInt(attr, 0)
                drawable.gradientType = gradientType
            } else if (attr == R.styleable.background_bl_gradient_useLevel) {
                drawable.useLevel = typedArray.getBoolean(attr, false)
            } else if (attr == R.styleable.background_bl_padding_left) {
                padding.left = typedArray.getDimension(attr, 0f).toInt()
            } else if (attr == R.styleable.background_bl_padding_top) {
                padding.top = typedArray.getDimension(attr, 0f).toInt()
            } else if (attr == R.styleable.background_bl_padding_right) {
                padding.right = typedArray.getDimension(attr, 0f).toInt()
            } else if (attr == R.styleable.background_bl_padding_bottom) {
                padding.bottom = typedArray.getDimension(attr, 0f).toInt()
            } else if (attr == R.styleable.background_bl_size_width) {
                sizeWidth = typedArray.getDimension(attr, 0f)
            } else if (attr == R.styleable.background_bl_size_height) {
                sizeHeight = typedArray.getDimension(attr, 0f)
            } else if (attr == R.styleable.background_bl_stroke_width) {
                strokeWidth = typedArray.getDimension(attr, 0f)
            } else if (attr == R.styleable.background_bl_stroke_color) {
                strokeColor = typedArray.getColor(attr, 0)
            } else if (attr == R.styleable.background_bl_stroke_dashWidth) {
                strokeDashWidth = typedArray.getDimension(attr, 0f)
            } else if (attr == R.styleable.background_bl_stroke_dashGap) {
                strokeGap = typedArray.getDimension(attr, 0f)
            }
        }
        if (hasSetRadius(cornerRadius)) {
            drawable.cornerRadii = cornerRadius
        }
        if (typedArray.hasValue(R.styleable.background_bl_size_width) && typedArray.hasValue(R.styleable.background_bl_size_height)) {
            drawable.setSize(sizeWidth.toInt(), sizeHeight.toInt())
        }
        //设置填充颜色
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            var start = 0
            var stateList: ArrayList<Int>? = ArrayList()
            var colorList: ArrayList<Int>? = ArrayList()
            if (typedArray.hasValue(R.styleable.background_bl_pressed_solid_color) && typedArray.hasValue(R.styleable.background_bl_unPressed_solid_color)) {
                stateList!!.add(android.R.attr.state_pressed)
                stateList.add(-android.R.attr.state_pressed)
                colorList!!.add(typedArray.getColor(R.styleable.background_bl_pressed_solid_color, 0))
                colorList.add(typedArray.getColor(R.styleable.background_bl_unPressed_solid_color, 0))
            }
            if (typedArray.hasValue(R.styleable.background_bl_checkable_solid_color) && typedArray.hasValue(R.styleable.background_bl_unCheckable_solid_color)) {
                stateList!!.add(android.R.attr.state_checkable)
                stateList.add(-android.R.attr.state_checkable)
                colorList!!.add(typedArray.getColor(R.styleable.background_bl_checkable_solid_color, 0))
                colorList.add(typedArray.getColor(R.styleable.background_bl_unCheckable_solid_color, 0))
            }
            if (typedArray.hasValue(R.styleable.background_bl_checked_solid_color) && typedArray.hasValue(R.styleable.background_bl_unChecked_solid_color)) {
                stateList!!.add(android.R.attr.state_checked)
                stateList.add(-android.R.attr.state_checked)
                colorList!!.add(typedArray.getColor(R.styleable.background_bl_checked_solid_color, 0))
                colorList.add(typedArray.getColor(R.styleable.background_bl_unChecked_solid_color, 0))
            }
            if (typedArray.hasValue(R.styleable.background_bl_enabled_solid_color) && typedArray.hasValue(R.styleable.background_bl_unEnabled_solid_color)) {
                stateList!!.add(android.R.attr.state_enabled)
                stateList.add(-android.R.attr.state_enabled)
                colorList!!.add(typedArray.getColor(R.styleable.background_bl_enabled_solid_color, 0))
                colorList.add(typedArray.getColor(R.styleable.background_bl_unEnabled_solid_color, 0))
            }
            if (typedArray.hasValue(R.styleable.background_bl_selected_solid_color) && typedArray.hasValue(R.styleable.background_bl_unSelected_solid_color)) {
                stateList!!.add(android.R.attr.state_selected)
                stateList.add(-android.R.attr.state_selected)
                colorList!!.add(typedArray.getColor(R.styleable.background_bl_selected_solid_color, 0))
                colorList.add(typedArray.getColor(R.styleable.background_bl_unSelected_solid_color, 0))
            }
            if (typedArray.hasValue(R.styleable.background_bl_focused_solid_color) && typedArray.hasValue(R.styleable.background_bl_unFocused_solid_color)) {
                stateList!!.add(android.R.attr.state_focused)
                stateList.add(-android.R.attr.state_focused)
                colorList!!.add(typedArray.getColor(R.styleable.background_bl_focused_solid_color, 0))
                colorList.add(typedArray.getColor(R.styleable.background_bl_unFocused_solid_color, 0))
            }

            if (stateList!!.size > 0) {
                val state = arrayOfNulls<IntArray>(stateList.size)
                val color = IntArray(stateList.size)
                for (iState in stateList) {
                    state[start] = intArrayOf(iState)
                    color[start] = colorList!![start]
                    start++
                }
                val colorStateList = ColorStateList(state, color)
                drawable.color = colorStateList
            } else if (typedArray.hasValue(R.styleable.background_bl_solid_color)) {
                drawable.setColor(solidColor)
            }
            stateList = null
            colorList = null
        } else if (typedArray.hasValue(R.styleable.background_bl_solid_color)) {
            drawable.setColor(solidColor)
        }

        //设置边框颜色
        if (typedArray.hasValue(R.styleable.background_bl_stroke_width)) {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                var start = 0
                var stateList: ArrayList<Int>? = ArrayList()
                var colorList: ArrayList<Int>? = ArrayList()
                if (typedArray.hasValue(R.styleable.background_bl_pressed_stroke_color) && typedArray.hasValue(R.styleable.background_bl_unPressed_stroke_color)) {
                    stateList!!.add(android.R.attr.state_pressed)
                    stateList.add(-android.R.attr.state_pressed)
                    colorList!!.add(typedArray.getColor(R.styleable.background_bl_pressed_stroke_color, 0))
                    colorList.add(typedArray.getColor(R.styleable.background_bl_unPressed_stroke_color, 0))
                }
                if (typedArray.hasValue(R.styleable.background_bl_checkable_stroke_color) && typedArray.hasValue(R.styleable.background_bl_unCheckable_stroke_color)) {
                    stateList!!.add(android.R.attr.state_checkable)
                    stateList.add(-android.R.attr.state_checkable)
                    colorList!!.add(typedArray.getColor(R.styleable.background_bl_checkable_stroke_color, 0))
                    colorList.add(typedArray.getColor(R.styleable.background_bl_unCheckable_stroke_color, 0))
                }
                if (typedArray.hasValue(R.styleable.background_bl_checked_stroke_color) && typedArray.hasValue(R.styleable.background_bl_unChecked_stroke_color)) {
                    stateList!!.add(android.R.attr.state_checked)
                    stateList.add(-android.R.attr.state_checked)
                    colorList!!.add(typedArray.getColor(R.styleable.background_bl_checked_stroke_color, 0))
                    colorList.add(typedArray.getColor(R.styleable.background_bl_unChecked_stroke_color, 0))
                }
                if (typedArray.hasValue(R.styleable.background_bl_enabled_stroke_color) && typedArray.hasValue(R.styleable.background_bl_unEnabled_stroke_color)) {
                    stateList!!.add(android.R.attr.state_enabled)
                    stateList.add(-android.R.attr.state_enabled)
                    colorList!!.add(typedArray.getColor(R.styleable.background_bl_enabled_stroke_color, 0))
                    colorList.add(typedArray.getColor(R.styleable.background_bl_unEnabled_stroke_color, 0))
                }
                if (typedArray.hasValue(R.styleable.background_bl_selected_stroke_color) && typedArray.hasValue(R.styleable.background_bl_unSelected_stroke_color)) {
                    stateList!!.add(android.R.attr.state_selected)
                    stateList.add(-android.R.attr.state_selected)
                    colorList!!.add(typedArray.getColor(R.styleable.background_bl_selected_stroke_color, 0))
                    colorList.add(typedArray.getColor(R.styleable.background_bl_unSelected_stroke_color, 0))
                }
                if (typedArray.hasValue(R.styleable.background_bl_focused_stroke_color) && typedArray.hasValue(R.styleable.background_bl_unFocused_stroke_color)) {
                    stateList!!.add(android.R.attr.state_focused)
                    stateList.add(-android.R.attr.state_focused)
                    colorList!!.add(typedArray.getColor(R.styleable.background_bl_focused_stroke_color, 0))
                    colorList.add(typedArray.getColor(R.styleable.background_bl_unFocused_stroke_color, 0))
                }
                if (stateList!!.size > 0) {
                    val state = arrayOfNulls<IntArray>(stateList.size)
                    val color = IntArray(stateList.size)
                    for (iState in stateList) {
                        state[start] = intArrayOf(iState)
                        color[start] = colorList!![start]
                        start++
                    }
                    val colorStateList = ColorStateList(state, color)
                    drawable.setStroke(strokeWidth.toInt(), colorStateList, strokeDashWidth, strokeGap)
                } else if (typedArray.hasValue(R.styleable.background_bl_stroke_color)) {
                    drawable.setStroke(strokeWidth.toInt(), strokeColor, strokeDashWidth, strokeGap)
                }

                stateList = null
                colorList = null
            } else if (typedArray.hasValue(R.styleable.background_bl_stroke_color)) {
                drawable.setStroke(strokeWidth.toInt(), strokeColor, strokeDashWidth, strokeGap)
            }
        }

        if (typedArray.hasValue(R.styleable.background_bl_gradient_centerX) && typedArray.hasValue(R.styleable.background_bl_gradient_centerY)) {
            drawable.setGradientCenter(centerX, centerY)
        }

        if (typedArray.hasValue(R.styleable.background_bl_gradient_startColor) &&
            typedArray.hasValue(R.styleable.background_bl_gradient_endColor) &&
            android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
        ) {
            val colors: IntArray
            if (typedArray.hasValue(R.styleable.background_bl_gradient_centerColor)) {
                colors = IntArray(3)
                colors[0] = startColor
                colors[1] = centerColor
                colors[2] = endColor
            } else {
                colors = IntArray(2)
                colors[0] = startColor
                colors[1] = endColor
            }
            drawable.colors = colors
        }
        if (gradientType == LINEAR_GRADIENT &&
            typedArray.hasValue(R.styleable.background_bl_gradient_angle) &&
            android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
        ) {
            gradientAngle %= 360
            if (gradientAngle % 45 != 0) {
                throw XmlPullParserException(
                    typedArray.positionDescription
                            + "<gradient> tag requires 'angle' attribute to "
                            + "be a multiple of 45"
                )
            }
            var mOrientation: GradientDrawable.Orientation = GradientDrawable.Orientation.LEFT_RIGHT
            when (gradientAngle) {
                0 -> mOrientation = GradientDrawable.Orientation.LEFT_RIGHT
                45 -> mOrientation = GradientDrawable.Orientation.BL_TR
                90 -> mOrientation = GradientDrawable.Orientation.BOTTOM_TOP
                135 -> mOrientation = GradientDrawable.Orientation.BR_TL
                180 -> mOrientation = GradientDrawable.Orientation.RIGHT_LEFT
                225 -> mOrientation = GradientDrawable.Orientation.TR_BL
                270 -> mOrientation = GradientDrawable.Orientation.TOP_BOTTOM
                315 -> mOrientation = GradientDrawable.Orientation.TL_BR
            }
            drawable.orientation = mOrientation

        }

        if (typedArray.hasValue(R.styleable.background_bl_padding_left) &&
            typedArray.hasValue(R.styleable.background_bl_padding_top) &&
            typedArray.hasValue(R.styleable.background_bl_padding_right) &&
            typedArray.hasValue(R.styleable.background_bl_padding_bottom)
        ) {
            try {
                val paddingField = drawable.javaClass.getDeclaredField("mPadding")
                paddingField.isAccessible = true
                paddingField.set(drawable, padding)
            } catch (e: NoSuchFieldException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }

        }
        return drawable
    }
    private fun hasSetRadius(radius: FloatArray): Boolean {
        var hasSet = false
        for (f in radius) {
            if (f != 0.0f) {
                hasSet = true
                break
            }
        }
        return hasSet
    }
}