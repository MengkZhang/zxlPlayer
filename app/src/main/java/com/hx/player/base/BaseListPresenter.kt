package com.hx.player.base

/**
 * Desc 列表p基类
 *
 * @author zhangxiaolin
 * Date 2021/5/10
 * Copyright © 川大智胜
 */
interface BaseListPresenter {
    fun loadData()
    fun loadMoreData(index: Int)

    fun destroyView()
}