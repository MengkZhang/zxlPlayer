package com.hx.player.utils

import android.os.Handler
import android.os.Looper

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/8
 * Copyright © 川大智胜
 */
object ThreadUtil {

    val handler = Handler(Looper.getMainLooper())

    fun runOnMainThread(runnable: Runnable) {
        handler.post(runnable)
    }
}