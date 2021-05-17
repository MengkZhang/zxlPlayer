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

    /**
     * 获取总进度
     */
    fun getDuration(): Int?

    /**
     * 获取当前进度
     */
    fun getProgress(): Int?

    /**
     * 手机拖动进度
     */
    fun seekTo(progress: Int)

    /**
     * 播放下一曲
     */
    fun playNext()

    /**
     * 播放上一曲
     */
    fun playPre()

    /**
     * 更新播放模式
     */
    fun updatePlayMode()

    /**
     * 获取播放模式
     */
    fun getPlayMode(): Int


}