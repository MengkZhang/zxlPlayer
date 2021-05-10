package com.hx.player.base

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hx.player.R
import kotlinx.android.synthetic.main.fragment_list.*


/**
 * Desc 列表基类抽取
 * 先把HomeFragment拿过来 再分析哪些可变
 * HomeView -- BaseView
 * HomeAdapter -- BaseListAdapter
 * HomePresenterImpl -- BaseListPresenter
 *
 * @author zhangxiaolin
 * Date 2021/5/8
 * Copyright © 川大智胜
 */
abstract class BaseListFragment<RESPONSE, ITEMBEAN, ITEMVIEW : View> : BaseFragment(),
    BaseView<RESPONSE> {


    protected val presenter by lazy { getSpecialPresenter() }

    private val adapter by lazy { getSpecialAdapter() }
    private var index = 1

    abstract fun getSpecialPresenter(): BaseListPresenter
    abstract fun getSpecialAdapter(): BaseListAdapter<ITEMBEAN, ITEMVIEW>

    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_list, null)
    }

    override fun initListener() {
        super.initListener()
        recycleView.layoutManager = LinearLayoutManager(context)
        val adapter = adapter
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

    override fun loadSuccess(data: RESPONSE?) {
        refreshLayout.isRefreshing = false
        adapter.updateData(getList(data))

    }

    override fun loadMoreSuccess(data: RESPONSE?) {
        refreshLayout.isRefreshing = false
        adapter.loadMoreData(getList(data))
    }

    abstract fun getList(data: RESPONSE?): List<ITEMBEAN>?


}