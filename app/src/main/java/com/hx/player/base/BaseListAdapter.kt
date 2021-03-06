package com.hx.player.base

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hx.player.widget.LoadMoreView
import java.util.*

/**
 * Desc 列表的基类
 *
 * @author zhangxiaolin
 * Date 2021/5/8
 * Copyright © 川大智胜
 */
abstract class BaseListAdapter<ITEMBEAN, ITEMVIEW : View> :
    RecyclerView.Adapter<BaseListAdapter.BaseListHolder>() {

    private var list: ArrayList<ITEMBEAN> = ArrayList()


    override fun getItemViewType(position: Int): Int {
        return if (position == list.size) {
            1
        } else {
            0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListHolder {
        return if (viewType == 1) {
            //最后一条 加载更多
            val loadMoreView = LoadMoreView(parent.context)
            BaseListHolder(loadMoreView)
        } else {
            //item
            BaseListHolder(getItemView(parent.context))
        }
    }


    override fun getItemCount(): Int {
        return list.size + 1
    }

    override fun onBindViewHolder(holder: BaseListHolder, position: Int) {
        if (position == list.size) {
            return
        }
        val data: ITEMBEAN = list.get(position)
        val itemView: ITEMVIEW = holder.itemView as ITEMVIEW
        refreshView(itemView, data)

        itemView.setOnClickListener {
            listener?.invoke(data)
            listeners?.invoke(position)
        }
    }

    //定义函数类型变量
    var listener: ((itemBean: ITEMBEAN) -> Unit)? = null

    fun setMyListener(listener: (itemBean: ITEMBEAN) -> Unit) {
        this.listener = listener
    }

    //定义函数类型变量
    var listeners: ((position: Int) -> Unit)? = null

    fun setMyListeners(listener: (position: Int) -> Unit) {
        this.listeners = listener
    }


    fun updateData(list: List<ITEMBEAN>?) {
        //let表达式 相当于Java的list的判空
        list?.let {
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }

    fun loadMoreData(list: List<ITEMBEAN>?) {
        list?.let {
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }

    abstract fun getItemView(context: Context?): ITEMVIEW
    abstract fun refreshView(itemView: ITEMVIEW, data: ITEMBEAN?)


    class BaseListHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}