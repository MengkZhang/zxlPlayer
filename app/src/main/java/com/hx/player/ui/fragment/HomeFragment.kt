package com.hx.player.ui.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.hx.player.R
import com.hx.player.adapter.HomeAdapter
import com.hx.player.base.BaseFragment
import com.hx.player.model.HomeItemBean
import com.hx.player.utils.ThreadUtil
import com.hx.player.utils.URLProviderUtils
import kotlinx.android.synthetic.main.fragment_list.*
import okhttp3.*
import java.io.IOException


/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/8
 * Copyright © 川大智胜
 */
class HomeFragment : BaseFragment() {

    private val homeAdapter by lazy { HomeAdapter() }
    private var index = 1

    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_list, null)
    }

    override fun initListener() {
        super.initListener()
        recycleView.layoutManager = LinearLayoutManager(context)
        val adapter = homeAdapter
        recycleView.adapter = adapter
        refreshLayout.setOnRefreshListener {
            //刷新
            loadData()
        }

        recycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val layoutManager = recyclerView.layoutManager
                    if (layoutManager is LinearLayoutManager) {
                        val manager: LinearLayoutManager = layoutManager
                        val lastVisibleItemPosition = manager.findLastVisibleItemPosition()
                        if (lastVisibleItemPosition == adapter.itemCount - 1) {
                            //加载更多
                            index++
//                            loadMoreData(adapter.itemCount - 1)
                            loadMoreData(index)
                        }
                    }
                }

            }


        })
    }

    override fun initData() {
        super.initData()
        loadData()
    }

    private fun loadData() {
        index = 1;
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
                ThreadUtil.runOnMainThread(Runnable { refreshLayout.isRefreshing = false })
                myToast("失败")
            }

            /**
             * onResponse 在子线程中执行
             */
            override fun onResponse(call: Call, response: Response) {

                val bean: HomeItemBean =
                    Gson().fromJson(response.body()?.string(), HomeItemBean::class.java)

                ThreadUtil.runOnMainThread(Runnable {
                    refreshLayout.isRefreshing = false
                    homeAdapter.updateData(bean.data)
                })

            }
        })


    }

    private fun loadMoreData(offSet: Int) {
        val path = URLProviderUtils.getHomeUrl(offSet)
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
                ThreadUtil.runOnMainThread(Runnable { refreshLayout.isRefreshing = false })
                myToast("失败")
            }

            /**
             * onResponse 在子线程中执行
             */
            override fun onResponse(call: Call, response: Response) {

                val bean: HomeItemBean =
                    Gson().fromJson(response.body()?.string(), HomeItemBean::class.java)

                ThreadUtil.runOnMainThread(Runnable {
                    refreshLayout.isRefreshing = false
                    homeAdapter.loadMoreData(bean.data)
                })

            }
        })


    }


}