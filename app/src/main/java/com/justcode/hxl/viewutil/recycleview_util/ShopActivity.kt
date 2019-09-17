package com.justcode.hxl.viewutil.recycleview_util

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.justcode.hxl.viewutil.R
import com.justcode.hxl.viewutil.recycleview_util.core.BaseItemViewBinder
import com.justcode.hxl.viewutil.recycleview_util.core.MultiTypeAdapter
import com.justcode.hxl.viewutil.recycleview_util.core.MyViewHolder
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.discretescrolllayoutManager.DSVOrientation
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.discretescrolllayoutManager.DiscreteScrollView
import com.justcode.hxl.viewutil.recycleview_util.layoutmanager.discretescrolllayoutManager.ScaleTransformer
import kotlinx.android.synthetic.main.activity_shop.*
import kotlinx.android.synthetic.main.item_shop_card.view.*

class ShopActivity : AppCompatActivity() {
    val list: MutableList<Shop> = ArrayList()
    val adapter: MultiTypeAdapter = MultiTypeAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        //第一步，初始化layoutmanger
        recyclerView.init(DSVOrientation.HORIZONTAL.ordinal)
        //设置滑动动画时间
        recyclerView.setItemTransitionTimeMillis(150)
        //设置item切换动画
        recyclerView.setItemTransformer(
            ScaleTransformer.Builder()
                .setMinScale(0.7f)
                .setMaxScale(1.0f)
                .build()
        )
        //添加滚动监听
        recyclerView.addScrollStateChangeListener(object : DiscreteScrollView.ScrollStateChangeListener<MyViewHolder> {
            override fun onScrollStart(currentItemHolder: MyViewHolder, adapterPosition: Int) {

            }

            override fun onScrollEnd(currentItemHolder: MyViewHolder, adapterPosition: Int) {
                item_name.text = list[adapterPosition].name
                item_price.text = list[adapterPosition].price
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
        initShopList()
        adapter.register(ItemBinderShop())
        recyclerView.adapter = adapter
        adapter.items = list
        adapter.notifyItems()

    }

    private fun initShopList() {
        list.add(Shop("Everyday Candle", "$12.00 USD", R.drawable.shop1))
        list.add(Shop("Small Porcelain Bowl", "$50.00 USD", R.drawable.shop2))
        list.add(Shop("Favourite Board", "$265.00 USD", R.drawable.shop3))
        list.add(Shop("Earthenware Bowl", "$18.00 USD", R.drawable.shop4))
        list.add(Shop("Porcelain Dessert Plate", "$36.00 USD", R.drawable.shop5))
        list.add(Shop("Detailed Rolling Pin", "$145.00 USD", R.drawable.shop6))
    }
}

data class Shop(var name: String? = null, var price: String? = null, var image: Int? = null)
class ItemBinderShop : BaseItemViewBinder<Shop>() {

    override val layoutRes: Int
        get() = R.layout.item_shop_card

    override fun onBindViewHolder(holder: MyViewHolder, item: Shop) {
        with(holder.itemView) {
            image_shop.setImageResource(item.image ?: 0)
        }
    }
}