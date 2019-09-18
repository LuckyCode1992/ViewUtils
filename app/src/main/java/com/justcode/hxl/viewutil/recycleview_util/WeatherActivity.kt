package com.justcode.hxl.viewutil.recycleview_util

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.recycleview_util.core.BaseItemViewBinder
import com.justcode.hxl.viewutil.recycleview_util.core.MultiTypeAdapter
import com.justcode.hxl.viewutil.recycleview_util.core.MyViewHolder
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.discretescrolllayoutManager.DSVOrientation
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.discretescrolllayoutManager.DiscreteScrollView
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.discretescrolllayoutManager.ScaleTransformer
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.item_weather.view.*

class WeatherActivity : AppCompatActivity() {
    val list: MutableList<Weather> = ArrayList()
    val adapter: MultiTypeAdapter = MultiTypeAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        initWeatherList()
        val itemViewBinder = WeatherItemBinder()

        //第一步，初始化layoutmanger
        recycler_weather.init(DSVOrientation.HORIZONTAL.ordinal)
        //设置滑动动画时间
        recycler_weather.setItemTransitionTimeMillis(150)
        //设置item切换动画
        recycler_weather.setItemTransformer(
            ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build()
        )

        itemViewBinder.itemPositionCallBack = {
            recycler_weather.smoothScrollToPosition(it)
        }
        //设置滚动监听
        recycler_weather.addScrollStateChangeListener(object :
            DiscreteScrollView.ScrollStateChangeListener<MyViewHolder> {
            override fun onScrollStart(currentItemHolder: MyViewHolder, adapterPosition: Int) {

            }

            override fun onScrollEnd(currentItemHolder: MyViewHolder, adapterPosition: Int) {
                itemViewBinder.currentIndex = adapterPosition
                adapter.items = list
                adapter.notifyItems()
                ll_weather.setBackgroundColor(list[adapterPosition].back)
                weather_temperature.text = list[adapterPosition].temperature
                weather_description.text = list[adapterPosition].weatherName
                weather_image.setImageResource(list[adapterPosition].weather)
            }

            override fun onScroll(
                scrollPosition: Float,
                currentPosition: Int,
                newPosition: Int,
                currentHolder: MyViewHolder?,
                newCurrent: MyViewHolder?
            ) {

            }
        })

        adapter.register(itemViewBinder)
        recycler_weather.adapter = adapter
        adapter.items = list
        adapter.notifyItems()
    }


    private fun initWeatherList() {
        list.add(
            Weather(
                "Pisa",
                R.drawable.pisa,
                "16",
                R.drawable.clear,
                "Clear",
                Color.parseColor("#5d8fb2")
            )
        )
        list.add(
            Weather(
                "Paris",
                R.drawable.paris,
                "14",
                R.drawable.cloudy,
                "Cloudy",
                Color.parseColor("#61b3e5")
            )
        )
        list.add(
            Weather(
                "New York",
                R.drawable.new_york,
                "9",
                R.drawable.mostly_cloudy,
                "Mostly Cloudy",
                Color.parseColor("#62bff5")
            )
        )
        list.add(
            Weather(
                "Rome",
                R.drawable.rome,
                "18",
                R.drawable.partly_cloudy,
                "Partly Cloudy",
                Color.parseColor("#cd47c6")
            )
        )
        list.add(
            Weather(
                "London",
                R.drawable.london,
                "6",
                R.drawable.cloudy,
                "Cloudy",
                Color.parseColor("#6d69ff")
            )
        )
        list.add(
            Weather(
                "Washington",
                R.drawable.washington,
                "20",
                R.drawable.clear,
                "Clear",
                Color.parseColor("#3453d1")
            )
        )
    }
}

data class Weather(
    var cityName: String,
    var cityIcon: Int,
    var temperature: String,
    var weather: Int,
    var weatherName: String,
    var back: Int

)

class WeatherItemBinder : BaseItemViewBinder<Weather>() {
    override val layoutRes: Int
        get() = R.layout.item_weather
    var currentIndex = 0
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override
    fun onBindViewHolder(holder: MyViewHolder, item: Weather) {
        with(holder.itemView) {
            city_image.setImageResource(item.cityIcon)
            city_name.text = item.cityName
            if (currentIndex == holder.adapterPosition) {
                city_name.visibility = View.VISIBLE

            } else {
                city_name.visibility = View.GONE
            }

            setOnClickListener {
                itemPositionCallBack.invoke(holder.adapterPosition)
            }
        }
    }

}