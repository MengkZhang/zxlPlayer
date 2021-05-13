package com.hx.player.view

import com.hx.player.model.bmobModel.VideoTab

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/13
 * Copyright © 川大智胜
 */
interface MvView {
    /**
     * 获取数据失败
     */
    fun onError(message: String?)

    /**
     * 刷新成功
     */
    fun loadSuccess(data: List<VideoTab>?)

}