package com.justcode.hxl.viewutil.自定义控件.第二章_视图动画

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.*
import com.justcode.hxl.viewutil.R
import kotlinx.android.synthetic.main.activity_view_animation_interpolator.*

/**
 * 插值器
 */
class ViewAnimationInterpolatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_animation_interpolator)
        val translate = TranslateAnimation(
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.ABSOLUTE,
            400f,
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            0f
        )
        translate.duration = 4000
        translate.fillAfter = true

        val rorate = RotateAnimation(
            0f, 950f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 1f
        )
        rorate.fillAfter = true
        rorate.duration = 3000

        btn_accelerate_decelerate_interpolator.setOnClickListener {
            translate.interpolator = AccelerateDecelerateInterpolator()
            tv_accelerate_decelerate_interpolator.startAnimation(translate)
        }
        btn_accelerate_interpolator.setOnClickListener {
            rorate.interpolator = AccelerateInterpolator()
            tv_accelerate_interpolator.startAnimation(rorate)
        }
        btn_decelerate_interpolator.setOnClickListener {
            rorate.interpolator = DecelerateInterpolator()
            tv_decelerate_interpolator.startAnimation(rorate)
        }
        btn_linear_interpolator.setOnClickListener {
            rorate.interpolator = LinearInterpolator()
            tv__linear_interpolator.startAnimation(rorate)
        }
        btn_bounce_interpolator.setOnClickListener {
            rorate.interpolator = BounceInterpolator()
            tv_bounce_interpolator.startAnimation(rorate)
        }
        btn_anticipate_interpolator.setOnClickListener {
            translate.interpolator = AnticipateInterpolator()
            tv_anticipate_interpolator.startAnimation(translate)
        }
        btn_overshoot_interpolator.setOnClickListener {
            translate.interpolator = OvershootInterpolator()
            tv_overshoot_interpolator.startAnimation(translate)
        }
        btn_anticipate_overshoot_interpolator.setOnClickListener {
            translate.interpolator = AnticipateOvershootInterpolator()
            tv_anticipate_overshoot_interpolator.startAnimation(translate)
        }
        btn_cycle_interpolator.setOnClickListener {
            translate.interpolator = CycleInterpolator(2f)
            tv_cycle_interpolator.startAnimation(translate)
        }


    }
}
