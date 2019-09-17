package com.justcode.hxl.viewutil.recycleview_util

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.bumptech.glide.Glide
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.extend.toast
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.ChipsLayoutManager
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.SpacingItemDecoration
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.chipslayoutmanager.util.CircleTransform
import com.justcode.hxl.viewutil.recycleview_util.core.BaseItemViewBinder
import com.justcode.hxl.viewutil.recycleview_util.core.MultiTypeAdapter
import com.justcode.hxl.viewutil.recycleview_util.core.MyViewHolder
import kotlinx.android.synthetic.main.activity_chips_layout_manager.*
import kotlinx.android.synthetic.main.layout_item_chips.view.*
import java.util.*
import kotlin.collections.ArrayList

class ChipsLayoutManagerActivity : AppCompatActivity() {

    val adapter = MultiTypeAdapter()
    val list: MutableList<ChipsEntity> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chips_layout_manager)


        val chipsLayoutManager = ChipsLayoutManager.newBuilder(this)
            .setOrientation(ChipsLayoutManager.VERTICAL)
            .build()

        recycle_chips.addItemDecoration(
            SpacingItemDecoration(20, 20)
        )

        val chipsItemBinder = ChipsItemBinder()
        chipsItemBinder.itemPositionCallBack = {
            if (list.size >= it) {
                list.removeAt(it)
                adapter.items = list
                adapter.notifyItems()
            }

        }
        adapter.register(chipsItemBinder)
        recycle_chips.layoutManager = chipsLayoutManager
        recycle_chips.adapter = adapter

        initList()
        adapter.items = list
        adapter.notifyItems()


        btn_insert.setOnClickListener {
            list.add(0, list[Random().nextInt(list.size - 1)])
            adapter.items = list
            adapter.notifyItems()
        }
        btn_to_top.setOnClickListener {
            recycle_chips.scrollToPosition(0)
        }

    }

    fun initList() {

        list.add(ChipsEntity("Batman", "", R.drawable.batman))
        list.add(ChipsEntity("V", "", null))
        list.add(ChipsEntity("Jayne", "Everyone want to meet Jayne", R.drawable.girl2))
        list.add(ChipsEntity("Cat", "", R.drawable.girl3))
        list.add(ChipsEntity("J", "", null))
        list.add(ChipsEntity("A", "", null))
        list.add(ChipsEntity("Second Batman", "Batman is our friend", R.drawable.girl5))
        list.add(ChipsEntity("C", "", null))
        list.add(ChipsEntity("Batman", "", R.drawable.batman))
        list.add(ChipsEntity("Karl", "", R.drawable.karl))
        list.add(ChipsEntity("Very Long Name Anonymous", "", R.drawable.anonymous))

        list.add(ChipsEntity("Batman", "", R.drawable.batman))
        list.add(ChipsEntity("V", "", null))
        list.add(ChipsEntity("Jayne", "Everyone want to meet Jayne", R.drawable.girl2))
        list.add(ChipsEntity("Cat", "", R.drawable.girl3))
        list.add(ChipsEntity("J", "", null))
        list.add(ChipsEntity("A", "", null))
        list.add(ChipsEntity("Second Batman", "Batman is our friend", R.drawable.girl5))
        list.add(ChipsEntity("C", "", null))
        list.add(ChipsEntity("Batman", "", R.drawable.batman))
        list.add(ChipsEntity("Karl", "", R.drawable.karl))
        list.add(ChipsEntity("Very Long Name Anonymous", "", R.drawable.anonymous))

    }
}

data class ChipsEntity(var name: String? = null, var description: String? = null, var imgId: Int? = null)

class ChipsItemBinder : BaseItemViewBinder<ChipsEntity>() {
    override val layoutRes: Int
        get() = R.layout.layout_item_chips

    override fun onBindViewHolder(holder: MyViewHolder, item: ChipsEntity) {
        with(holder.itemView) {
            item.imgId?.let {
                iv_icon.visibility = View.VISIBLE
                Glide.with(context).load(it)
                    .transform(
                        CircleTransform(
                            context
                        )
                    ).into(iv_icon)
            } ?: kotlin.run {
                iv_icon.visibility = View.GONE
            }
            item.name?.let { tv_name.text = it }
            if (TextUtils.isEmpty(item.description)) {
                tv_description.visibility = View.GONE
            } else {
                tv_description.visibility = View.VISIBLE
                tv_description.text = item.description
            }
            tv_position.text = holder.adapterPosition.toString()

            setOnClickListener {
                "${holder.adapterPosition}-${item}".toast(context)
            }
            iv_delete.setOnClickListener {
                itemPositionCallBack.invoke(holder.adapterPosition)
            }

        }

    }

}