package com.hx.player.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.hx.player.R
import com.hx.player.adapter.MvPageAdapter
import com.hx.player.base.BaseFragment
import com.hx.player.model.bmobModel.VideoTab
import com.hx.player.presenter.impl.MvPresenterImpl
import com.hx.player.view.MvView
import kotlinx.android.synthetic.main.fragment_mv.*

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/8
 * Copyright © 川大智胜
 */
class MvFragment : BaseFragment(), MvView {

    val presenter by lazy {
        MvPresenterImpl(this)
    }

    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_mv, null)
    }

    override fun initData() {
        super.initData()
        presenter.loadData()
    }

    override fun onError(message: String?) {
        myToast(message)
    }

    override fun loadSuccess(data: List<VideoTab>?) {
        viewPager?.adapter = MvPageAdapter(context, data, childFragmentManager)
        tabLayout?.setupWithViewPager(viewPager)
    }

}