package com.justcode.hxl.viewutil.自定义控件.属性动画进阶

import android.animation.Animator
import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.justcode.hxl.viewutil.R
import kotlinx.android.synthetic.main.activity_view_property_animator.*

class ViewPropertyAnimatorActivity : AppCompatActivity() {

    /**
     * ViewPropertyAnimator 简化了ObjectAnimator
     *  1.view.animate()作为起始
     *  2.并不需要 先后i调用 start()方法，会自动隐式调用
     *  3.流畅（fluent） 拥有一个流畅的急口(fluent interface) ,允许将多个函数调用自然的串在一起，并把一个多属性的动画写成一行代码
     *
     *  对应 api 就是view的属性
     */


    var i = 0
    var j = 0

    @SuppressLint("ObjectAnimatorBinding")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_property_animator)

        btn_1.setOnClickListener {
            //变为 原来的1.5倍
            tv_1_1.animate().scaleY(1.5f)
            //增加1.5倍
            tv_1_2.animate().scaleYBy(1.5f)
        }
        /**
         * 设置监听
         */
        btn_1.animate().scaleX(1.2f).scaleY(1.2f).setListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {

            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })

        /**
         * 从性能来说，ViewPropertyAnimator 是根据预设的每一个动画帧计算出对应的所有属性值，并设置给控件
         * 然后调用一次 invalidate()方法进行绘制
         * ObjectAnimator 是使用反射或者jni，并且是对控件每个属性单独计算，绘制。
         *
         * 在 使用组合动画，更简单。
         *
         * 但，其实，性能上的差异很小，可以忽略，使用ViewPropertyAnimator 只是更简洁的编写代码
         */


        /**
         * 为ViewGroup内的组件添加动画
         * ViewAnimator ObeAnimator ,AnimatorSet 都是 针对一个控件做动画
         * 现在需要给viewGroup内的控件添加动画，比如给listview中的item添加动画
         *
         * android 为此 提供了4种方法
         */

        /**
         * layoutAnimation标签和LayoutAnimationController
         * 针对listview添加入场动画所使用的。
         * LayoutAnimationController 是他的代码实现。
         */

        /**
         * gridLayoutAnimation标签和GridLayoutAnimationController
         * 针对 gridview 添加入场动画所使用的
         * GridLayoutAnimationController 是他的代码实现。
         */

        /**
         *  android:animateLayoutChanges 属性
         *  添加和移除其中的控件时自动添加动画
         *  有一个简单的属性 android:animateLayoutChanges="true/false"
         */

        /**
         *  LayoutTransition
         *  实现ViewGroup 动态添加和删除其中的控件时指定动画
         *
         *  此方法最为强大
         */

        /**
         * 由于 layoutAnimation和 gridLayoutAnimation 都有缺陷（新增数据无法添加动画），
         * 所以，直接学后面两种
         */


        // android:animateLayoutChanges
        btn_add.setOnClickListener {
            addBtuttonView()

        }
        btn_remove.setOnClickListener {
            removeButtonView()
        }

        // LayoutTransition

        /**
         *  LayoutTransition 使用方法
         *  1.创建实例
         *  2.创建动画并进行设置
         *  3.将LayoutTransition 设置到ViewGroup中
         *  LayoutTransition.DISAPPEARING:元素在容器中消失时的动画
         *  LayoutTransition.APPEARING:元素在容器中显示时的动画
         *
         */
        val transitioner = LayoutTransition()
        //出场动画
        val animOut = ObjectAnimator.ofFloat(null, "rotation", 0f, 90f, 0f)
        //入场动画
        val animIn = ObjectAnimator.ofFloat(null, "rotationY", 0f, 360f, 0f)

        transitioner.setAnimator(LayoutTransition.DISAPPEARING, animOut)
        transitioner.setAnimator(LayoutTransition.APPEARING, animIn)
        ll_layout_transition.layoutTransition = transitioner
        btn_transition_add_view.setOnClickListener {
            addTransitionView()

        }
        btn_transition_remove_view.setOnClickListener {
            removeTransitionView()
        }

    }


    fun addTransitionView() {
        j++
        val button = Button(this)
        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        button.layoutParams = params
        ll_layout_transition.addView(button)
    }

    fun removeTransitionView() {
        if (j > 0) {
            ll_layout_transition.removeViewAt(0)
            j--
        }
    }


    fun addBtuttonView() {
        i++
        val button1 = Button(this)
        val button2 = Button(this)
        button1.text = "button$i"
        button2.text = "button$i"

        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        button1.layoutParams = params
        button2.layoutParams = params

        ll_true.addView(button1)
        ll_false.addView(button2)
    }

    fun removeButtonView() {
        if (i > 0) {
            ll_true.removeViewAt(0)
            ll_false.removeViewAt(0)
            i--
        }

    }
}
