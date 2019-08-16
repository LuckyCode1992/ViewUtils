package com.justcode.hxl.viewutil.shape_selector_util

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.justcode.hxl.viewutil.R
import kotlinx.android.synthetic.main.activity_shape.*

class ShapeAndSelectorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shape)

        rg.setOnCheckedChangeListener { group, id ->
            when (id) {
                R.id.rb_enable -> {
                    tv_enable.isEnabled = true
                }
                R.id.rb_unenable -> {
                    tv_enable.isEnabled = false
                }
            }
        }
        tv_selector.setOnClickListener {
            tv_selector.isSelected = !tv_selector.isSelected
        }
    }
}
