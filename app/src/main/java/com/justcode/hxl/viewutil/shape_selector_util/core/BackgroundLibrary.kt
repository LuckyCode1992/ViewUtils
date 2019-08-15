package com.justcode.hxl.viewutil.shape_selector_util.core

import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity

import android.view.LayoutInflater


object BackgroundLibrary {
    fun inject(context: Context): LayoutInflater? {
        val inflater: LayoutInflater?
        if (context is Activity) {
            inflater = context.layoutInflater
        } else {
            inflater = LayoutInflater.from(context)
        }
        if (inflater == null) {
            return null
        }
        if (inflater.factory2 == null) {
            val factory = BackgroundFactory()
            if (context is AppCompatActivity) {
                val delegate = context.delegate
                factory.setInterceptFactory(LayoutInflater.Factory { name, context, attrs -> delegate.createView(null, name, context, attrs) })
            }
            inflater.factory2 = factory
        } else if (inflater.factory2 !is BackgroundFactory) {
            forceSetFactory2(inflater)
        }
        return inflater
    }

    fun forceSetFactory2(inflater: LayoutInflater) {
        try {
            //反射方法获取mFactorySet对象
            val field = LayoutInflater::class.java.getDeclaredField("mFactorySet")
            field.isAccessible = true
            field.setBoolean(inflater, false)

            val factory = BackgroundFactory()
            if (inflater.factory2 != null) {
                factory.setInterceptFactory2(inflater.factory2)
            } else if (inflater.factory != null) {
                factory.setInterceptFactory(inflater.factory)
            }
            inflater.factory2 = factory
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

    }
}