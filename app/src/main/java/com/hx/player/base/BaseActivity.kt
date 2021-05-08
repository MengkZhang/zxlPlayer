package com.hx.player.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/7
 * Copyright © 川大智胜
 */
abstract class BaseActivity : AppCompatActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutView())
        initData()
        initListener()

    }

    /**
     * 加open才能被复写
     */
    open protected fun initListener() {

    }

    open protected fun initData() {

    }

    abstract fun getLayoutView(): Int

    protected fun myToast(smg: String) {
        runOnUiThread {
            toast(smg)
        }
    }

    /**
     * 开启页面 并关闭当前
     * inline 内联关键字
     */
    inline fun <reified T: BaseActivity> startActivityAndFinish() {
        startActivity<T>()
        finish()
    }
}