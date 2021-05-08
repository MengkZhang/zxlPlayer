package com.hx.player.ui.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hx.player.R
import com.hx.player.adapter.HomeAdapter
import com.hx.player.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/8
 * Copyright © 川大智胜
 */
class HomeFragment :BaseFragment() {
    override fun initView(): View? {
        return View.inflate(context,R.layout.fragment_list,null)
    }

    override fun initListener() {
        super.initListener()
        recycleView.layoutManager = LinearLayoutManager(context)
        val adapter = HomeAdapter()
        recycleView.adapter = adapter
    }



}