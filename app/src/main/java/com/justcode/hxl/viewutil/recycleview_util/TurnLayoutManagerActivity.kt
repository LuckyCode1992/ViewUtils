package com.justcode.hxl.viewutil.recycleview_util

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.SeekBar
import androidx.annotation.LayoutRes
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.recycleview_util.core.BaseAdapter
import com.justcode.hxl.viewutil.recycleview_util.core.BaseViewHolder
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.turnlayoutmanager.TurnLayoutManager
import kotlinx.android.synthetic.main.activity_turn_layout_manager.*
import kotlinx.android.synthetic.main.item_turn.view.*

class TurnLayoutManagerActivity : AppCompatActivity() {
    lateinit var layoutManager: TurnLayoutManager
    var adapter = TrunAdapter()

    private val gravityOptionsClickListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            when (position) {
                0 -> {
                    layoutManager.setGravity(TurnLayoutManager.Gravity.START)
                    return
                }
                1 -> layoutManager.setGravity(TurnLayoutManager.Gravity.END)
            }// do nothing
        }

        override fun onNothingSelected(parent: AdapterView<*>) {

        }
    }
    private val orientationOptionsClickListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            when (position) {
                0 -> {
                    layoutManager.orientation = TurnLayoutManager.Orientation.VERTICAL
                    return
                }
                1 -> layoutManager.orientation = TurnLayoutManager.Orientation.HORIZONTAL
            }// do nothing
        }

        override fun onNothingSelected(parent: AdapterView<*>) {

        }
    }

    private val rotateListener =
        CompoundButton.OnCheckedChangeListener { buttonView, isChecked -> layoutManager.setRotate(isChecked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_turn_layout_manager)


        recycler_turn.adapter = adapter

        layoutManager = TurnLayoutManager(
            this,
            seek_radius.progress,
            seek_peek.progress
        )
        recycler_turn.layoutManager = layoutManager
        adapter.notifyDataSetChanged()


        seek_radius.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                radius_text.text = "radius:$progress"
                if (fromUser) {
                    layoutManager.setRadius(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        seek_peek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                peek_text.text = "peek:${progress}"
                if (fromUser) {
                    layoutManager.setPeekDistance(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        gravity.onItemSelectedListener = gravityOptionsClickListener
        orientation.onItemSelectedListener = orientationOptionsClickListener
        gravity.adapter = GravityAdapter(this, R.layout.spinner_item)
        orientation.adapter = OrientationAdapter(this, R.layout.spinner_item)
        rotate.setOnCheckedChangeListener(rotateListener)
        control_handle.setOnClickListener {
            val translationY = (if (control_panel.getTranslationY() == 0f) control_panel.getHeight() else 0).toFloat()
            control_panel.animate().translationY(translationY).start()
            control_handle.animate().translationY(translationY).start()
        }
    }


}

private class OrientationAdapter(context: Context, @LayoutRes resource: Int) :
    ArrayAdapter<String>(context, resource, arrayOf("Vertical", "Horizontal"))

private class GravityAdapter(context: Context, @LayoutRes resource: Int) :
    ArrayAdapter<String>(context, resource, arrayOf("Start", "End"))

class TrunAdapter : BaseAdapter() {
    override var layoutId: Int
        get() = R.layout.item_turn
        set(value) {}

    override fun getItemCount(): Int {
        return 50
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        with(holder.itemView) {
            tv_number.text = position.toString()
        }
    }

}