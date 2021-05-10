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
class MRequest<RESPONSE>(val url: String, val handler: ResponsHandler<RESPONSE>) {
    fun parseResult(string: String?): RESPONSE {
        //获取泛型类型
        val type = (this.javaClass.genericInterfaces as ParameterizedType).actualTypeArguments[0]
        return Gson().fromJson(string, type)
    }

}