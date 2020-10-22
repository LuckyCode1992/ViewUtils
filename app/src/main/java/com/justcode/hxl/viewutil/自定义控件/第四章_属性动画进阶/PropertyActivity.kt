package com.justcode.hxl.viewutil.自定义控件.第四章_属性动画进阶

import android.animation.*
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.animation.AccelerateInterpolator
import android.widget.TextView
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.extend.start
import kotlinx.android.synthetic.main.activity_property.*

class PropertyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property)
        /**
         *  PropertyValuesHolder
         *  保存了动画过程中所需要操作的属性和值
         */
//        ObjectAnimator.ofPropertyValuesHolder()


        btn_1.setOnClickListener {

            val rotationHolder =
                PropertyValuesHolder.ofFloat(
                    "rotation",
                    60f,
                    -60f,
                    40f,
                    -40f,
                    -20f,
                    20f,
                    10f,
                    -10f,
                    0f
                )
            val alphaHolder = PropertyValuesHolder.ofFloat("alpha", 0.1f, 1.0f, 0.1f, 1.0f)
            val scaleXHolder = PropertyValuesHolder.ofFloat("scaleX", 0.5f, 1.0f, 0.5f, 1.0f)
            val scaleYHolder = PropertyValuesHolder.ofFloat("scaleY", 0.5f, 1.0f, 0.5f, 1.0f)

            val animator =
                ObjectAnimator.ofPropertyValuesHolder(
                    tv_1,
                    rotationHolder,
                    alphaHolder,
                    scaleXHolder,
                    scaleYHolder
                )
            animator.duration = 3000
            animator.start()

        }

        /**
         * 不知哪里不对
         */
        btn_2.setOnClickListener {
            val valueAnimator2 =
                ValueAnimator.ofObject(CharEvaluator(), 'A', 'Z')

            valueAnimator2.duration = 2000;   //设置时长
            valueAnimator2.addUpdateListener(
                ValueAnimator.AnimatorUpdateListener {
                    val char = it.animatedValue as Char
                    tv_2.text = char.toString();
                }
            );
            valueAnimator2.start() //开始
        }

        /**
         * keyframe
         * 关键帧
         * 控制动画速率变化
         *
         * fraction: 当前显示的进度 （插值器中 getInterpolation）
         * value: 动画当前所在的数值位置
         *
         */
        btn_3.setOnClickListener {
            val frame0 = Keyframe.ofFloat(0f, 0f)

            val frame1 = Keyframe.ofFloat(0.1f, -20f)

            val frame2 = Keyframe.ofFloat(1f, 0f)
            val frameHolder = PropertyValuesHolder.ofKeyframe("rotation", frame0, frame1, frame2)
            val animator = ObjectAnimator.ofPropertyValuesHolder(tv_3, frameHolder)
            animator.duration = 3000
            animator.start()

        }

        //响铃效果
        btn_4.setOnClickListener {
            val frame0 = Keyframe.ofFloat(0f, 0f)
            val frame1 = Keyframe.ofFloat(0.1f, -20f)
            val frame2 = Keyframe.ofFloat(0.2f, 20f)
            val frame3 = Keyframe.ofFloat(0.3f, -20f)
            val frame4 = Keyframe.ofFloat(0.4f, 20f)
            val frame5 = Keyframe.ofFloat(0.5f, -20f)
            val frame6 = Keyframe.ofFloat(0.6f, 20f)
            val frame7 = Keyframe.ofFloat(0.7f, -20f)
            val frame8 = Keyframe.ofFloat(0.8f, 20f)
            val frame9 = Keyframe.ofFloat(0.9f, -20f)
            val frame10 = Keyframe.ofFloat(1f, 0f)
            val frameHolder = PropertyValuesHolder.ofKeyframe(
                "rotation",
                frame0,
                frame1,
                frame2,
                frame3,
                frame4,
                frame5,
                frame6,
                frame7,
                frame8,
                frame9,
                frame10
            )
            val animator = ObjectAnimator.ofPropertyValuesHolder(iv_ring, frameHolder)
            animator.duration = 1000
            animator.start()
        }
        /**
         * Keyframe 可以使用插值器，和animator 使用方式一样
         *
         * Keyframe 注意事项
         *  1.keyFrame 构建动画，必须至少两帧
         *  2.keyFrame 动画从第一个关键帧开始
         *  3.keyFrame 动画从最后一个关键帧结束
         */

        //完整的响铃效果
        btn_5.setOnClickListener {
            val frame0 = Keyframe.ofFloat(0f, 0f)
            val frame1 = Keyframe.ofFloat(0.1f, -20f)
            val frame2 = Keyframe.ofFloat(0.2f, 20f)
            val frame3 = Keyframe.ofFloat(0.3f, -20f)
            val frame4 = Keyframe.ofFloat(0.4f, 20f)
            val frame5 = Keyframe.ofFloat(0.5f, -20f)
            val frame6 = Keyframe.ofFloat(0.6f, 20f)
            val frame7 = Keyframe.ofFloat(0.7f, -20f)
            val frame8 = Keyframe.ofFloat(0.8f, 20f)
            val frame9 = Keyframe.ofFloat(0.9f, -20f)
            val frame10 = Keyframe.ofFloat(1f, 0f)
            val frameHolder = PropertyValuesHolder.ofKeyframe(
                "rotation",
                frame0,
                frame1,
                frame2,
                frame3,
                frame4,
                frame5,
                frame6,
                frame7,
                frame8,
                frame9,
                frame10
            )
            val scaleXFrame0 = Keyframe.ofFloat(0f, 1f)
            val scaleXFrame1 = Keyframe.ofFloat(0.1f, 1.1f)
            val scaleXFrame9 = Keyframe.ofFloat(0.9f, 1.1f)
            val scaleXFrame10 = Keyframe.ofFloat(1f, 1f)

            val frameHolder2 =
                PropertyValuesHolder.ofKeyframe(
                    "scaleX",
                    scaleXFrame0,
                    scaleXFrame1,
                    scaleXFrame9,
                    scaleXFrame10
                )

            val scaleYFrame0 = Keyframe.ofFloat(0f, 1f)
            val scaleYFrame1 = Keyframe.ofFloat(0.1f, 1.1f)
            val scaleYFrame9 = Keyframe.ofFloat(0.9f, 1.1f)
            val scaleYFrame10 = Keyframe.ofFloat(1f, 1f)
            val frameHolder3 =
                PropertyValuesHolder.ofKeyframe(
                    "scaleY",
                    scaleYFrame0,
                    scaleYFrame1,
                    scaleYFrame9,
                    scaleYFrame10
                )

            val animator = ObjectAnimator.ofPropertyValuesHolder(
                iv_ring2,
                frameHolder,
                frameHolder2,
                frameHolder3
            )
            animator.duration = 1000
            animator.start()
        }

        btn_6.setOnClickListener {
            start<ViewPropertyAnimatorActivity>()
        }
    }
}


class CharEvaluator : TypeEvaluator<Char> {
    override fun evaluate(fraction: Float, startValue: Char, endValue: Char): Char {
        /**
         * 将字符从转为数字，  阿斯克码
         */
        val startInt: Int = startValue.toInt()
        val endInt: Int = endValue.toInt()
        val curInt: Int = (startInt + fraction * (endInt - startInt)).toInt()
        //重新将数字转为字符
        return curInt.toChar()
    }

}

class MyTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {


    public fun setCharText(char: Char) {
        text = char.toString()
    }
}
