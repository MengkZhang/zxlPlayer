package com.hx.player.presenter.impl

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.hx.player.model.bmobModel.MusicList
import com.hx.player.presenter.interfaces.VbPresenter
import com.hx.player.view.VbView

/**
 * Desc
 * HomePresenterImpl(var homeView: HomeView) 构造方法加var 可以在类中使用homeView
 *
 * @author zhangxiaolin
 * Date 2021/5/10
 * Copyright © 川大智胜
 */
class VbPresenterImpl(var view: VbView?) : VbPresenter {
    override fun destroyView() {
        if (view != null) view == null
    }

    override fun loadData() {
        val bmobQuery = BmobQuery<MusicList>()

        bmobQuery.findObjects(object : FindListener<MusicList>() {
            override fun done(list: MutableList<MusicList>?, e: BmobException?) {
                if (e == null) {
                    view?.loadSuccess(list)

                } else {
                    view?.onError(e.message)
                }

            }
        })
    }


    override fun loadMoreData(index: Int) {

    }


}