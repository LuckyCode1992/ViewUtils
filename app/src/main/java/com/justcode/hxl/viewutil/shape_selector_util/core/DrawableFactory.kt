package com.justcode.hxl.viewutil.shape_selector_util.core

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import com.justcode.hxl.viewutil.shape_selector_util.core.drawable.*
import org.xmlpull.v1.XmlPullParserException

object DrawableFactory {
    //获取shape属性的drawable
    @Throws(XmlPullParserException::class)
    fun getDrawable(typedArray: TypedArray): GradientDrawable {
        return GradientDrawableCreator(typedArray).create() as GradientDrawable
    }
    //获取selector属性的drawable
    @Throws(Exception::class)
    fun getSelectorDrawable(typedArray: TypedArray, selectorTa: TypedArray): StateListDrawable {
        return SelectorDrawableCreator(typedArray, selectorTa).create() as StateListDrawable
    }
    //获取button 属性的drawable
    @Throws(Exception::class)
    fun getButtonDrawable(typedArray: TypedArray, buttonTa: TypedArray): StateListDrawable {
        return ButtonDrawableCreator(typedArray, buttonTa).create() as StateListDrawable
    }
    //获取text selector属性关于text的color
    fun getTextSelectorColor(textTa: TypedArray): ColorStateList {
        return ColorStateCreator(textTa).create()
    }
    //适配早期版本的属性
    @Throws(Exception::class)
    fun getPressDrawable(drawable: GradientDrawable, typedArray: TypedArray, pressTa: TypedArray): StateListDrawable {
        return PressDrawableCreator(drawable, typedArray, pressTa).create() as StateListDrawable
    }
    fun getMultiSelectorDrawable(context: Context, selectorTa: TypedArray, typedArray: TypedArray): StateListDrawable {
        return MultiSelectorDrawableCreator(context, selectorTa, typedArray).create() as StateListDrawable
    }
    fun getMultiTextColorSelectorColorCreator(context: Context, selectorTa: TypedArray): ColorStateList {
        return MultiTextColorSelectorColorCreator(context, selectorTa).create()
    }
}