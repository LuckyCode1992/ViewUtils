package com.justcode.hxl.viewutil.自定义控件.属性动画

import android.animation.Animator
import android.animation.ValueAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.extend.toast
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.util.log.Log
import kotlinx.android.synthetic.main.activity_value_animator.*

class ValueAnimatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_value_animator)

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
    }

    private fun tantiaoDemo() {


    }

    private fun listenerDemo() {

        val animator = ValueAnimator.ofInt(0, 400)
        animator.repeatMode = ValueAnimator.REVERSE
        animator.repeatCount = ValueAnimator.INFINITE
        animator.duration = 1000
        animator.start()

        btn_set_count.setOnClickListener {
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
