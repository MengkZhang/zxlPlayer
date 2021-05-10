package com.hx.player.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hx.player.base.BaseListAdapter
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
class HomeAdapter : BaseListAdapter<Data, HomeItemView>() {
    override fun getItemView(context: Context?): HomeItemView {
        return HomeItemView(context)
    }

    override fun refreshView(itemView: HomeItemView, data: Data?) {
        itemView.setData(data)
    }


}