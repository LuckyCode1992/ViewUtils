package com.justcode.hxl.viewutil.自定义控件.属性动画

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import com.justcode.hxl.viewutil.R
import kotlinx.android.synthetic.main.activity_animator_set.*


class AnimatorSetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator_set)

        btn_play_sequentially.setOnClickListener {
            //            tv_1_sequentially.setBackgroundColor()
//            tv_1_sequentially.translationX
            val tv1BgAnimator = ObjectAnimator.ofInt(
                tv_1_sequentially,
                "BackgroundColor",
                0xffff00ff.toInt(),
                0xffffff00.toInt(),
                0xffff00ff.toInt()
            )
            val tv1TranslateX = ObjectAnimator.ofFloat(tv_1_sequentially, "translationX", 0f, 400f, 0f)
            val tv2TranslateX = ObjectAnimator.ofFloat(tv_2_sequentially, "translationX", 0f, 300f, 20f)
            val animatorSet = AnimatorSet()
            animatorSet.playSequentially(tv1BgAnimator, tv1TranslateX, tv2TranslateX)
            animatorSet.duration = 2000
            animatorSet.start()
        }
        btn_play_together.setOnClickListener {
            val tv1BgAnimator = ObjectAnimator.ofInt(
                tv_1_together,
                "BackgroundColor",
                0xffff00ff.toInt(),
                0xffffff00.toInt(),
                0xffff00ff.toInt()
            )
            val tv1TranslateX = ObjectAnimator.ofFloat(tv_1_together, "translationX", 0f, 400f, 0f)
            val tv2TranslateX = ObjectAnimator.ofFloat(tv_2_together, "translationX", 0f, 300f, 20f)
            val animatorSet = AnimatorSet()
            animatorSet.playTogether(tv1BgAnimator, tv1TranslateX, tv2TranslateX)
            animatorSet.duration = 2000
            animatorSet.start()
        }
        /**
         * 总结一下：
         * playSequentially 是动画按照顺序执行，第一个动画执行完毕后，第二个动画才能开始。
         * playSequentially 只负责执行顺序，不会管动画如何执行，执行次数，是否延迟等。举个例子，第一个动画无限循环，那么第二个动画永远不会执行。
         *
         * playTogether 是动画同时执行 这个”执行“并不代表动画开始，只是表示可以执行。举个例子 A动画直接执行，B动画延时执行。那么现象会是，先看到A动了
         * 2秒后看到B动了。
         *
         */

        btn_play_set.setOnClickListener {

            //            tv_set.text
            val animatior = ValueAnimator.ofObject(CharEvaluator(), '0', 'z')
            animatior.addUpdateListener {
                val char: Char = it.animatedValue as Char
                tv_set.text = char.toString()
            }
            animatior.interpolator = AccelerateInterpolator()
            animatior.repeatCount = ValueAnimator.INFINITE
            animatior.repeatMode = ValueAnimator.REVERSE
            val tvBgAnimator = ObjectAnimator.ofInt(
                tv_set,
                "BackgroundColor",
                0xffff00ff.toInt(),
                0xffffff00.toInt(),
                0xffff00ff.toInt()
            )
            tvBgAnimator.repeatCount = ValueAnimator.INFINITE
            tvBgAnimator.repeatMode = ValueAnimator.RESTART
            val tvTranslateX = ObjectAnimator.ofFloat(tv_set, "translationX", 0f, 400f, 0f)
            tvTranslateX.repeatMode = ValueAnimator.RESTART
            tvTranslateX.repeatCount = ValueAnimator.INFINITE
            val animatorSet = AnimatorSet()
            animatorSet.duration = 3000
            animatorSet.playTogether(animatior, tvBgAnimator, tvTranslateX)
            animatorSet.start()
        }

    }
}
