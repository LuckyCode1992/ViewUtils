package com.justcode.hxl.viewutil.extend

import android.app.Activity
import android.content.Context
import android.content.Intent

inline fun <reified T : Activity> Context.start() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}