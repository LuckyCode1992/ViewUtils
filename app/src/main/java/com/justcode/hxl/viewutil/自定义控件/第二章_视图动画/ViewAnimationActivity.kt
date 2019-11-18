package com.justcode.hxl.viewutil.自定义控件.第二章_视图动画

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.*
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.extend.start
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


        /**
         *  fromXScale: 动画起始，X轴方向上的变化 1.0表示原样
        toXScale:动画结束时，X轴方向上的变化 2.0表示放大一倍

        duration:动画持续时间

        pivotX：X轴方向的起点
        pivotY：Y方向的起点
        三种表示： 50，50%，50%p ,
        50:原点 50px处开始
        50%:控件左上角+自己宽（高）的一半 的地方开始，其实就是控件的 （x,y）中点
        50%p:控件的左上角+父控件（宽）高的一半的地方开始，

        fillAfter:是否保持动画结束时的状态 true表示保持
        fillBefore:还原到初始状态 如果fillAfter存在切为true，则无效

        repeatCount:重复次数 (-1表示，无限次，一直播放)
        repeatMode：动画重复模式，类型 reverse表示 反向播放 restart 表示重新播放
        interpolator：插值器：控制动画的变化速率。比如匀速，加速，具体看demo效果
         */
        //这里的Animation.RELATIVE_TO_SELF 对应 xml中的 50%
        //Animation.ABSOLUTE 对应xml中的50px
        //Animation.RELATIVE_TO_PARENT 对应xml中的 50%p
        //其他均和xml中的属性一一对应
        val scaleAnimation = ScaleAnimation(
            1.0f,
            2.0f,
            1.0f,
            2.0f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        scaleAnimation.duration = 2000

//        scaleAnimation.fillBefore = true
        scaleAnimation.fillAfter = true
//        scaleAnimation.repeatMode = Animation.REVERSE
        scaleAnimation.repeatCount = -1
        //代码实现插值器
        scaleAnimation.interpolator = AccelerateDecelerateInterpolator()

        //调用方式也是一模一样
        //tv_set.startAnimation(scaleAnimation)
        val alphaAnimation = AlphaAnimation(
            1.0f, 0.1f
        )
        alphaAnimation.duration = 2000
        alphaAnimation.fillAfter = true
//        alphaAnimation.repeatMode = Animation.REVERSE
        alphaAnimation.repeatCount = -1

        val rotateAnimation = RotateAnimation(
            0f, 359f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
        )
        rotateAnimation.duration = 2000
        rotateAnimation.fillAfter = true
//        rotateAnimation.repeatMode = Animation.RESTART
        rotateAnimation.repeatCount = -1

        val translateAnimation = TranslateAnimation(
            Animation.RELATIVE_TO_SELF,
            0F,
            Animation.ABSOLUTE,
            300f,
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.ABSOLUTE,
            300f
        )
        translateAnimation.duration = 2000
        translateAnimation.fillAfter = true
//        translateAnimation.repeatMode = Animation.REVERSE
        translateAnimation.repeatCount = -1


//        取消动画
//        translateAnimation.cancel()
//        重置动画
//        translateAnimation.reset()


        btn_set_together.setOnClickListener {

            val animationSet = AnimationSet(true)

            /**
             * 注意，添加的顺序不同，效果可能会不同
             *
             */
            alphaAnimation.repeatCount = -1
            scaleAnimation.repeatCount = -1
            rotateAnimation.repeatCount = -1
            translateAnimation.repeatCount = -1

            animationSet.addAnimation(alphaAnimation)
            animationSet.addAnimation(scaleAnimation)
            animationSet.addAnimation(rotateAnimation)
            animationSet.addAnimation(translateAnimation)

            animationSet.repeatMode = Animation.REVERSE

            animationSet.start()

            tv_set_together.startAnimation(animationSet)

        }


        btn_order.setOnClickListener {
            alphaAnimation.repeatCount = 0
            scaleAnimation.repeatCount = 0
            rotateAnimation.repeatCount = 0
            translateAnimation.repeatCount = 0

            translateAnimation.setAnimationListener(object : Animation.AnimationListener {
                //动画重复
                override fun onAnimationRepeat(animation: Animation) {

                }

                //动画结束
                override fun onAnimationEnd(animation: Animation) {

                    tv_order.startAnimation(scaleAnimation)
                }

                //动画开始
                override fun onAnimationStart(animation: Animation) {

                }

            })
            scaleAnimation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {

                }

                override fun onAnimationEnd(animation: Animation?) {
                    tv_order.startAnimation(alphaAnimation)
                }

                override fun onAnimationStart(animation: Animation?) {

                }

            })
            alphaAnimation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {

                }

                override fun onAnimationEnd(animation: Animation?) {
                    tv_order.startAnimation(rotateAnimation)
                }

                override fun onAnimationStart(animation: Animation?) {

                }

            })
            rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {
                    Log.d("animation_listener", "onAnimationRepeat")
                }

                override fun onAnimationEnd(animation: Animation?) {
                    Log.d("animation_listener", "onAnimationEnd")
                }

                override fun onAnimationStart(animation: Animation?) {
                    Log.d("animation_listener", "onAnimationStart")
                }

            })
            tv_order.startAnimation(translateAnimation)
        }


        ll_interpolator.setOnClickListener {
            start<ViewAnimationInterpolatorActivity>()
        }
        ll_animation_demo.setOnClickListener {
            start<ViewAnimationDemoActivity>()
        }
        ll_frame.setOnClickListener {
            start<FrameAnimationActivity>()
        }
    }

    private fun getAnimation(anim: Int): Animation {
        val animation = AnimationUtils.loadAnimation(this, anim)
        return animation
    }
}
