package com.hx.player.ui.fragment

import com.hx.player.adapter.HomeAdapter
import com.hx.player.base.BaseListAdapter
import com.hx.player.base.BaseListFragment
import com.hx.player.base.BaseListPresenter
import com.hx.player.model.Data
import com.hx.player.model.HomeItemBean
import com.hx.player.presenter.impl.HomePresenterImpl
import com.hx.player.view.HomeView
import com.hx.player.widget.HomeItemView


/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/8
 * Copyright © 川大智胜
 */
class HomeFragment : BaseListFragment<HomeItemBean, Data, HomeItemView>(), HomeView {
    override fun getSpecialPresenter(): BaseListPresenter {
        return HomePresenterImpl(this)
    }

    override fun getSpecialAdapter(): BaseListAdapter<Data, HomeItemView> {
        return HomeAdapter()
    }

    override fun getList(data: HomeItemBean?): List<Data>? {
        return data?.data
    }


}