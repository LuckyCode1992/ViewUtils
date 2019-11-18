package com.justcode.hxl.viewutil.自定义控件.第三章_属性动画

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.extend.toast
import kotlinx.android.synthetic.main.activity_animator_path.*


class AnimatorPathActivity : AppCompatActivity() {

    var isMenuOpen = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator_path)
        /**
         *  数学原理 三角函数 x = r * sin(a)   y = r*cos(a)
         *  在java中 sin 和cos 方法 需要传入的弧度，
         *  java中有一个Math.toRadians(double a) 方法，可以将角度转为弧度  也可以 Math.PI = 180度的公式，自己运算
         */

        menu.setOnClickListener {
            if (!isMenuOpen) {
                isMenuOpen = true
                openMenu()
            } else {
                isMenuOpen = false
                closeMenu()
            }
        }
        item1.setOnClickListener {
            "item1".toast(this)
        }
        item2.setOnClickListener {
            "item2".toast(this)
        }
        item3.setOnClickListener {
            "item3".toast(this)
        }
        item4.setOnClickListener {
            "item4".toast(this)
        }
        item5.setOnClickListener {
            "item5".toast(this)
        }
    }

    private fun closeMenu() {
        doAnimatorClose(item1, 0, 5, 300.0)
        doAnimatorClose(item2, 1, 5, 300.0)
        doAnimatorClose(item3, 2, 5, 300.0)
        doAnimatorClose(item4, 3, 5, 300.0)
        doAnimatorClose(item5, 4, 5, 300.0)
    }

    private fun openMenu() {
        doAnimateOpen(item1, 0, 5, 300.0)
        doAnimateOpen(item2, 1, 5, 300.0)
        doAnimateOpen(item3, 2, 5, 300.0)
        doAnimateOpen(item4, 3, 5, 300.0)
        doAnimateOpen(item5, 4, 5, 300.0)
    }

    fun doAnimateOpen(view: View, index: Int, total: Int, radius: Double) {
        if (view.visibility != View.VISIBLE) {
            view.visibility = View.VISIBLE
        }
        // 将90度的弧平均分
        val radian = Math.toRadians(90.0) / (total - 1)
        //当前item的弧度
        val currentRadian = radian * index
        //向左平移的距离
        val transalateX: Float = -(radius * Math.sin(currentRadian)).toFloat()
        //向上平移的距离
        val translateY: Float = -(radius * Math.cos(currentRadian)).toFloat()
        val animatorSet = AnimatorSet()
        //下面是效果 平移，缩放，透明度
        //动画同时进行，所有用 playTogether
//        view.translationX
//        view.translationY
//        view.scaleX
//        view.scaleY
//        view.alpha
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "translationX", 0f, transalateX),
            ObjectAnimator.ofFloat(view, "translationY", 0f, translateY),
            ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
            ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
            ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        )
        animatorSet.duration = 500
        animatorSet.start()
    }

    fun doAnimatorClose(view: View, index: Int, total: Int, radius: Double) {
        // 将90度的弧平均分
        val radian = Math.toRadians(90.0) / (total - 1)
        //当前item的弧度
        val currentRadian = radian * index
        //向左平移的距离
        val transalateX: Float = -(radius * Math.sin(currentRadian)).toFloat()
        //向上平移的距离
        val translateY: Float = -(radius * Math.cos(currentRadian)).toFloat()
        val animatorSet = AnimatorSet()
        //下面是效果 平移，缩放，透明度
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "translationX", transalateX, 0f),
            ObjectAnimator.ofFloat(view, "translationY", translateY, 0f),
            ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.0001f),
            ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.0001f),
            ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        )
        animatorSet.duration = 500
        animatorSet.start()

        /**
         * 特别说明 scale 函数做动画时，控件缩小到0，属性动画将不改变控件的位置，就像视图动画那样
         * 所以，可以如下处理，1。scale不缩放到0   2. 添加动画监听，在结束的时候，重新设置scale
         */
    }

}
