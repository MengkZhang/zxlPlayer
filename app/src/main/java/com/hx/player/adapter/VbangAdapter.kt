package com.hx.player.adapter

import android.content.Context
import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import androidx.cursoradapter.widget.CursorAdapter
import com.hx.player.model.AudioBean
import com.hx.player.widget.VbangItemView

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/14
 * Copyright © 川大智胜
 */

class VbangAdapter(context: Context?, c: Cursor?) : CursorAdapter(context, c) {
    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return VbangItemView(context)
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        val itemView = view as VbangItemView
        val list = AudioBean.getAudioBean(cursor)
        itemView.setData(list)
    }
}