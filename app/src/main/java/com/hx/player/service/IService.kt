package com.hx.player.service

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/14
 * Copyright © 川大智胜
 */
interface IService {
    fun playItem()
    /**
     * 更细播放状态
     */
    fun updatePlayState()

    /**
     * 获取播放状态
     */
    fun isPlaying(): Boolean?
}