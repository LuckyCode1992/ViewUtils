package com.justcode.hxl.viewutil.recycleview_util.core

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

abstract class BaseItemViewBinder<T> : ItemViewBinder<T, MyViewHolder>() {
    /**
     * 布局文件
     */
    abstract val layoutRes: Int
    var itemDataCallBack: ((T) -> Unit) = { }
    var itemPositionCallBack: ((Int) -> Unit) = {}
    var itemPositionDataCallBack: ((Int, T) -> Unit) = { position, data -> }
    var itemPositionViewCallBack: ((Int, View) -> Unit) = { position, view -> }
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): MyViewHolder {
        return MyViewHolder(inflater.inflate(layoutRes, parent, false))
    }


}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)