package com.justcode.hxl.viewutil.自定义View.绘制1_1


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.Toast
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.extend.start
import com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.PieViewActivity
import kotlinx.android.synthetic.main.page_fragment_1_1.view.*


class PageFragment :
    Fragment() {
    var sampleLayoutRes = 0
    var practiceLayoutRes = 0

    companion object {
        fun buildFragment(sampleLayoutRes: Int, practiceLayoutRes: Int): Fragment {
            val fragment = PageFragment()
            val args = Bundle()
            args.putInt("sampleLayoutRes", sampleLayoutRes)
            args.putInt("practiceLayoutRes", practiceLayoutRes)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.page_fragment_1_1, container, false)
        val sampleStub = view.findViewById<View>(R.id.sampleStub) as ViewStub
        sampleStub.layoutResource = sampleLayoutRes
        sampleStub.inflate()

        val practiceStub =
            view.findViewById<View>(R.id.practiceStub) as ViewStub
        practiceStub.layoutResource = practiceLayoutRes
        practiceStub.inflate()
        if (sampleLayoutRes == R.layout.sample_pie_chart) {
            view.rl_back.setOnClickListener {
                context?.start<PieViewActivity>()
            }
        }

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        if (args != null) {
            sampleLayoutRes = args.getInt("sampleLayoutRes")
            practiceLayoutRes = args.getInt("practiceLayoutRes")
        }
    }


}