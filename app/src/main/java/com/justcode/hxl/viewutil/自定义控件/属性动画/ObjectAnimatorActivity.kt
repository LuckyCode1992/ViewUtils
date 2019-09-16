package com.justcode.hxl.viewutil.自定义控件.属性动画

import android.animation.ObjectAnimator
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.recycleview_util.chipslayoutmanager.util.log.Log
import kotlinx.android.synthetic.main.activity_object_animator.*

class ObjectAnimatorActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object_animator)
        /**
         *  objectAnimator 是valueAnimator的派生类
         *  因为valueAnimator 有一个缺点，就是只能对动画的数值进行计算，需要通过监听动画过程，这个相对补间动画就繁琐了
         *  所以，才出现了objectAnimator。
         *
         *   objectAnimator 也有几个主要函数， ofInt(),ofFloat()等
         */

        btn_alpha.setOnClickListener {
            /**
             *  Object target, String propertyName, float... values
             *  target：指定动画操作的控件
             *  propertyName: 控件改变属性，比如 透明度 alpha ，旋转 rotation等
             */
            val animator = ObjectAnimator.ofFloat(iv_alpha, "alpha", 1f, 0.1f)
            animator.duration = 2000
            animator.start()
        }
        val view = View(this)
        view.alpha = 0.5f
        view.rotation = 123f
        view.rotationX = 123f
        view.rotationY = 123f
        view.translationX = 123f
        view.translationY = 123f
        view.translationZ = 123f
        view.scaleX = 1.2f
        view.scaleY = 1.2f
        /**
         * 重点，propertyName这个属性，对应的其实，就是view所列出来的这些属性 、
         *
         * 我们总结出两点：
         *
         * 1.使用ObjectAnimator 来构造动画，propertyName属性，只能是view对应的set函数，如上面写的这些
         *
         * 2.propertyName属性对应的名称是上述这样的写法，简而言之，复制上述的属性名称，即可
         */
        btn_rotation.setOnClickListener {

            val rotationZ = ObjectAnimator.ofFloat(iv_rotation_z, "rotation", 0f, 180f, 0f)
            rotationZ.duration = 2000
            rotationZ.start()
            val rotationX = ObjectAnimator.ofFloat(iv_rotation_x, "rotationX", 0f, 270f, 0f)
            rotationX.duration = 2000
            rotationX.start()
            val rotationY = ObjectAnimator.ofFloat(iv_rotation_y, "rotationY", 0f, 180f, 0f)
            rotationY.duration = 2000
            rotationY.start()
        }


        val y = iv_translation_y.pivotY
        val y0 = iv_translation_x.pivotY
        btn_translation.setOnClickListener {
            val translationX = ObjectAnimator.ofFloat(
                iv_translation_x,
                "translationX",
                iv_translation_x.x.toFloat(),
                iv_translation_x.x + 200f,
                iv_translation_x.x.toFloat()
            )
            translationX.duration = 2000
            translationX.start()

            val translationY0 = ObjectAnimator.ofFloat(
                iv_translation_x,
                "translationY",
                y0,
                y0 + 200f,
                y0
            )
            translationY0.duration = 2000
            translationY0.start()

            val translationY = ObjectAnimator.ofFloat(
                iv_translation_y,
                "translationY",
                y,
                y + 200f,
                y
            )
            translationY.duration = 2000
            translationY.start()
        }
    }
}
