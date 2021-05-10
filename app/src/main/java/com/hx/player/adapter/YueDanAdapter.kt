package com.hx.player.adapter

import android.content.Context
import com.hx.player.base.BaseListAdapter
import com.hx.player.model.YueDanBean
import com.hx.player.widget.HomeItemView

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/8
 * Copyright © 川大智胜
 */
class YueDanAdapter : BaseListAdapter<YueDanBean.Data, HomeItemView>() {
    override fun getItemView(context: Context?): HomeItemView {
        return HomeItemView(context)
    }

    override fun refreshView(itemView: HomeItemView, data: YueDanBean.Data?) {
        itemView.setData(data)
    }


}