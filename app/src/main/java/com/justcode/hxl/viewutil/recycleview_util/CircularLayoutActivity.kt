package com.justcode.hxl.viewutil.recycleview_util

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.recycleview_util.core.BaseAdapter
import com.justcode.hxl.viewutil.recycleview_util.core.BaseViewHolder
import com.leshchenko.circularlayoutmanagerlib.CircularRecyclerLayoutManager
import kotlinx.android.synthetic.main.activity_circular_layout.*

class CircularLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circular_layout)
        recycler_circular.layoutManager = CircularRecyclerLayoutManager(
            canScrollHorizontally = true,
            itemsPerCircle = 4,
            anglePerItem = 100.0,
            firstCircleRadius = 200.0,
            angleStepForCircles = 45.0,
            isClockwise = false,
            initialAngle = 180.0
        )
        recycler_circular.adapter = CircularAdapter()
    }
    inner class CircularAdapter:BaseAdapter(){
        override var layoutId: Int
            get() = R.layout.item_circular
            set(value) {}

        override fun getItemCount(): Int {
           return 200
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        }

    }
}
