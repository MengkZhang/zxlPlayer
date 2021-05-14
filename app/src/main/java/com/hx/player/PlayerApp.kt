package com.hx.player

import android.app.Application
import cn.bmob.v3.Bmob
import com.hx.player.config.AppConfig
import com.jeremyliao.liveeventbus.LiveEventBus

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
        initEventBus()
    }

    private fun initEventBus() {
        LiveEventBus
            .config()
            .autoClear(true)
            .lifecycleObserverAlwaysActive(true)
    }

    private fun initBomb() {
        Bmob.initialize(this, AppConfig.bmobApplicationId)
    }
}