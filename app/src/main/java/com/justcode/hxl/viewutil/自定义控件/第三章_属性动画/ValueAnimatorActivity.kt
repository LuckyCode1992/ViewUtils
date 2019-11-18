package com.justcode.hxl.viewutil.自定义控件.第三章_属性动画

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.AttributeSet
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Interpolator
import android.widget.ImageView
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.extend.start
import com.justcode.hxl.viewutil.extend.toast
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.util.log.Log
import kotlinx.android.synthetic.main.activity_value_animator.*

class ValueAnimatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_value_animator)

        /**
         * 属性动画，除了可以如下使用 代码实现，还可以使用xml 实现，就像视图动画那样，但是，平时习惯，更喜欢代码。故不作demo了
         */

        btn_demo1.setOnClickListener {
            //解释一下这个属性动画
            // 这里ValueAnimator.ofInt(0, 300)是为了配合addUpdateListener使用。这里设置一个数值，从0到300，
            //在回调中，就是不断的回调这个数值，然后，再根据这个数值动态的改变你想要改变的值
            // 就如下面这个，是不断的改变tv的在位置（layout（）方法）
            val animator = ValueAnimator.ofInt(0, 300)
            animator.duration = 1000
            animator.addUpdateListener {
                val curValue: Int = it.animatedValue as Int
                Log.d("value_animator_", "curValue：${curValue}")
                tv_1.layout(curValue, curValue, curValue + tv_1.width, curValue + tv_1.height)
            }
            animator.start()
        }

        ofFloatDemo()
        listenerDemo()
        tantiaoDemo()

        btn_my_interpolator.setOnClickListener {
            val animator = ValueAnimator.ofInt(0, 300)
            animator.duration = 1000
            animator.addUpdateListener {
                val curValue: Int = it.animatedValue as Int
                tv_my_interpolator.layout(
                    tv_my_interpolator.left,
                    curValue,
                    tv_my_interpolator.right,
                    curValue + tv_my_interpolator.height
                )
            }
            animator.interpolator = MyInterpolator()
            animator.start()
        }
        btn_my_evaluator.setOnClickListener {
            val animator = ValueAnimator.ofInt(0, 300)
            animator.duration = 1000
            animator.addUpdateListener {
                val curValue: Int = it.animatedValue as Int
                tv_my_evaluator.layout(
                    tv_my_evaluator.left,
                    curValue,
                    tv_my_evaluator.right,
                    curValue + tv_my_evaluator.height
                )
            }
            animator.setEvaluator(ReverseEvaluator())
            animator.start()
        }

        btn_argb_evaluator.setOnClickListener {
            val animator = ValueAnimator.ofInt(0xffffff00.toInt(), 0xff0000ff.toInt())
            animator.setEvaluator(ArgbEvaluator())
            animator.duration = 3000
            animator.addUpdateListener {
                val curValue: Int = it.animatedValue as Int
                tv_argb_evaluator.setBackgroundColor(curValue)
            }
            animator.start()
        }

        rl_of_object.setOnClickListener {
            start<OfOjectActivity>()
        }
        rl_object_animator.setOnClickListener {
            start<ObjectAnimatorActivity>()
        }
        rl_animator_set.setOnClickListener {
            start<AnimatorSetActivity>()
        }
        rl_animator_path.setOnClickListener {
            start<AnimatorPathActivity>()
        }
    }

    private fun tantiaoDemo() {
        var currentIndex = 0
        val imgCount = 3

        val valueAnimator = ValueAnimator.ofInt(0, 100, 0)
        valueAnimator.repeatMode = ValueAnimator.RESTART
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        valueAnimator.duration = 2000
        valueAnimator.interpolator = AccelerateDecelerateInterpolator()
        valueAnimator.addUpdateListener {
            val dx: Int = it.getAnimatedValue() as Int
            iv_loading_tantiao.top = iv_loading_tantiao.mTop - dx
        }
        valueAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                currentIndex++
                when (currentIndex % imgCount) {
                    0 -> {
                        iv_loading_tantiao.setImageResource(R.drawable.pic_1)
                    }
                    1 -> {
                        iv_loading_tantiao.setImageResource(R.drawable.pic_2)
                    }
                    2 -> {
                        iv_loading_tantiao.setImageResource(R.drawable.pic_3)
                    }
                }

            }

            override fun onAnimationEnd(animation: Animator?) {

            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {
                iv_loading_tantiao.setImageResource(R.drawable.pic_1)
            }

        })

        btn_loading_tantiao.setOnClickListener {
            valueAnimator.start()
        }

    }

    private fun listenerDemo() {

        val animator = ValueAnimator.ofInt(0, 400)
        animator.repeatMode = ValueAnimator.REVERSE
        animator.repeatCount = ValueAnimator.INFINITE
        animator.duration = 1000
        animator.start()

        btn_set_count.setOnClickListener {
            val input = et_input.text.toString()
            if (TextUtils.isEmpty(input)) return@setOnClickListener
            val count: Int = et_input.text.toString().toInt()
            animator.repeatCount = count
            animator.start()
        }

        btn_add_listener.setOnClickListener {
            animator.addUpdateListener {
                val curValue: Int = it.animatedValue as Int
                tv_listener.layout(curValue, curValue, curValue + tv_1.width, curValue + tv_1.height)
            }
            animator.addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator) {
                    Log.d("value_animator_", "onAnimationRepeat")
                }

                override fun onAnimationEnd(animation: Animator) {
                    Log.d("value_animator_", "onAnimationEnd")
                }

                override fun onAnimationCancel(animation: Animator) {
                    Log.d("value_animator_", "onAnimationCancel")
                }

                override fun onAnimationStart(animation: Animator) {
                    Log.d("value_animator_", "onAnimationStart")
                }

            })
        }
        btn_remove_listener.setOnClickListener {
            animator.removeAllListeners()
            animator.removeAllUpdateListeners()
        }

    }

    private fun ofFloatDemo() {

        val animator = ValueAnimator.ofFloat(0f, 400f, 50f, 300f)
        animator.duration = 3000
        //注意一点， 如果选择无限循环，在销毁activity时记得cancel 否则可能会内存泄漏
        animator.repeatCount = ValueAnimator.INFINITE //循环次数  -1表示无限
        animator.repeatMode = ValueAnimator.REVERSE // REVERSE表示倒放 RESTART表示重新放
        animator.addUpdateListener {
            val curValue: Float = it.animatedValue as Float
            tv_float.layout(
                curValue.toInt(), curValue.toInt(), (curValue + tv_float.width).toInt(),
                (curValue + tv_float.height).toInt()
            )
        }
        //即使是动画中也能响应点击事件
        tv_float.setOnClickListener {

            "点击了".toast(this)
        }
        btn_of_float_start.setOnClickListener {
            animator.start()
        }
        btn_of_float_cancel.setOnClickListener {
            animator.cancel()
        }

    }
}

class LoadingImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {
    var mTop = 0
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mTop = top

    }

}

/**
 * 自定义插值器
 *
 * 插值器接口，总共只有一个方法需要实现 getInterpolation
 * input 参数，是float，取值范围0-1  表示当前动画的进度。而返回值表示实际显示的进度，返回值可以小于0 ，也可以大于1。
 * 所以，插值器的关键就是这个返回值和这个input之间的函数关系。
 * 比如 返回值 = input 则表示匀速
 *
 */

class MyInterpolator : Interpolator {
    override fun getInterpolation(input: Float): Float {


        return input * input * input
    }

}

/**
 *  自定义 估值器（Evaluator）
 *  Evaluator 是另外一种改变动画的东西
 *  fraction ： 参数是插值器的返回值，表示当前动画的数值进度，小数
 *  startValue和endValue：对应animator.ofInt(start,end)
 *  返回值 就是当前进度所对应的具体数值，这个值就是addUpdateListener回掉中 it.animatedValue
 *
 *  下面就解释了，Evaluator在动画播放中作用。
 *
 *  假如 动画为 animator.ofInt(0,300)，  return (startValue + (endValue - startValue) * fraction).toInt() 则是和原来一样
 *
 *  return (200+startValue + (endValue - startValue) * fraction).toInt() 则相当于animator.ofInt(200,500)
 *
 *
 */
class IntEvaluator : TypeEvaluator<Int> {
    override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
        return (startValue + (endValue - startValue) * fraction).toInt()
    }

}

class ReverseEvaluator : TypeEvaluator<Int> {
    override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
        return (endValue - fraction * (endValue - startValue)).toInt()
    }

}
