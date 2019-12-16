package com.justcode.hxl.viewutil.自定义控件.第十二章_封装控件

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.TextView
import com.justcode.hxl.viewutil.R


class PackageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_package)


    }
}

class MyTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {

    init {
        val typedArrat = context.obtainStyledAttributes(attrs, R.styleable.MyTextView)
        val headerHeight = typedArrat.getDimension(R.styleable.MyTextView_headerHeight, -1f)
        val age = typedArrat.getInt(R.styleable.MyTextView_age, -1)
        typedArrat.recycle()
        this.setText("headerHeight:${headerHeight}-age:${age}")
    }
    /**
     * 常用 declare-styleable标签属性:
     *  - reference:资源ID，@drawable/xx
     *  - color:颜色值，@color/xx,#123456
     *  - boolean:布尔，true|false
     *  - dimension:尺寸值，20dp
     *  - float:浮点 ，20.0f
     *  - integer:整型，19
     *  - string: 字符串，"dsds"
     *  - fraction:百分数，30%
     *  - enum:枚举值，attr 下一级 <enum name="A" value="0"/>
     *  - flag:位或运算 和枚举类似，不同的是，可以多选
     */

}


/**
 *  view 和 viewGroup基本相同，只是ViewGroup 中不仅要绘制自己，还要绘制其中的子控件
 *  而View只需要绘制自己就可以了，所以这里就以ViewGroup为例来讲整个绘制流程
 *
 *  绘制流程分为3个步骤：测量，布局，绘制。onMeasure,onLayout,onDraw
 *      - onMeasure: 测量当前控件的大小，为正式布局提供建议，只是建议。
 *          - onMeasure函数与MeasureSpec
 *      - onLayout：使用layout函数，对所有子控件布局
 *      - onDraw：根据布局的位置绘图
 *
 *
 */
class MyLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {


    /**
     * 传入两个参数，这两个值就是参考值
     *
     * MeasureSpec 的组成：
     *  - 模式分类：
     *      - MeasureSpec.UNSPECIFIED: 父元素不对子元素施加任何束缚，子元素可以得到任意想要的大小
     *      - MeasureSpec.EXACTLY：父元素决定子元素的确切大小，子元素将欸限定在给定边界里而忽略他本身的大小
     *      - MeasureSpec.AT_MOST:子元素至多到指定的大小
     *  - 模式提取：widthMeasureSpec和heightMeasureSpec由模式和数值组成,二进制前两位标识模式，后三十位表示值
     *      - MeasureSpec.getMode() 获取模式
     *      - MeasureSpec.getSize() 获取值
     *  - 模式的用处：当模式是MeasureSpec.EXACTLY时，就不必设定我们计算得数值，当模式是MeasureSpec.AT_MOST，就需要设定计算值
     *      - xml 布局和模式对应：
     *          - wrap_content -> MeasureSpec.AT_MOST
     *          - match_parent -> MeasureSpec.EXACTLY
     *          - 具体值  -> MeasureSpec.EXACTLY
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measureWidth = MeasureSpec.getSize(widthMeasureSpec)
        val measureHeight = MeasureSpec.getSize(heightMeasureSpec)
        val measureWidthMode = MeasureSpec.getMode(widthMeasureSpec)
        val measureHeightMode = MeasureSpec.getMode(heightMeasureSpec)

        //计算后的宽高
        val width = 10
        val height = 10

        setMeasuredDimension(
            if (measureWidthMode == MeasureSpec.EXACTLY) {
                measureWidth
            } else {
                width
            },
            if (measureHeightMode == MeasureSpec.EXACTLY) {
                measuredHeight
            } else {
                height
            }
        )

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {


    }
}