package com.hx.player.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hx.player.R
import com.hx.player.model.Data
import kotlinx.android.synthetic.main.item_home.view.*

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/8
 * Copyright © 川大智胜
 */
class HomeItemView : RelativeLayout {
    /**
     * 设置数据
     */
    fun setData(data: Data) {
        title.text = data.artistsname
        desc.text = data.name

    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    /**
     * 初始化方法 几个构造方法都会执行
     */
    init {
        View.inflate(context, R.layout.item_home, this)
    }
}