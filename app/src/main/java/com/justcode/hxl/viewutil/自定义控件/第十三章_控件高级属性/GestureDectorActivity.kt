package com.justcode.hxl.viewutil.自定义控件.第十三章_控件高级属性

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.justcode.hxl.viewutil.R
import kotlinx.android.synthetic.main.activity_gesture_dector.*


class GestureDectorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gesture_dector)
        // 1.创建（构造）OnGestureListener 的实例
        val gestureListener = MyGestureListener()
        //2.创建 GestureDetector 实例
        val gestureDetector = GestureDetector(this, gestureListener)
        //3.在onTouch 中拦截
        //4.绑定控件
        tv_gesture.setOnTouchListener { v, event -> gestureDetector.onTouchEvent(event) }
        //下面两个必须加上才能看到效果
        tv_gesture.isFocusable = true
        tv_gesture.isLongClickable = true




    }
}


/**
 *  使用GestureDetector 基本步骤：
 *      - 1.创建（构造）OnGestureListener 的实例
 *      - 2.创建 GestureDetector 实例
 *      - 3.在onTouch 中拦截
 *      - 4.绑定控件
 */
class MyGestureListener : GestureDetector.OnGestureListener {

    /**
     *  触发顺序 onDown -> onShowPress -> onLongPress
     *
     *   单击一下非常快的 Touchup ，触发顺序：onDown -> onSingleTapUp -> onSingleTapConfirmed
     *
     *   单击一下稍微慢点的 Touchup，触发顺序：  onDown -> onShowPress -> onSingleTapUp -> onSingleTapConfirmed
     *
     *   滑屏 触发顺序： onDown -> onScroll ->onScroll.... -> onFling
     *
     *   拖动 触发顺序： onDown -> onScroll -> onScroll -> onFling
     */

    /**
     *  如果按下的时间超过瞬间，而且安歇的时候没有松开或者拖动的，该函数被触发
     */
    override fun onShowPress(e: MotionEvent?) {
        Log.d("gesture_", "onShowPress")
    }

    /**
     *  一次单独的轻击操作。
     */
    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        Log.d("gesture_", "onSingleTapUp")
        return true
    }

    /**
     *  用户按下屏幕时出发
     */
    override fun onDown(e: MotionEvent?): Boolean {
        Log.d("gesture_", "onDown")
        return false
    }

    /**
     *  滑屏，用户按下触屏键，快速移动后松开。由一个DOWN 和多个MOVE，一个UP触发
     */
    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        Log.d("gesture_", "onFling")
        return true
    }

    /**
     *  在屏幕上拖动事件。
     */
    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        Log.d("gesture_", "onScroll")
        return true
    }

    /**
     *  长按触摸屏，超过一定时长，就会触发这个函数
     */
    override fun onLongPress(e: MotionEvent?) {
        Log.d("gesture_", "onLongPress")
    }


}


class MyDoubleGestureListener : GestureDetector.OnDoubleTapListener{
    override fun onDoubleTap(e: MotionEvent?): Boolean {
        Log.d("gesture_", "onDoubleTap")
        return false
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        Log.d("gesture_", "onDoubleTapEvent")
     return false
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        Log.d("gesture_", "onSingleTapConfirmed")
       return false
    }

}