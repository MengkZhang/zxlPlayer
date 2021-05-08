package com.hx.player.ui.activity

import androidx.appcompat.widget.Toolbar
import com.hx.player.R
import com.hx.player.base.BaseActivity
import com.hx.player.utils.ToolBarManager
import org.jetbrains.anko.find

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/8
 * Copyright © 川大智胜
 */
class AboutActivity : BaseActivity(), ToolBarManager {
    override val toolbar by lazy {
        find<Toolbar>(R.id.toolbar)
    }

    override fun getLayoutView(): Int {
        return R.layout.activity_about
    }

    override fun initData() {
        super.initData()
        toolbar.title = "关于"
    }

}