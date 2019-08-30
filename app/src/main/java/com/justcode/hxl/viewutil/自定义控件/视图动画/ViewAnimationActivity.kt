package com.justcode.hxl.viewutil.自定义控件.视图动画

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.justcode.hxl.viewutil.R

/**
 * Android 中，动画分为两种：View Animation （视图动画），Property Animation (属性动画)。
 *
 * View Animation 包含：Tween Animation(补间动画),Frame Animation (逐帧动画)
 *
 * Property Animation 包含： ValueAnimator , ObjectAnimator
 */

class ViewAnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_animation)
    }
}
