package com.hx.player.presenter.impl

import android.util.Log
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.hx.player.model.bmobModel.VideoTab
import com.hx.player.presenter.interfaces.MvPresenter
import com.hx.player.view.MvView

/**
 * Desc
 * HomePresenterImpl(var homeView: HomeView) 构造方法加var 可以在类中使用homeView
 *
 * @author zhangxiaolin
 * Date 2021/5/10
 * Copyright © 川大智胜
 */
class MvPresenterImpl(var mvView: MvView?) : MvPresenter {

    override fun loadData() {
        BmobQuery<VideoTab>().findObjects(object : FindListener<VideoTab>() {
            override fun done(list: MutableList<VideoTab>?, e: BmobException?) {
                if (e == null) {
                    list?.let {
                        Log.e("===z ", "size=" + list.size)
                        mvView?.loadSuccess(it)
                    }

                } else {
                    mvView?.onError(e.message)
                }

            }
        })
    }


}