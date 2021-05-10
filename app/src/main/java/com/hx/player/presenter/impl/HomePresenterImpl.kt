package com.hx.player.presenter.impl

import com.google.gson.Gson
import com.hx.player.model.HomeItemBean
import com.hx.player.presenter.interfaces.HomePresenter
import com.hx.player.utils.ThreadUtil
import com.hx.player.utils.URLProviderUtils
import com.hx.player.view.HomeView
import kotlinx.android.synthetic.main.fragment_list.*
import okhttp3.*
import java.io.IOException

/**
 * Desc
 * HomePresenterImpl(var homeView: HomeView) 构造方法加var 可以在类中使用homeView
 *
 * @author zhangxiaolin
 * Date 2021/5/10
 * Copyright © 川大智胜
 */
class HomePresenterImpl(var homeView: HomeView) : HomePresenter {
    override fun loadData() {
        val index = 1
        val path = URLProviderUtils.getHomeUrl(index)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(path)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            /**
             * onFailure 在子线程中执行
             */
            override fun onFailure(call: Call, e: IOException) {
                ThreadUtil.runOnMainThread(Runnable {
                    homeView.onError(e.message)
                })

            }

            /**
             * onResponse 在子线程中执行
             */
            override fun onResponse(call: Call, response: Response) {

                val bean: HomeItemBean =
                    Gson().fromJson(response.body()?.string(), HomeItemBean::class.java)

                ThreadUtil.runOnMainThread(Runnable {
                    homeView.loadSuccess(bean.data)
                })

            }
        })

    }

    override fun loadMoreData(index: Int) {
        val path = URLProviderUtils.getHomeUrl(index)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(path)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            /**
             * onFailure 在子线程中执行
             */
            override fun onFailure(call: Call, e: IOException) {
                ThreadUtil.runOnMainThread(Runnable { homeView.onError(e.message) })
            }

            /**
             * onResponse 在子线程中执行
             */
            override fun onResponse(call: Call, response: Response) {

                val bean: HomeItemBean =
                    Gson().fromJson(response.body()?.string(), HomeItemBean::class.java)

                ThreadUtil.runOnMainThread(Runnable {
                    //回调到View层
                    homeView.loadMoreSuccess(bean.data)
                })

            }
        })
    }

}