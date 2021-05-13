package com.hx.player.presenter.impl

import android.util.Log
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.hx.player.model.bmobModel.VideoListByTab
import com.hx.player.presenter.interfaces.MvListPresenter
import com.hx.player.view.MvListView

/**
 * Desc
 * HomePresenterImpl(var homeView: HomeView) 构造方法加var 可以在类中使用homeView
 *
 * @author zhangxiaolin
 * Date 2021/5/10
 * Copyright © 川大智胜
 */
class MvListPresenterImpl(var itemId: String? = "127311", var view: MvListView?) : MvListPresenter {
    override fun destroyView() {
        if (view != null) view == null
    }

    override fun loadData() {
        itemId?.let {
            val bmobQuery = BmobQuery<VideoListByTab>()
            bmobQuery.addWhereEqualTo("itemId", itemId)

            bmobQuery.findObjects(object : FindListener<VideoListByTab>() {
                override fun done(list: MutableList<VideoListByTab>?, e: BmobException?) {
                    if (e == null) {
                        list?.let {
                            Log.e("===z ", "size=" + list.size)
                            view?.loadSuccess(list)
                        }

                    } else {
                        view?.onError(e.message)
                    }

                }
            })
        }
    }


    override fun loadMoreData(index: Int) {

    }


}