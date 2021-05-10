package com.hx.player.net

/**
 * Desc 请求的回调
 * RESPONCE泛型
 *
 * @author zhangxiaolin
 * Date 2021/5/10
 * Copyright © 川大智胜
 */
interface ResponsHandler<RESPONSE> {
    fun onError(msg: String?)
    fun onSuccess(result: RESPONSE)
}