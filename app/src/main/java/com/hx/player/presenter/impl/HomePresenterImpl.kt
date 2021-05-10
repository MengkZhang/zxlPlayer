package com.hx.player.presenter.impl

import com.hx.player.model.HomeItemBean
import com.hx.player.net.HomeRequest
import com.hx.player.net.ResponseHandler
import com.hx.player.presenter.interfaces.HomePresenter
import com.hx.player.view.HomeView

/**
 * Desc
 * HomePresenterImpl(var homeView: HomeView) 构造方法加var 可以在类中使用homeView
 *
 * @author zhangxiaolin
 * Date 2021/5/10
 * Copyright © 川大智胜
 */
class HomePresenterImpl(var homeView: HomeView?) : HomePresenter {
    override fun destroyView() {
        if (homeView != null) homeView == null
    }

    override fun loadData() {
        HomeRequest(1, object : ResponseHandler<HomeItemBean> {
            override fun onError(msg: String?) {
                homeView?.onError(msg)

            }

            override fun onSuccess(result: HomeItemBean?) {
                homeView?.loadSuccess(result)
            }
        }).execute()

    }


    override fun loadMoreData(index: Int) {

        HomeRequest(index, object : ResponseHandler<HomeItemBean> {
            override fun onError(msg: String?) {
                homeView?.onError(msg)

            }

            override fun onSuccess(result: HomeItemBean?) {
                homeView?.loadMoreSuccess(result)
            }
        }).execute()
    }


}