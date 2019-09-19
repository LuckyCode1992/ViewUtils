package com.justcode.hxl.viewutil.自定义控件.属性动画

import android.animation.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
            //设置动画控件目标，一旦设置，其他动画中设置无效
//            animatorSet.setTarget(tv_1_together)
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
            animatior.interpolator = AccelerateInterpolator() as TimeInterpolator
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


        btn_play_builder.setOnClickListener {
            val animatior = ValueAnimator.ofObject(CharEvaluator(), '0', 'z')
            animatior.addUpdateListener {
                val char: Char = it.animatedValue as Char
                tv_builder.text = char.toString()
            }
            animatior.duration = 3000
            val tvBgAnimator = ObjectAnimator.ofInt(
                tv_builder,
                "BackgroundColor",
                0xffff00ff.toInt(),
                0xffffff00.toInt(),
                0xffff00ff.toInt()
            )
            tvBgAnimator.duration = 2000

            val tvTranslateX = ObjectAnimator.ofFloat(tv_builder, "translationX", 0f, 400f, 0f)
            tvTranslateX.duration = 4000

            val rotationZ = ObjectAnimator.ofFloat(tv_builder, "rotation", 0f, 180f, 0f)
            rotationZ.duration = 3000


            val animatorSet = AnimatorSet()

            // addListener 是监听 animatorSet的状态，而animatorSet 没有 循环的说法，所以，永远不会走onAnimationRepeat
            animatorSet.addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                    Log.d("animatorSet__", "onAnimationRepeat")
                }

                override fun onAnimationEnd(animation: Animator?) {
                    Log.d("animatorSet__", "onAnimationEnd")
                }

                override fun onAnimationCancel(animation: Animator?) {
                    Log.d("animatorSet__", "onAnimationCancel")
                }

                override fun onAnimationStart(animation: Animator?) {
                    Log.d("animatorSet__", "onAnimationStart")
                }

            })

            //这里有一点需要注意， play 的是主体，如果 play 的动画是无限循环或者play之前动画是无限循环
            // 在该动画之后执行的动画都不会执行，但是其他的可以无限循环，后面依然会执行
            //也就是说，只有 with 和 before 是 无限循环，动画才能执行后续 串行的动画
//            tvTranslateX.repeatCount = ValueAnimator.INFINITE
            tvBgAnimator.repeatCount = ValueAnimator.INFINITE
//            animatior.repeatCount = ValueAnimator.INFINITE
            rotationZ.repeatCount = ValueAnimator.INFINITE

            //可以统一设置 插值器，并且一旦设置了，其他动画设置就无效了
            animatorSet.interpolator = AccelerateInterpolator()


            //下面这段代码，解释一下： 播放tvTranslateX 同时 播放tvBgAnimator 在 animatior 之后播放 在rotationZ 之前播放 延时 5000ms播放
            //转换为人话就是 ： 先播放 animatior 然后播放tvTranslateX和播放tvBgAnimator，最后播放rotationZ
            // 延时5000ms，是从start开始，而本例中，因为animatior 是3秒，所以，在现象中，是animatior完毕后 延时2秒
            animatorSet
                .play(tvTranslateX)
                .with(tvBgAnimator)
                .after(animatior)
                .before(rotationZ)
                .after(5000)


            animatorSet.start()

        }

    }
}
