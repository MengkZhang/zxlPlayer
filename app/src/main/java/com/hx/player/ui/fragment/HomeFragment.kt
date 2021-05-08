package com.hx.player.ui.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.hx.player.R
import com.hx.player.adapter.HomeAdapter
import com.hx.player.base.BaseFragment
import com.hx.player.model.Data
import com.hx.player.model.HomeItemBean
import com.hx.player.utils.ThreadUtil
import com.hx.player.utils.URLProviderUtils
import kotlinx.android.synthetic.main.fragment_list.*
import okhttp3.*
import java.io.IOException
import java.util.*


/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/8
 * Copyright © 川大智胜
 */
class HomeFragment : BaseFragment() {

    private val homeAdapter by lazy { HomeAdapter() }

    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_list, null)
    }

    override fun initListener() {
        super.initListener()
        recycleView.layoutManager = LinearLayoutManager(context)
        val adapter = homeAdapter
        recycleView.adapter = adapter
    }

    override fun initData() {
        super.initData()
        loadData()
    }

    private fun loadData() {
        val path = URLProviderUtils.getHomeUrl(1)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(path)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            /**
             * onFailure 在子线程中执行
             */
            override fun onFailure(call: Call, e: IOException) {
                myToast("失败")
            }

            /**
             * onResponse 在子线程中执行
             */
            override fun onResponse(call: Call, response: Response) {
                val bean: HomeItemBean =
                    Gson().fromJson(response.body()?.string(), HomeItemBean::class.java)

                ThreadUtil.runOnMainThread(Runnable { homeAdapter.updateData(bean.data) })

            }
        })


    }


}