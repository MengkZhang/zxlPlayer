package com.hx.player.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hx.player.model.Data
import com.hx.player.widget.HomeItemView
import java.util.ArrayList

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/8
 * Copyright © 川大智胜
 */
class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {

    private var list: ArrayList<Data> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(HomeItemView(parent.context))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        val data = list.get(position)
        val itemView = holder.itemView as HomeItemView
        itemView.setData(data)
    }

    fun updateData(list: ArrayList<Data>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}