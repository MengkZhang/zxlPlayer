package com.hx.player

import android.app.Application
import cn.bmob.v3.Bmob
import com.hx.player.config.AppConfig

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/11
 * Copyright © 川大智胜
 */
class PlayerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initBomb()
    }

    private fun initBomb() {
        Bmob.initialize(this, AppConfig.bmobApplicationId)
    }
}