package com.hx.player.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hx.player.model.Data
import com.hx.player.widget.HomeItemView
import com.hx.player.widget.LoadMoreView
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

    override fun getItemViewType(position: Int): Int {
        return if (position == list.size) {
            1
        } else {
            0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return if (viewType == 1) {
            //最后一条 加载更多
            HomeHolder(LoadMoreView(parent.context))
        } else {
            //item
            HomeHolder(HomeItemView(parent.context))
        }
    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        if (position == list.size) {
            return
        }
        val data = list.get(position)
        val itemView = holder.itemView as HomeItemView
        itemView.setData(data)
    }

    fun updateData(list: List<Data>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun loadMoreData(list: List<Data>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}