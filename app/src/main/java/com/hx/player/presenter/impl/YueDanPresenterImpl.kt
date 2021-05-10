package com.hx.player.presenter.impl

import com.hx.player.model.YueDanBean
import com.hx.player.net.ResponseHandler
import com.hx.player.net.YueDanRequest
import com.hx.player.presenter.interfaces.YueDanPresenter
import com.hx.player.view.YueDanView

/**
 * Desc
 * HomePresenterImpl(var homeView: HomeView) 构造方法加var 可以在类中使用homeView
 *
 * @author zhangxiaolin
 * Date 2021/5/10
 * Copyright © 川大智胜
 */
class YueDanPresenterImpl(var yueDanView: YueDanView?) : YueDanPresenter {
    override fun destroyView() {
        if (yueDanView != null) yueDanView == null
    }

    override fun loadData() {
        YueDanRequest(1, object : ResponseHandler<YueDanBean> {
            override fun onError(msg: String?) {
                yueDanView?.onError(msg)

            }

            override fun onSuccess(result: YueDanBean?) {
                yueDanView?.loadSuccess(result)
            }
        }).execute()

    }


    override fun loadMoreData(index: Int) {

        YueDanRequest(index, object : ResponseHandler<YueDanBean> {
            override fun onError(msg: String?) {
                yueDanView?.onError(msg)

            }

            override fun onSuccess(result: YueDanBean?) {
                yueDanView?.loadMoreSuccess(result)
            }
        }).execute()
    }


}