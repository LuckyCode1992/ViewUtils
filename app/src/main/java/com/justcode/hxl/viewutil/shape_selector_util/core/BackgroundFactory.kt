package com.justcode.hxl.viewutil.shape_selector_util.core

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.drawable.*
import android.os.Build
import android.support.v4.util.ArrayMap
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.view.InflateException
import android.view.LayoutInflater
import android.view.View
import android.widget.CompoundButton
import android.widget.TextView
import com.justcode.hxl.viewutil.R
import java.lang.reflect.Constructor

class BackgroundFactory : LayoutInflater.Factory2 {
    private var mViewCreateFactory: LayoutInflater.Factory? = null
    private var mViewCreateFactory2: LayoutInflater.Factory2? = null

    private val sConstructorSignature = arrayOf(Context::class.java, AttributeSet::class.java)
    private val mConstructorArgs = arrayOfNulls<Any>(2)
    private val sConstructorMap = ArrayMap<String, Constructor<out View>>()

    fun setInterceptFactory2(factory: LayoutInflater.Factory2) {
        mViewCreateFactory2 = factory
    }

    fun setInterceptFactory(factory: LayoutInflater.Factory) {
        mViewCreateFactory = factory
    }


    override fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet): View? {
        return onCreateView(name, context, attrs)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        var view: View? = null
        //防止与其他调用factory库冲突，例如字体、皮肤替换库，用已经设置的factory来创建view
        if (mViewCreateFactory2 != null) {
            view = mViewCreateFactory2!!.onCreateView(name, context, attrs)
            if (view == null) {
                view = mViewCreateFactory2!!.onCreateView(null, name, context, attrs)
            }
        } else if (mViewCreateFactory != null) {
            view = mViewCreateFactory!!.onCreateView(name, context, attrs)
        }
        return setViewBackground(name, context, attrs, view)
    }

    fun setViewBackground(name: String, context: Context, attrs: AttributeSet, view0: View?): View? {
        var view: View? = null
        view = view0
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.background)
        val pressTa = context.obtainStyledAttributes(attrs, R.styleable.background_press)
        val selectorTa = context.obtainStyledAttributes(attrs, R.styleable.background_selector)
        val textTa = context.obtainStyledAttributes(attrs, R.styleable.text_selector)
        val buttonTa = context.obtainStyledAttributes(attrs, R.styleable.background_button_drawable)
        val otherTa = context.obtainStyledAttributes(attrs, R.styleable.bl_other)
        val multiSelTa = context.obtainStyledAttributes(attrs, R.styleable.background_multi_selector)
        val multiTextTa = context.obtainStyledAttributes(attrs, R.styleable.background_multi_selector_text)

        try {
            if (typedArray.indexCount == 0 && selectorTa.indexCount == 0 && pressTa.indexCount == 0
                    && textTa.indexCount == 0 && buttonTa.indexCount == 0
                    && multiSelTa.indexCount == 0 && multiTextTa.indexCount == 0) {
                return view
            }
            if (view == null) {
                view = createViewFromTag(context, name, attrs)
            }
            if (view == null) {
                return null
            }
            //R.styleable.background_selector 和 R.styleable.background_multi_selector的属性不能同时使用
            if (selectorTa.indexCount > 0 && multiSelTa.indexCount > 0) {
                throw IllegalArgumentException("Background_selector and background_multi_selector cannot be used simultaneously")
            }
            if (textTa.indexCount > 0 && multiTextTa.indexCount > 0) {
                throw IllegalArgumentException("text_selector and background_multi_selector_text cannot be used simultaneously")
            }

            var drawable: GradientDrawable? = null
            var stateListDrawable: StateListDrawable? = null
            if (buttonTa.indexCount > 0 && view is CompoundButton) {
                view.setClickable(true)
                view.setButtonDrawable(DrawableFactory.getButtonDrawable(typedArray, buttonTa))
            } else if (selectorTa.indexCount > 0) {
                stateListDrawable = DrawableFactory.getSelectorDrawable(typedArray, selectorTa)
                view.isClickable = true
                setDrawable(stateListDrawable, view, otherTa, typedArray)
            } else if (pressTa.indexCount > 0) {
                drawable = DrawableFactory.getDrawable(typedArray)
                stateListDrawable = DrawableFactory.getPressDrawable(drawable, typedArray, pressTa)
                view.isClickable = true
                setDrawable(stateListDrawable, view, otherTa, typedArray)
            } else if (multiSelTa.indexCount > 0) {
                stateListDrawable = DrawableFactory.getMultiSelectorDrawable(context, multiSelTa, typedArray)
                setBackground(stateListDrawable, view, typedArray)
            } else if (typedArray.indexCount > 0) {
                drawable = DrawableFactory.getDrawable(typedArray)
                setDrawable(drawable, view, otherTa, typedArray)
            }

            if (view is TextView && textTa.indexCount > 0) {
                view.setTextColor(DrawableFactory.getTextSelectorColor(textTa))
            } else if (view is TextView && multiTextTa.indexCount > 0) {
                view.setTextColor(DrawableFactory.getMultiTextColorSelectorColorCreator(context, multiTextTa))
            }

            if (typedArray.getBoolean(R.styleable.background_bl_ripple_enable, false) && typedArray.hasValue(R.styleable.background_bl_ripple_color)) {
                val color = typedArray.getColor(R.styleable.background_bl_ripple_color, 0)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val contentDrawable = stateListDrawable ?: drawable
                    val rippleDrawable = RippleDrawable(ColorStateList.valueOf(color), contentDrawable, contentDrawable)
                    view.isClickable = true
                    setBackground(rippleDrawable, view, typedArray)
                } else if (stateListDrawable == null) {
                    val tmpDrawable = StateListDrawable()
                    val unPressDrawable = DrawableFactory.getDrawable(typedArray)
                    unPressDrawable.setColor(color)
                    tmpDrawable.addState(intArrayOf(-android.R.attr.state_pressed), drawable)
                    tmpDrawable.addState(intArrayOf(android.R.attr.state_pressed), unPressDrawable)
                    view.isClickable = true
                    setDrawable(tmpDrawable, view, otherTa, typedArray)
                }
            }

            return view
        } catch (e: Exception) {
            e.printStackTrace()
            return view
        } finally {
            typedArray.recycle()
            pressTa.recycle()
            selectorTa.recycle()
            textTa.recycle()
            buttonTa.recycle()
            otherTa.recycle()
            multiSelTa.recycle()
            multiTextTa.recycle()
        }
    }

    private fun setDrawable(drawable: Drawable, view: View, otherTa: TypedArray, typedArray: TypedArray) {

        if (view is TextView) {
            if (otherTa.hasValue(R.styleable.bl_other_bl_position)) {
                if (otherTa.getInt(R.styleable.bl_other_bl_position, 0) == 1) {
                    drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
                    view.setCompoundDrawables(drawable, null, null, null)
                } else if (otherTa.getInt(R.styleable.bl_other_bl_position, 0) == 2) {
                    drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
                    view.setCompoundDrawables(null, drawable, null, null)
                } else if (otherTa.getInt(R.styleable.bl_other_bl_position, 0) == 4) {
                    drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
                    view.setCompoundDrawables(null, null, drawable, null)
                } else if (otherTa.getInt(R.styleable.bl_other_bl_position, 0) == 8) {
                    drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
                    view.setCompoundDrawables(null, null, null, drawable)
                }
            } else {
                setBackground(drawable, view, typedArray)
            }
        } else {
            setBackground(drawable, view, typedArray)
        }

    }
    private fun setBackground(drawable: Drawable, view: View, typedArray: TypedArray) {
        var drawable = drawable
        if (typedArray.hasValue(R.styleable.background_bl_stroke_width) && typedArray.hasValue(R.styleable.background_bl_stroke_position)) {
            //bl_stroke_position flag默认值
            val left = 1 shl 1
            val top = 1 shl 2
            val right = 1 shl 3
            val bottom = 1 shl 4
            val width = typedArray.getDimension(R.styleable.background_bl_stroke_width, 0f)
            val position = typedArray.getInt(R.styleable.background_bl_stroke_position, 0)
            val leftValue = if (hasStatus(position, left)) width else -width
            val topValue = if (hasStatus(position, top)) width else -width
            val rightValue = if (hasStatus(position, right)) width else -width
            val bottomValue = if (hasStatus(position, bottom)) width else -width
            drawable = LayerDrawable(arrayOf(drawable))
            drawable.setLayerInset(0, leftValue.toInt(), topValue.toInt(), rightValue.toInt(), bottomValue.toInt())
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.background = drawable
        } else {
            view.setBackgroundDrawable(drawable)
        }
    }
    private fun hasStatus(flag: Int, status: Int): Boolean {
        return flag and status == status
    }

    @Throws(InflateException::class)
    private fun createView(context: Context, name: String, prefix: String?): View? {
        var constructor: Constructor<out View>? = sConstructorMap[name]
        try {
            if (constructor == null) {
                val clazz = context.classLoader.loadClass(
                    if (prefix != null) prefix + name else name
                ).asSubclass(View::class.java)

                constructor = clazz.getConstructor(*sConstructorSignature)
                sConstructorMap[name] = constructor
            }
            constructor!!.isAccessible = true
            return constructor.newInstance(*mConstructorArgs)
        } catch (e: Exception) {
            Log.w("BackgroundLibrary", "cannot create 【$name】 : ")
            return null
        }

    }

    private fun createViewFromTag(context: Context, name: String, attrs: AttributeSet): View? {
        var name = name
        if (TextUtils.isEmpty(name)) {
            return null
        }
        if (name == "view") {
            name = attrs.getAttributeValue(null, "class")
        }
        try {
            mConstructorArgs[0] = context
            mConstructorArgs[1] = attrs

            if (-1 == name.indexOf('.')) {
                var view: View? = null
                if ("View" == name) {
                    view = createView(context, name, "android.view.")
                }
                if (view == null) {
                    view = createView(context, name, "android.widget.")
                }
                if (view == null) {
                    view = createView(context, name, "android.webkit.")
                }
                return view
            } else {
                return createView(context, name, null)
            }
        } catch (e: Exception) {
            Log.w("BackgroundLibrary", "cannot create 【$name】 : ")
            return null
        } finally {
            mConstructorArgs[0] = null
            mConstructorArgs[1] = null
        }
    }

}