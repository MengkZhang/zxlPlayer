package com.hx.player.ui.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hx.player.R
import com.hx.player.adapter.HomeAdapter
import com.hx.player.base.BaseFragment
import com.hx.player.model.Data
import com.hx.player.presenter.impl.HomePresenterImpl
import com.hx.player.view.HomeView
import kotlinx.android.synthetic.main.fragment_list.*


/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/8
 * Copyright © 川大智胜
 */
class HomeFragment : BaseFragment(), HomeView {


    private val presenter by lazy { HomePresenterImpl(this) }

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
            presenter.loadData()
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
                            presenter.loadMoreData(index)
                        }
                    }
                }

            }


        })
    }

    override fun initData() {
        super.initData()
        presenter.loadData()
    }

    override fun onError(message: String?) {
        refreshLayout.isRefreshing = false
        myToast("加载数据失败")

    }

    override fun loadSuccess(data: List<Data>?) {
        refreshLayout.isRefreshing = false
        homeAdapter.updateData(data)
    }

    override fun loadMoreSuccess(data: List<Data>?) {
        refreshLayout.isRefreshing = false
        homeAdapter.loadMoreData(data)
    }


}