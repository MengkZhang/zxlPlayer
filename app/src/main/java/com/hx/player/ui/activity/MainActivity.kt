package com.hx.player.ui.activity

import androidx.appcompat.widget.Toolbar
import com.hx.player.R
import com.hx.player.base.BaseActivity
import com.hx.player.utils.FragmentUtil
import com.hx.player.utils.ToolBarManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find

/**
 * Desc 主页面
 *
 * @author zhangxiaolin
 * Date 2021/5/8
 * Copyright © 川大智胜
 */
class MainActivity : BaseActivity(), ToolBarManager {

    /**
     * 初始化toolBar
     * 惰性加载
     * by lazy 用的时候再初始化 find<Toolbar>(R.id.toolbar)相当于findViewById
     */
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun getLayoutView(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        super.initData()
        initMainToolBar()
    }

    override fun initListener() {
        super.initListener()
        bottomBar.setOnTabSelectListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(
                R.id.container,
                FragmentUtil.fragmentUtil.getFragment(it),
                it.toString()
            )
            transaction.commit()
        }
    }

}