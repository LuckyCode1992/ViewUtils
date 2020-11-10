package com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.`interface`

import com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.drawable.BaseTextDrawable

interface PieTextFactory <T : BaseTextDrawable>{
    fun createPieText(): T
}