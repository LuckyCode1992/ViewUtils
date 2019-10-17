package com.justcode.hxl.viewutil.recycleview_util.core

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


abstract class BaseAdapter : RecyclerView.Adapter<BaseViewHolder>() {
    abstract var layoutId: Int
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            LayoutInflater.from(parent.getContext()).inflate(
                layoutId,
                parent,
                false
            )
        )
    }

   abstract override fun getItemCount(): Int

   abstract override fun onBindViewHolder(holder: BaseViewHolder, position: Int)

}

class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view)

