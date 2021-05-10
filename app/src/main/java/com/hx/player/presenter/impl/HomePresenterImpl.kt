package com.hx.player.presenter.impl

import com.hx.player.model.HomeItemBean
import com.hx.player.net.HomeRequest
import com.hx.player.net.NetManager
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
class HomePresenterImpl(var homeView: HomeView) : HomePresenter {
    override fun loadData() {
        val homeRequest = HomeRequest(1, object : ResponseHandler<HomeItemBean> {
            override fun onError(msg: String?) {
                homeView.onError(msg)

            }

            override fun onSuccess(result: HomeItemBean?) {
                homeView.loadSuccess(result?.data)
            }
        })
        NetManager.netManager.sendRequest(homeRequest)

    }

//    override fun loadData() {
//        val index = 1
//        val path = URLProviderUtils.getHomeUrl(index)
//        val client = OkHttpClient()
//        val request = Request.Builder()
//            .url(path)
//            .get()
//            .build()
//        client.newCall(request).enqueue(object : Callback {
//            /**
//             * onFailure 在子线程中执行
//             */
//            override fun onFailure(call: Call, e: IOException) {
//                ThreadUtil.runOnMainThread(Runnable {
//                    homeView.onError(e.message)
//                })
//
//            }
//
//            /**
//             * onResponse 在子线程中执行
//             */
//            override fun onResponse(call: Call, response: Response) {
//
//                val bean: HomeItemBean =
//                    Gson().fromJson(response.body()?.string(), HomeItemBean::class.java)
//
//                ThreadUtil.runOnMainThread(Runnable {
//                    homeView.loadSuccess(bean.data)
//                })
//
//            }
//        })
//
//    }

    override fun loadMoreData(index: Int) {

        val homeRequest = HomeRequest(index, object : ResponseHandler<HomeItemBean> {
            override fun onError(msg: String?) {
                homeView.onError(msg)

            }

            override fun onSuccess(result: HomeItemBean?) {
                homeView.loadMoreSuccess(result?.data)
            }
        })
        NetManager.netManager.sendRequest(homeRequest)
    }

//    override fun loadMoreData(index: Int) {
//        val path = URLProviderUtils.getHomeUrl(index)
//        val client = OkHttpClient()
//        val request = Request.Builder()
//            .url(path)
//            .get()
//            .build()
//        client.newCall(request).enqueue(object : Callback {
//            /**
//             * onFailure 在子线程中执行
//             */
//            override fun onFailure(call: Call, e: IOException) {
//                ThreadUtil.runOnMainThread(Runnable { homeView.onError(e.message) })
//            }
//
//            /**
//             * onResponse 在子线程中执行
//             */
//            override fun onResponse(call: Call, response: Response) {
//
//                val bean: HomeItemBean =
//                    Gson().fromJson(response.body()?.string(), HomeItemBean::class.java)
//
//                ThreadUtil.runOnMainThread(Runnable {
//                    //回调到View层
//                    homeView.loadMoreSuccess(bean.data)
//                })
//
//            }
//        })
//    }

}