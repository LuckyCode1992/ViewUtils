package com.justcode.hxl.viewutil.自定义控件.第七章_绘图进阶.阴影效果

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.justcode.hxl.viewutil.R
import kotlinx.android.synthetic.main.activity_shadow_layer.*

class ShadowLayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shadow_layer)
        btn_add_shadow.setOnClickListener {
            shadow1.addShadow()
        }
        btn_delete_shadow.setOnClickListener {
            shadow1.deleteShadow()
        }
    }
}
