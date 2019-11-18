package com.justcode.hxl.viewutil.自定义控件.第三章_属性动画

import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import com.justcode.hxl.viewutil.R
import kotlinx.android.synthetic.main.activity_of_oject.*


class OfOjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_of_oject)
        btn_of_obj_char.setOnClickListener {
            val animatior = ValueAnimator.ofObject(CharEvaluator(), '0', 'z')
            animatior.addUpdateListener {
                val char: Char = it.animatedValue as Char
                tv_of_obj_char.text = char.toString()
            }
            animatior.duration = 10000
            animatior.interpolator = AccelerateInterpolator()
            animatior.start()
        }
        btn_of_obj_paowu.setOnClickListener {
            val animator = ValueAnimator.ofObject(FallingBallEvaluator(), Point(0, 0), Point(800, 500))
            animator.addUpdateListener {
                val point: Point = it.animatedValue as Point
                iv_of_obj_paowu.layout(
                    point.x,
                    point.y,
                    point.x + iv_of_obj_paowu.width,
                    point.y + iv_of_obj_paowu.height
                )
            }
            animator.duration = 3000
            animator.start()
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

/**
 * 抛物线 公式 y=ax^2+bx+c 这里 简化为 y = 2x*x
 */
class FallingBallEvaluator : TypeEvaluator<Point> {
    var point = Point()
    override fun evaluate(fraction: Float, startValue: Point, endValue: Point): Point {
        point.x =
            (startValue.x + fraction * (endValue.x - startValue.x)).toInt()

        point.y = (startValue.y + 2 * fraction * fraction * (endValue.y - startValue.y)).toInt()

        return point
    }

}
