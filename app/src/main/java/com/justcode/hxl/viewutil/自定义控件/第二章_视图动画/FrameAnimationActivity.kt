package com.justcode.hxl.viewutil.自定义控件.第二章_视图动画

import android.graphics.drawable.AnimationDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.justcode.hxl.viewutil.R
import kotlinx.android.synthetic.main.activity_frame_animation.*

class FrameAnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame_animation)
        val anim = iv_frame.background as AnimationDrawable

        val anim2 = AnimationDrawable()
        for (i in 1..14) {
            //获取 资源的文件的一种方式 name表示 资源名字， defType表示资源所在文件类型，defPackage 表示应用包名
            val id = resources.getIdentifier("list_icon_gif_playing${i}","drawable",packageName)
            val drawable = resources.getDrawable(id)
            anim2.addFrame(drawable,60)
        }
        anim2.isOneShot = false
        iv_frame2.setBackgroundDrawable(anim2)
        btn_frame.setOnClickListener {
            anim.start()
            anim2.start()

        }
        btn_frame_stop.setOnClickListener {
            anim.stop()
            anim2.stop()
        }

    }
}
