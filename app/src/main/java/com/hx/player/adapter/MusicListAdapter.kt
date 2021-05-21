package com.hx.player.adapter

import android.content.Context
import com.hx.player.base.BaseListAdapter
import com.hx.player.model.bmobModel.MusicList
import com.hx.player.widget.VbangItemView

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/8
 * Copyright © 川大智胜
 */
class MusicListAdapter : BaseListAdapter<MusicList, VbangItemView>() {
    override fun getItemView(context: Context?): VbangItemView {
        return VbangItemView(context)
    }

    override fun refreshView(itemView: VbangItemView, data: MusicList?) {
        data?.let {
            itemView.setData(data)
        }
    }


}