package com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.entry

class HollowPieEntry : PieEntry {
    //距离圆心多少距离内中空(0~1)
    var hollowLengthRate: Float? = 0f

    constructor(hollowLengthRate: Float?) : super() {
        this.hollowLengthRate = hollowLengthRate
    }

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
        sweepAngle: Float? = 0f,
        hollowLengthRate: Float? = 0f
    ) : super(
        varue,
        color,
        isShowPieText,
        title,
        textColor,
        textSize,
        isShowPieIndicateText,
        indicateText,
        indicateLineColor,
        indicateTextColor,
        indicateTextSize,
        startAngle,
        sweepAngle
    ) {
        this.hollowLengthRate = hollowLengthRate
    }
}