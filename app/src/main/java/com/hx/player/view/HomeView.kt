package com.hx.player.view

import com.hx.player.model.Data

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/10
 * Copyright © 川大智胜
 */
interface HomeView {
    /**
     * 获取数据失败
     */
    fun onError(message: String?)

    /**
     * 刷新成功
     */
    fun loadSuccess(data: List<Data>?)

    /**
     * 加载更多成功
     */
    fun loadMoreSuccess(data: List<Data>?)

}