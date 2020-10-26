package com.justcode.hxl.viewutil.rotate


import android.R.attr.centerX
import android.R.attr.centerY
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageView


@SuppressLint("AppCompatCustomView")
class RotateImage @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {

    var x1: Double = 0.0
    var y1 = 0.0
    var center_x = 0.0
    var center_y = 0.0
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d("RotateImage_", "x:${this.x} y:${this.y}")

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        Log.d("RotateImage_", "w:${w} h:${h}")
        center_x = w / 2.0
        center_y = h / 2.0
        Log.d("RotateImage_", "centerX:${centerX} centerY:${centerY}")
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                x1 = event.x.toDouble()
                y1 = event.y.toDouble()
                Log.d("RotateImage_", "ACTION_DOWN x:${event.x} y:${event.y}")

            }
            MotionEvent.ACTION_MOVE -> {

            }
            MotionEvent.ACTION_UP -> {

            }
        }
        return true
    }


    private fun getRotate(
        x1: Double,
        y1: Double,
        x2: Double,
        y2: Double
    ): Double {
        val abx = center_x - x1
        val aby = center_y - y1
        val acx = center_x - x2
        val acy = center_y - y2
        val bcx = x2 - x1
        val bcy = y2 - y1
        val c = Math.hypot(abx, aby)
        Log.e("TAG", "c == $c")
        val b = Math.hypot(acx, acy)
        Log.e("TAG", "b == $b")
        val a = Math.hypot(bcx, bcy)
        Log.e("TAG", "a == $a")
        var cos1 = (c * c + b * b - a * a) / (2 * b * c)
        Log.e("TAG", "cos == $cos1")
        if (cos1 >= 1) {
            cos1 = 1.0
        }
        val radian = Math.acos(cos1)
        val degree = Math.toDegrees(radian)

        return  degree
    }

}