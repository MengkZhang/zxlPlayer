package com.hx.player.base

/**
 * Desc 列表的基类View
 *
 * @author zhangxiaolin
 * Date 2021/5/10
 * Copyright © 川大智胜
 */
interface BaseBmobView<RESPONSE> {
    /**
     * 获取数据失败
     */
    fun onError(message: String?)

    /**
     * 刷新成功
     */
    fun loadSuccess(data: List<RESPONSE>?)

    /**
     * 加载更多成功
     */
    fun loadMoreSuccess(data: List<RESPONSE>?)
}