package com.justcode.hxl.viewutil.自定义控件.视图动画

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.justcode.hxl.viewutil.R
import kotlinx.android.synthetic.main.activity_view_animation.*

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

        btn_scale_xml.setOnClickListener {
            tv_scale_xml.startAnimation(getAnimation(R.anim.scale))
        }
        btn_alpha_xml.setOnClickListener {
            tv_alpha_xml.startAnimation(getAnimation(R.anim.alpha))
        }
        btn_rotate_xml.setOnClickListener {
            tv_rotate_xml.startAnimation(getAnimation(R.anim.rotate))
        }
        btn_translate_xml.setOnClickListener {
            tv_translate_xml.startAnimation(getAnimation(R.anim.translate))
        }
        btn_set_xml.setOnClickListener {
            tv_set_xml.startAnimation(getAnimation(R.anim.set))
        }
    }

    private fun getAnimation(anim: Int): Animation {
        val animation = AnimationUtils.loadAnimation(this,anim)
        return animation
    }
}
