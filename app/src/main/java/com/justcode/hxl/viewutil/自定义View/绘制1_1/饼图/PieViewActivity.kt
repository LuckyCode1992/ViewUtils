package com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.entry.HollowPieEntry
import com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.entry.PieEntry
import com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.interfaces.OnPieViewItemClickListener
import com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.interfaces.PieTextVisibleFilter
import java.util.*

class PieViewActivity : AppCompatActivity() {
    lateinit var mPieView: PieView
    lateinit var  titleTv: TextView
    lateinit  var centerTitleTv:TextView
    lateinit var  mSwitch: Switch
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie_view)
        mPieView = findViewById(R.id.pieView)
        titleTv = findViewById(R.id.title_tv)
        centerTitleTv = findViewById(R.id.center_title_tv)
        mSwitch = findViewById(R.id.switch1)
        mPieView.setPieEntries(getMarsmanElement())
        mSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(
                this,
                "点击任意绿色按钮后生效",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun simpleExample1(view: View?) {
        changeStyle("2018年全球游戏市场")
        val pieEntries: MutableList<PieEntry> = ArrayList<PieEntry>()
        pieEntries.add(PieEntry(0.23f, "北美 23%"))
        pieEntries.add(PieEntry(0.04f, "拉丁美洲 4%"))
        pieEntries.add(PieEntry(0.52f, "亚太地区 52%"))
        pieEntries.add(PieEntry(0.21f, "欧洲,中东和非洲 21%"))
        if (mSwitch.isChecked) {
            mPieView.setHollowPieEntries(pieEntryArrToHollowArr(pieEntries))
        } else {
            mPieView.setPieEntries(pieEntries)
        }
        mPieView.setOnPieViewItemClickListener { pieEntry ->
            Toast.makeText(this@PieViewActivity, "点击:${pieEntry.getTitle()}" , Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun simpleExample2(view: View?) {
        changeStyle("2018年全球游戏市场")
        val pieEntries: MutableList<PieEntry> = ArrayList<PieEntry>()
        pieEntries.add(PieEntry(0.23f, "北美 23%", "$327亿"))
        pieEntries.add(PieEntry(0.04f, "拉丁美洲 4%", "$50亿"))
        pieEntries.add(PieEntry(0.52f, "亚太地区 52%", "$714亿"))
        pieEntries.add(PieEntry(0.21f, "欧洲,中东和非洲 21%", "$287亿"))
        if (mSwitch.isChecked) {
            mPieView.setHollowPieEntries(pieEntryArrToHollowArr(pieEntries))
        } else {
            mPieView.setPieEntries(pieEntries)
        }
        mPieView.setOnPieViewItemClickListener(null)
    }

    fun simpleExample3(view: View?) {
        changeStyle("火星人体元素组成")
        if (mSwitch.isChecked) {
            mPieView.setHollowPieEntries(pieEntryArrToHollowArr(getMarsmanElement()))
        } else {
            mPieView.setPieEntries(getMarsmanElement())
        }
        mPieView.setOnPieViewItemClickListener(null)
    }

    fun varyParameterExample(view: View?) {
        changeStyle("2018年全球游戏市场")
        //饼图内文字颜色
        val textColor = -0x1000000
        //饼图内文字大小sp
        val textSize = 12
        //指示线颜色
        val indicateColor = -0x10000
        //指示文字颜色
        val indicateTextColor = -0x10000
        //指示文字大小
        val indicateTextSize = 10
        val pieEntries: MutableList<PieEntry> = ArrayList<PieEntry>()
        val pieEntry1 = PieEntry(
            0.23f,
            "北美 23%",
            textColor,
            textSize,
            "$327亿",
            indicateColor,
            indicateTextColor,
            indicateTextSize
        )
        val pieEntry2 = PieEntry(
            0.04f,
            "拉丁美洲 4%",
            textColor,
            textSize,
            "$50亿",
            indicateColor,
            indicateTextColor,
            indicateTextSize
        )
        pieEntry2.setShowPieText(false) //不显示饼图文字
        //可单独设置扇形区域各种属性
        val pieEntry3 =
            PieEntry(0.52f, "亚太地区 52%", -0xff5bc8, 16, "$714亿", -0xffff01, -0xff0060, 12)
        //indicateText传null,则不显示指示线及指示文字
        val pieEntry4 = PieEntry(
            0.21f,
            "欧洲,中东和非洲 21%",
            textColor,
            textSize,
            null,
            indicateColor,
            indicateTextColor,
            indicateTextSize
        )
        pieEntry4.setColor(-0x6c6c01) //设置饼图颜色
        pieEntries.add(pieEntry1)
        pieEntries.add(pieEntry2)
        pieEntries.add(pieEntry3)
        pieEntries.add(pieEntry4)
        if (mSwitch.isChecked) {
            mPieView.setHollowPieEntries(pieEntryArrToHollowArr(pieEntries))
        } else {
            mPieView.setPieEntries(pieEntries)
        }
        mPieView.setOnPieViewItemClickListener(null)
    }

    private var isNotFilter = false

    fun filterExampe(view: View) { //大于等于0.15的显示饼图内文字
        mPieView.setPieTextVisibleFilter { pieEntry ->
            if (isNotFilter) true else pieEntry.value.compareTo(0.15f) == 1 || pieEntry.value.compareTo(
                0.15f
            ) == 0
        }
        //小于0.15的显示指示线
        mPieView.setPieIndicateTextVisibleFilter { pieEntry ->
            if (isNotFilter) true else pieEntry.value.compareTo(0.15f) == -1
        }
        isNotFilter = !isNotFilter
        (view as Button).text = if (isNotFilter) "禁用文字显示筛选器" else "启用文字显示筛选器"
    }

    fun toggleHighLightEnable(view: View) {
        mPieView.isHighlightEnable = !mPieView.isHighlightEnable
        (view as Button).text = if (mPieView.isHighlightEnable) "禁用点击高亮" else "启用点击高亮"
    }

    fun toggleAutoUnpress(view: View) {
        mPieView.isAutoUnpressOther = !mPieView.isAutoUnpressOther
        (view as Button).text = if (mPieView.isAutoUnpressOther) "禁用自动收回其他非高亮饼图" else "自动收回其他非高亮饼图"
    }

    private fun getMarsmanElement(): List<PieEntry> {
        val indicateColor = -0xf9c89f
        val indicateTextColor = -0xf6b2e3
        val textSize = 12
        val indicateTextSize = 10
        val pieEntries: MutableList<PieEntry> = ArrayList<PieEntry>()
        pieEntries.add(
            PieEntry(
                0.28f,
                "C",
                -0x1,
                textSize,
                "碳 28%",
                indicateColor,
                indicateTextColor,
                indicateTextSize
            )
        )
        pieEntries.add(
            PieEntry(
                0.19f,
                "N",
                -0x1,
                textSize,
                "氮 19%",
                indicateColor,
                indicateTextColor,
                indicateTextSize
            )
        )
        pieEntries.add(
            PieEntry(
                0.17f,
                "P",
                -0x1,
                textSize,
                "磷 17%",
                indicateColor,
                indicateTextColor,
                indicateTextSize
            )
        )
        pieEntries.add(
            PieEntry(
                0.135f,
                "H",
                -0x1,
                textSize,
                "氢 13.5%",
                indicateColor,
                indicateTextColor,
                indicateTextSize
            )
        )
        pieEntries.add(
            PieEntry(
                0.087f,
                "O",
                -0x1,
                textSize,
                "氧 8.7%",
                indicateColor,
                indicateTextColor,
                indicateTextSize
            )
        )
        pieEntries.add(
            PieEntry(
                0.06f,
                "Fe",
                -0x1,
                textSize,
                "铁 6%",
                indicateColor,
                indicateTextColor,
                indicateTextSize
            )
        )
        pieEntries.add(
            PieEntry(
                0.055f,
                "S",
                -0x1,
                textSize,
                "硫 5.5%",
                indicateColor,
                indicateTextColor,
                indicateTextSize
            )
        )
        pieEntries.add(
            PieEntry(
                0.023f,
                "其他",
                -0x1,
                textSize,
                "少于3%",
                indicateColor,
                indicateTextColor,
                indicateTextSize
            )
        )
        return pieEntries
    }

    private fun pieEntryArrToHollowArr(pieEntries: List<PieEntry>): List<HollowPieEntry> {
        val hollowPieEntries: MutableList<HollowPieEntry> =
            ArrayList<HollowPieEntry>()
        val hollowLengthRate = 0.6f //距圆心多少距离内中空(0~1)
        for (pieEntry in pieEntries) {
            val hollowPieEntry = HollowPieEntry(pieEntry, hollowLengthRate)
            hollowPieEntries.add(hollowPieEntry)
        }
        return hollowPieEntries
    }

    private fun changeStyle(title: String) {
        if (mSwitch.isChecked) {
            centerTitleTv.text = title
            mPieView.setBackgroundColor(-0x813b8f)
        } else {
            mPieView.setBackgroundColor(0)
        }
        titleTv.text = title
        centerTitleTv.visibility = if (mSwitch.isChecked) View.VISIBLE else View.GONE
    }
}
