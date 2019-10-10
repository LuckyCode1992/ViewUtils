package com.justcode.hxl.viewutil.recycleview_util

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.*
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.recycleview_util.core.MyViewHolder
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.flowdraglayoutmanager.FlowDragLayoutConstant
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.flowdraglayoutmanager.FlowDragLayoutManager
import kotlinx.android.synthetic.main.activity_flow_drag_layout_manager.*
import kotlinx.android.synthetic.main.item_assemble_tags.view.*

class FlowDragLayoutManagerActivity : AppCompatActivity() {
    var tagsData: MutableList<String> = ArrayList<String>()
    lateinit var layoutManager: FlowDragLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_drag_layout_manager)
        initList()

        layoutManager = FlowDragLayoutManager()
        recycler_view.layoutManager = layoutManager
        val adapter = FlowDragAdapter(this, tagsData)
        recycler_view.adapter = adapter

        //拖拽
        val touchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
                val dragFlags = (ItemTouchHelper.UP or ItemTouchHelper.DOWN
                        or ItemTouchHelper.START or ItemTouchHelper.END)
                return ItemTouchHelper.Callback.makeFlag(ItemTouchHelper.ACTION_STATE_DRAG, dragFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val animStartString = tagsData.removeAt(viewHolder.adapterPosition)
                tagsData.add(target.adapterPosition, animStartString)
                adapter.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            }


        })
        touchHelper.attachToRecyclerView(recycler_view)


    }

    private fun initList() {
        tagsData.add("RxJava")
        tagsData.add("JavaScript")
        tagsData.add("PHP")
        tagsData.add("Python")
        tagsData.add("黑客")
        tagsData.add("作家")
        tagsData.add("创业肖邦")
        tagsData.add("世界末日")
        tagsData.add("流感病毒")
        tagsData.add("爸爸去哪儿")
        tagsData.add("钓鱼岛，我们的")
        tagsData.add("Github")
        tagsData.add("打飞机")
        tagsData.add("华尔街之狼")
        tagsData.add("黑暗骑士")
        tagsData.add("你的名字")
        tagsData.add("惊天魔盗团")
        tagsData.add("希拉里落选")
        tagsData.add("热门标签")
        tagsData.add("ImageView")
        tagsData.add("wheel无限循环")
        tagsData.add("ViewPager")
        tagsData.add("数据存储")
        tagsData.add("上拉加载")
        tagsData.add("dialog")
        tagsData.add("滑动浏览")
        tagsData.add("下载")
        tagsData.add("神奇动物在哪里")
        tagsData.add("video")
        tagsData.add("垂直ViewPager")
        tagsData.add("控件")
        tagsData.add("加载更多")
        tagsData.add("Retrofit")
        tagsData.add("肖生克的救赎")
        tagsData.add("工具")
        tagsData.add("加载动画")
        tagsData.add("EditText")
        tagsData.add("电锯惊魂")
        tagsData.add("狙击电话亭")
        tagsData.add("这个杀手不太冷")
        tagsData.add("阿甘正传")
        tagsData.add("十二怒汉")
        tagsData.add("海上钢琴师")
        tagsData.add("搏击俱乐部")
        tagsData.add("忠犬八公的故事")
        tagsData.add("卡比利亚之夜")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.getItemId()) {
            R.id.menu_align_left -> layoutManager.setAlignMode(FlowDragLayoutConstant.LEFT)
            R.id.menu_align_center -> layoutManager.setAlignMode(FlowDragLayoutConstant.CENTER)
            R.id.menu_align_right -> layoutManager.setAlignMode(FlowDragLayoutConstant.RIGHT)
            R.id.menu_align_two_side -> layoutManager.setAlignMode(FlowDragLayoutConstant.TWO_SIDE)
        }
        return true
    }
}

class FlowDragAdapter(var context: Context, var list: MutableList<String>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_assemble_tags,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder.itemView) {
            tv_word.text = list[position]
        }
    }

}