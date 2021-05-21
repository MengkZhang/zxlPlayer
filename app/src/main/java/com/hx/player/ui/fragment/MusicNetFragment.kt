package com.hx.player.ui.fragment

import com.hx.player.adapter.HomeAdapter
import com.hx.player.adapter.MusicListAdapter
import com.hx.player.adapter.MvListAdapter
import com.hx.player.base.*
import com.hx.player.model.AudioBean
import com.hx.player.model.Data
import com.hx.player.model.HomeItemBean
import com.hx.player.model.bmobModel.MusicList
import com.hx.player.model.bmobModel.VideoListByTab
import com.hx.player.presenter.impl.HomePresenterImpl
import com.hx.player.presenter.impl.MvListPresenterImpl
import com.hx.player.presenter.impl.VbPresenterImpl
import com.hx.player.ui.activity.AudioPlayActivity
import com.hx.player.ui.activity.PlayActivity
import com.hx.player.view.HomeView
import com.hx.player.view.MvListView
import com.hx.player.view.VbView
import com.hx.player.widget.HomeItemView
import com.hx.player.widget.VbangItemView
import org.jetbrains.anko.startActivity
import java.text.FieldPosition


/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/8
 * Copyright © 川大智胜
 */
class MusicNetFragment : BaseBmobListFragment<MusicList, VbangItemView>(),
    VbView {

    var itemId: String? = null

    override fun init() {
        itemId = arguments?.getString("id")
    }

    override fun getSpecialPresenter(): BaseListPresenter {
        return VbPresenterImpl(this)
    }

    override fun getSpecialAdapter(): BaseListAdapter<MusicList, VbangItemView> {
        return MusicListAdapter()
    }

    override fun initListener() {
        super.initListener()
        adapter.setMyListeners {
            toPlay(it)
        }
    }

    private fun toPlay(position: Int) {
        val dataList = ArrayList<AudioBean>()
        list?.let {
            for (index in 0 until it.size) {
                dataList.add(AudioBean(it[index].audio, 2000, it[index].title, it[index].author))
            }

            (context as BaseActivity).startActivity<AudioPlayActivity>(
                "item" to dataList,
                "position" to position
            )


        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        presenter.destroyView()
    }
}