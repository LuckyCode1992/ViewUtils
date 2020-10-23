package com.justcode.hxl.viewutil.rotate


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.Toast

@SuppressLint("AppCompatCustomView")
class RotateImage @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {


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
        val centerX = left + w/2
        val centerY = top + h/2
        Log.d("RotateImage_", "centerX:${centerX} centerY:${centerY}")
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d("RotateImage_", "x:${event.x} y:${event.y}")
                Log.d("RotateImage_", "rawX:${event.rawX} rawY:${event.rawY}")

            }
            MotionEvent.ACTION_MOVE -> {

            }
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return super.onTouchEvent(event)
    }
}