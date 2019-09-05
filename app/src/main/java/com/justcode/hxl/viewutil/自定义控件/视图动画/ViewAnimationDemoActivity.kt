package com.justcode.hxl.viewutil.自定义控件.视图动画

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.*
import com.justcode.hxl.viewutil.R
import kotlinx.android.synthetic.main.activity_view_animation_demo.*

class ViewAnimationDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_animation_demo)
        val handler = Handler()
        btn_demo1.setOnClickListener {
            val scaleAnimation = ScaleAnimation(
                1.0f, 1.5f, 1.0f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
            )
            scaleAnimation.fillAfter = true
            scaleAnimation.duration = 6000
            scaleAnimation.interpolator = BounceInterpolator()
            iv_img.startAnimation(scaleAnimation)
        }
        btn_demo2.setOnClickListener {
            val rotateAnimation = RotateAnimation(
                0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
            )
            rotateAnimation.duration = 2000
            rotateAnimation.interpolator = LinearInterpolator()
            rotateAnimation.repeatCount = Animation.INFINITE
            iv_loading.startAnimation(rotateAnimation)
        }
        val animation1 = AnimationUtils.loadAnimation(this, R.anim.scan_alpha_anim)
        val animation2 = AnimationUtils.loadAnimation(this, R.anim.scan_alpha_anim)
        val animation3 = AnimationUtils.loadAnimation(this, R.anim.scan_alpha_anim)
        val animation4 = AnimationUtils.loadAnimation(this, R.anim.scan_alpha_anim)
        val animation5 = AnimationUtils.loadAnimation(this, R.anim.scan_alpha_anim)
        val animation6 = AnimationUtils.loadAnimation(this, R.anim.scan_alpha_anim)
        val animation7 = AnimationUtils.loadAnimation(this, R.anim.scan_alpha_anim)
        val animation8 = AnimationUtils.loadAnimation(this, R.anim.scan_alpha_anim)
        val animation9 = AnimationUtils.loadAnimation(this, R.anim.scan_alpha_anim)
        btn_demo3.setOnClickListener {

            circle1.startAnimation(animation1)
            handler.postDelayed({
                circle2.startAnimation(animation2)
            }, 400)

            handler.postDelayed({
                circle3.startAnimation(animation3)
            }, 700)

            handler.postDelayed({
                circle4.startAnimation(animation4)
            }, 1000)

            handler.postDelayed({
                circle4.startAnimation(animation4)
            }, 1300)

            handler.postDelayed({
                circle5.startAnimation(animation5)
            }, 1600)

            handler.postDelayed({
                circle6.startAnimation(animation6)
            }, 1900)

            handler.postDelayed({
                circle7.startAnimation(animation7)
            }, 2200)

            handler.postDelayed({
                circle8.startAnimation(animation8)
            }, 2500)

            handler.postDelayed({
                circle9.startAnimation(animation9)
            }, 2800)


        }
    }
}
