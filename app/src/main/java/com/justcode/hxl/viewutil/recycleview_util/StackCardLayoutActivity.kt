package com.justcode.hxl.viewutil.recycleview_util

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.extend.toast
import com.justcode.hxl.viewutil.recycleview_util.core.BaseAdapter
import com.justcode.hxl.viewutil.recycleview_util.core.BaseViewHolder
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.stackcardlayoutmanager.StackCardLayoutManager
import kotlinx.android.synthetic.main.activity_stack_card_layout.*
import kotlinx.android.synthetic.main.item_stack_layout.view.*

class StackCardLayoutActivity : AppCompatActivity() {

    val list: MutableList<StackData> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stack_card_layout)
        initList()

        val adapter = StackCardAdapter(list)
        recycler_stack_layout.adapter = adapter
        recycler_stack_layout.layoutManager = StackCardLayoutManager(5)
        val itemDecor = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean = true.also { _ ->
                    val fromPos = viewHolder.adapterPosition
                    val toPos = target.adapterPosition
                    adapter.notifyItemMoved(fromPos, toPos)
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                }
            })
        itemDecor.attachToRecyclerView(recycler_stack_layout)

    }

    private fun initList() {
        list.add(
            StackData(
                R.drawable.ic_omg,
                R.drawable.omg_background,
                "omisego"
            )
        )
        list.add(
            StackData(
                R.drawable.ic_nem,
                R.drawable.nem_background,
                "NEM"
            )
        )
        list.add(
            StackData(
                R.drawable.ic_ethereum,
                R.drawable.ethereum_background,
                "Ethereum"
            )
        )
        list.add(
            StackData(
                R.drawable.ic_ripple,
                R.drawable.ripple_background,
                "Ripple"
            )
        )
        list.add(
            StackData(
                R.drawable.ic_bitcoin,
                R.drawable.bitcoin_background,
                "Bitcoin"
            )
        )
    }
}

data class StackData(
    var logo: Int,
    var background: Int,
    var title: String
)

class StackCardAdapter(var list: MutableList<StackData>) : BaseAdapter() {
    override var layoutId: Int
        get() = R.layout.item_stack_layout
        set(value) {}

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        with(holder.itemView) {
            setOnClickListener {
                "点击了第${position}".toast(context)
            }
            relative_layout.setBackgroundResource(list[position].background)
            title_text_view.text = list[position].title
            iv_log.setImageResource(list[position].logo)

        }
    }

}