package com.hx.player.net

import com.hx.player.utils.ThreadUtil
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

/**
 * Desc 单例 发送网络请求
 *
 * @author zhangxiaolin
 * Date 2021/5/10
 * Copyright © 川大智胜
 */
class NetManager private constructor() {

    private val client by lazy {
        OkHttpClient()
    }

    companion object {
        val netManager by lazy {
            NetManager()
        }
    }

    /**
     * 定义函数的泛型
     */
    fun <RESPONSE> sendRequest(req: MRequest<RESPONSE>) {
        val path = req.url
        val request = Request.Builder()
            .url(path)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            /**
             * onFailure 在子线程中执行
             */
            override fun onFailure(call: Call, e: IOException) {
                ThreadUtil.runOnMainThread(Runnable {
                    //回调
                    req.handler.onError(e.message)
                })

            }

            /**
             * onResponse 在子线程中执行
             */
            override fun onResponse(call: Call, response: Response) {

                val result = response.body()?.string()

                result?.let {
                    val obj = JSONObject(result)
                    val code = obj.getString("code")
                    if ("200" == code) {
                        val parseResult = req.parseResult(result)

                        ThreadUtil.runOnMainThread(Runnable {
                            req.handler.onSuccess(parseResult)
                        })
                    } else {
                        ThreadUtil.runOnMainThread(Runnable {
                            req.handler.onError(obj.getString("msg"))
                        })
                    }

                }


            }
        })
    }
}