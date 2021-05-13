package com.hx.player.ui.fragment

import com.hx.player.adapter.MvListAdapter
import com.hx.player.base.BaseBmobListFragment
import com.hx.player.base.BaseListAdapter
import com.hx.player.base.BaseListPresenter
import com.hx.player.model.bmobModel.VideoListByTab
import com.hx.player.presenter.impl.MvListPresenterImpl
import com.hx.player.view.MvListView
import com.hx.player.widget.HomeItemView

class MvPageFragment : BaseBmobListFragment<VideoListByTab, HomeItemView>(),
    MvListView {

    var itemId: String? = null

    override fun init() {
        itemId = arguments?.getString("id")
    }

    override fun getSpecialPresenter(): BaseListPresenter {
        return MvListPresenterImpl(itemId,this)
    }

    override fun getSpecialAdapter(): BaseListAdapter<VideoListByTab, HomeItemView> {
        return MvListAdapter()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        presenter.destroyView()
    }


}
