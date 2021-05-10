package com.hx.player.ui.fragment

import com.hx.player.adapter.YueDanAdapter
import com.hx.player.base.BaseListAdapter
import com.hx.player.base.BaseListFragment
import com.hx.player.base.BaseListPresenter
import com.hx.player.model.YueDanBean
import com.hx.player.presenter.impl.YueDanPresenterImpl
import com.hx.player.view.YueDanView
import com.hx.player.widget.HomeItemView

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/8
 * Copyright © 川大智胜
 */
class YueDanFragment : BaseListFragment<YueDanBean, YueDanBean.Data, HomeItemView>(), YueDanView {
    override fun getSpecialPresenter(): BaseListPresenter {
        return YueDanPresenterImpl(this)
    }

    override fun getSpecialAdapter(): BaseListAdapter<YueDanBean.Data, HomeItemView> {
        return YueDanAdapter()
    }

    override fun getList(data: YueDanBean?): List<YueDanBean.Data>? {
        return data?.data
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.destroyView()
    }


}