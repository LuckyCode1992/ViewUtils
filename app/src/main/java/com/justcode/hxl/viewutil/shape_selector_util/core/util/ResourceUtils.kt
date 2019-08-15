package com.justcode.hxl.viewutil.shape_selector_util.core.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat

object ResourceUtils {
    fun getDrawable(context: Context, resName: String): Drawable? {
        val resources = context.resources
        if (resName.startsWith("#")) {
            return ColorDrawable(Color.parseColor(resName))
        }
        var id = resources.getIdentifier(resName, "drawable", context.packageName)
        if (id == 0) {
            id = resources.getIdentifier(resName, "mipmap", context.packageName)
        }
        if (id == 0) {
            id = resources.getIdentifier(resName, "color", context.packageName)
        }
        return if (id == 0) {
            null
        } else ContextCompat.getDrawable(context, id)
    }


    fun getColor(context: Context, resName: String): Int {
        val resources = context.resources
        if (resName.startsWith("#")) {
            return Color.parseColor(resName)
        }
        val id = resources.getIdentifier(resName, "color", context.packageName)
        return if (id == 0) {
            -1
        } else ContextCompat.getColor(context, id)
    }
}