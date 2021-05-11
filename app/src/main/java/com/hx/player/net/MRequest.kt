package com.hx.player.net

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType

/**
 * Desc 请求的基类
 *
 * @author zhangxiaolin
 * Date 2021/5/10
 * Copyright © 川大智胜
 */
open class MRequest<RESPONSE>(val url: String, val handler: ResponseHandler<RESPONSE>) {
    fun parseResult(string: String?): RESPONSE {
        //获取泛型类型
        val type = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        return Gson().fromJson(string, type)
    }

    /**
     * 发送网络请求
     */
    fun execute() {
        NetManager.netManager.sendRequest(this)
    }

}