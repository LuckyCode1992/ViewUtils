package com.justcode.hxl.viewutil.recycleview_util

import android.animation.Animator
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.extend.toast
import com.justcode.hxl.viewutil.recycleview_util.core.BaseItemViewBinder
import com.justcode.hxl.viewutil.recycleview_util.core.MultiTypeAdapter
import com.justcode.hxl.viewutil.recycleview_util.core.MyViewHolder
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.fanlayoutmanager.FanLayoutManager
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.fanlayoutmanager.FanLayoutManagerSettings
import kotlinx.android.synthetic.main.activity_fan_layout_manager.*
import kotlinx.android.synthetic.main.itembinder_fan.view.*

class FanLayoutManagerActivity : AppCompatActivity() {

    val adapter = MultiTypeAdapter()
    val list: MutableList<FanData> = arrayListOf(
        FanData(back = Color.CYAN),
        FanData(back = Color.RED),
        FanData(back = Color.GREEN),
        FanData(back = Color.BLUE),
        FanData(back = Color.DKGRAY),
        FanData(back = Color.GRAY),
        FanData(back = Color.LTGRAY),
        FanData(back = Color.MAGENTA),
        FanData(back = Color.YELLOW),
        FanData(back = Color.GREEN)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fan_layout_manager)


        val fanLayoutManagerSettings = FanLayoutManagerSettings
            .newBuilder(this)
            .withFanRadius(true)
            .withAngleItemBounce(5f)
            .withViewHeightDp(160f)
            .withViewWidthDp(120f)
            .build()
        val fanManger = FanLayoutManager(this, fanLayoutManagerSettings)
        recycle_fan.layoutManager = fanManger

        val itemViewBinder = FanItemBinder()
        itemViewBinder.itemPositionViewCallBack = { postition, view ->
            if (fanManger.selectedItemPosition != postition) {
                fanManger.switchItem(recycle_fan, postition)
            } else {
                fanManger.straightenSelectedItem(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {

                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        "点击了${postition}".toast(this@FanLayoutManagerActivity)
                    }

                    override fun onAnimationCancel(animation: Animator?) {

                    }

                    override fun onAnimationStart(animation: Animator?) {

                    }
                })
            }
        }

        adapter.register(itemViewBinder)
        recycle_fan.adapter = adapter
        adapter.items = list
        adapter.notifyItems()
    }
}

class FanItemBinder : BaseItemViewBinder<FanData>() {
    override val layoutRes: Int
        get() = R.layout.itembinder_fan

    override fun onBindViewHolder(holder: MyViewHolder, item: FanData) {
        with(holder.itemView) {
            card_layout.setBackgroundColor(item.back!!)
            setOnClickListener {
                itemPositionViewCallBack.invoke(holder.adapterPosition, this)
            }
        }
    }

}

data class FanData(
    var title: String? = "this is title",
    var content: String? = "this is content",
    var back: Int? = 0,
    var icon: Int? = 0
)