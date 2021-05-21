package com.hx.player.widget

import android.content.Context
import android.text.format.Formatter
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hx.player.R
import com.hx.player.model.AudioBean
import com.hx.player.model.bmobModel.MusicList
import kotlinx.android.synthetic.main.item_vbang.view.*

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/14
 * Copyright © 川大智胜
 */
class VbangItemView : RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        View.inflate(context, R.layout.item_vbang, this)
    }

    /**
     * view+data绑定
     */
    fun setData(itemBean: AudioBean) {
        //歌曲名
        title.text = itemBean.display_name
        //歌手名
        artist.text = itemBean.artist
        //歌曲大小
        size.text = Formatter.formatFileSize(context, itemBean.size)
    }

    fun setData(itemBean: MusicList) {
        //歌曲名
        title.text = itemBean.title
        //歌手名
        artist.text = itemBean.author
        //歌曲大小
        size.text = itemBean.size
    }
}