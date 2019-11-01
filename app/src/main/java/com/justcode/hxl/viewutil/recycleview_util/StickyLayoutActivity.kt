package com.justcode.hxl.viewutil.recycleview_util

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.*
import android.widget.TextView
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.recycleview_util.core.BaseViewHolder
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.stickylayoutmanager.StickyHeaders
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.stickylayoutmanager.StickyHeadersGridLayoutManager
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.stickylayoutmanager.StickyHeadersLinearLayoutManager
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.stickylayoutmanager.StickyHeadersStaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_sticky_layout.*
import kotlinx.android.synthetic.main.sticky_item.view.*
import java.util.*

class StickyLayoutActivity : AppCompatActivity() {
    lateinit var adapter: StickAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sticky_layout)

        recycler_sticky.setHasFixedSize(true)
        adapter = StickAdapter()
        setLinearLayoutManager()
        recycler_sticky.adapter = adapter


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sticky_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu1 -> setLinearLayoutManager()
            R.id.menu2 -> setGridLayoutManager()
            R.id.menu3 -> setStaggeredGridLayoutManager()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setLinearLayoutManager() {
        val layoutManager = StickyHeadersLinearLayoutManager<StickAdapter>(this)
        recycler_sticky.layoutManager = layoutManager
    }

    private fun setGridLayoutManager() {
        val layoutManager = StickyHeadersGridLayoutManager<StickAdapter>(this, 3)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {

            override fun getSpanSize(position: Int): Int {
                return if (adapter.isStickyHeader(position)) {
                    3
                } else 1
            }
        }
        recycler_sticky.layoutManager = layoutManager
    }

    private fun setStaggeredGridLayoutManager() {
        val layoutManager =
            StickyHeadersStaggeredGridLayoutManager<StickAdapter>(3, StaggeredGridLayoutManager.VERTICAL)
        recycler_sticky.layoutManager = layoutManager
    }
}

class StickAdapter : RecyclerView.Adapter<BaseViewHolder>(), StickyHeaders, StickyHeaders.ViewSetup {

    private val DICT = arrayOf(
        "A",
        "B",
        "C",
        "D",
        "E",
        "F",
        "G",
        "H",
        "I",
        "J",
        "K",
        "L",
        "M",
        "N",
        "O",
        "P",
        "Q",
        "R",
        "S",
        "T",
        "U",
        "V",
        "W",
        "X",
        "Y",
        "Z",
        "a",
        "b",
        "c",
        "d",
        "e",
        "f",
        "g",
        "h",
        "i",
        "j",
        "k",
        "l",
        "m",
        "n",
        "o",
        "p",
        "q",
        "r",
        "s",
        "t",
        "u",
        "v",
        "w",
        "x",
        "y",
        "z"
    )
    private val HEADER_ITEM = 123
    var datas: MutableList<String> = ArrayList()

    init {
        for (i in 65 until 26 + 65) {
            datas.add(i.toChar().toString())
            for (j in 0..9) {
                val itemText = getItemText(i.toChar())
                datas.add(itemText)
            }
        }
    }

    private fun getItemText(prefix: Char): String {
        val length = createRandom(0, 10)
        val builder = StringBuilder()
        builder.append(prefix)
        for (i in 0 until length) {
            val random = createRandom(0, 51)
            builder.append(DICT[random])
        }
        return builder.toString()
    }

    private fun createRandom(min: Int, max: Int): Int {
        val random = Random()
        return random.nextInt(max) % (max - min + 1) + min
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 11 == 0) HEADER_ITEM else super.getItemViewType(position)
    }

    override fun setupStickyHeaderView(stickyHeader: View) {
        ViewCompat.setElevation(stickyHeader, 10f)
    }

    override fun teardownStickyHeaderView(stickyHeader: View) {
        ViewCompat.setElevation(stickyHeader, 0f)
    }

    override fun isStickyHeader(position: Int): Boolean {
        return getItemViewType(position) == HEADER_ITEM
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder) {
        super.onViewAttachedToWindow(holder)
        val lp = holder.itemView.layoutParams
        if (lp != null && lp is StaggeredGridLayoutManager.LayoutParams) {
            if (isStickyHeader(holder.layoutPosition)) {
                lp.isFullSpan = true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.sticky_item, parent, false)
        return BaseViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        with(holder.itemView) {
            tv_title_sticky.text = datas[position]
            tv_content_sticky.text = datas[position]
            if (isStickyHeader(position)) {
                tv_title_sticky.visibility = View.VISIBLE
                tv_content_sticky.visibility = View.GONE
            } else {
                tv_title_sticky.visibility = View.GONE
                tv_content_sticky.visibility = View.VISIBLE
            }
        }
    }

}