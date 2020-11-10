package com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.entry

open class PieEntry {
    //此饼图项占比0~1
    var varue: Float? = 0f
    //饼图颜色
    var color: Int? = 0
    //是否在饼图上显示文字
    var isShowPieText: Boolean? = true
    var title: String? = null
    var textColor: Int? = 0
    //字体大小sp
    var textSize: Int? = 0
    //是否在饼图上显示指示文字
    var isShowPieIndicateText: Boolean? = true
    //外层指示文字
    var indicateText: String? = null
    //外层指示文字线颜色,
    var indicateLineColor: Int? = 0
    //外层指示文字颜色
    var indicateTextColor: Int? = 0
    //外层指示文字字体大小
    var indicateTextSize: Int? = 0
    var startAngle: Float? = 0f
    var sweepAngle: Float? = 0f

    constructor()
    constructor(
        //此饼图项占比0~1
        varue: Float? = 0f,
        //饼图颜色
        color: Int? = 0,
        //是否在饼图上显示文字
        isShowPieText: Boolean? = true,
        title: String? = null,
        textColor: Int? = 0,
        //字体大小sp
        textSize: Int? = 0,
        //是否在饼图上显示指示文字
        isShowPieIndicateText: Boolean? = true,
        //外层指示文字
        indicateText: String? = null,
        //外层指示文字线颜色,
        indicateLineColor: Int? = 0,
        //外层指示文字颜色
        indicateTextColor: Int? = 0,
        //外层指示文字字体大小
        indicateTextSize: Int? = 0,
        startAngle: Float? = 0f,
        sweepAngle: Float? = 0f
        ) {
        this.varue = varue
        this.color = color
        this.isShowPieText = isShowPieText
        this.title = title
        this.textColor = textColor
        this.textSize = textSize
        this.isShowPieIndicateText = isShowPieIndicateText
        this.indicateText = indicateText
        this.indicateLineColor = indicateLineColor
        this.indicateTextColor = indicateTextColor
        this.indicateTextSize = indicateTextSize
        this.startAngle = startAngle
        this.sweepAngle = sweepAngle
    }

}