package com.justcode.hxl.viewutil.recycleview_util.viewpagerlayoutactivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.graphics.drawable.VectorDrawableCompat
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Toast
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.viewpagerlayoutmanager.ScrollHelper
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.viewpagerlayoutmanager.ViewPagerLayoutManager
import kotlinx.android.synthetic.main.activity_base_view_pager.*
import kotlinx.android.synthetic.main.item_viewpagerlayout_layout.view.*

abstract class BaseViewPagerActivity<V : ViewPagerLayoutManager, S : SettingPopUpWindow> : AppCompatActivity() {
    lateinit var viewPagerLayoutManager: V
    var settingPopUpWindow: S? = null
    lateinit var recyclerView: RecyclerView
    abstract fun createLayoutManager(): V
    abstract fun createSettingPopUpWindow(): S
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_view_pager)
        viewPagerLayoutManager = createLayoutManager()
        recyclerView = recycler
        val dataAdpter = DataAdpter()
        dataAdpter.itemClick = { position, view ->
            Toast.makeText(this, "clicked:$position", Toast.LENGTH_SHORT).show()
            ScrollHelper.smoothScrollToTargetView(recyclerView, view)

        }
        recyclerView.adapter = dataAdpter
        recyclerView.layoutManager = viewPagerLayoutManager
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        val settings = menu.findItem(R.id.setting)
        val settingIcon = VectorDrawableCompat.create(resources, R.drawable.ic_settings_white_48px, null)
        settings.icon = settingIcon
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting -> {
                showDialog()
                return true
            }
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun showDialog() {
        if (settingPopUpWindow == null) {
            settingPopUpWindow = createSettingPopUpWindow()
        }
        settingPopUpWindow?.showAtLocation(recycler, Gravity.CENTER, 0, 0)
    }


    override fun onDestroy() {
        super.onDestroy()
        if (settingPopUpWindow != null && settingPopUpWindow?.isShowing ==true)
            settingPopUpWindow?.dismiss()
    }
}

class DataAdpter : RecyclerView.Adapter<MyHolder>() {
    var itemClick: (Int, View) -> Unit = { position, view -> }
    val images = intArrayOf(
        R.drawable.item1,
        R.drawable.item2,
        R.drawable.item3,
        R.drawable.item4,
        R.drawable.item5,
        R.drawable.item6,
        R.drawable.item7,
        R.drawable.item8,
        R.drawable.item9,
        R.drawable.item10
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_viewpagerlayout_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        with(holder.itemView) {
            image.setImageResource(images[position])
            setOnClickListener {
                itemClick.invoke(position, it)
            }
        }
    }

}


class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView)