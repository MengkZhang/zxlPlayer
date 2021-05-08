package com.hx.player.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.hx.player.R
import com.hx.player.base.BaseFragment

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/8
 * Copyright © 川大智胜
 */
class VBangFragment :BaseFragment() {
    override fun initView(): View? {
        val tv = TextView(context)
        tv.gravity = Gravity.CENTER
        tv.text = "vbang"
        tv.setTextColor(Color.BLACK)
        return tv
    }

}