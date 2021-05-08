package com.hx.player.utils

import android.content.Intent
import androidx.appcompat.widget.Toolbar
import com.hx.player.R
import com.hx.player.ui.activity.SettingActivity

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/8
 * Copyright © 川大智胜
 */
interface ToolBarManager {
    val toolbar: Toolbar

    /**
     * 接口中的方法可以实现
     */
    fun initMainToolBar() {
        toolbar.title = "影音软件"
        toolbar.inflateMenu(R.menu.main)

        //Kotlin的写法 如果Java接口中只有一个未实现的方法  可以省略接口对象 直接用{}来表示这个方法
        toolbar.setOnMenuItemClickListener {
            when (it?.itemId) {
                R.id.setting -> {
                    toolbar.context.startActivity(
                        Intent(
                            toolbar.context,
                            SettingActivity::class.java
                        )
                    )
                }
            }
            true
        }

        //Java的写法
//        toolbar.setOnMenuItemClickListener(object : Toolbar.OnMenuItemClickListener {
//            override fun onMenuItemClick(item: MenuItem?): Boolean {
//                when (item?.itemId) {
//                    R.id.setting -> {
//                        Toast.makeText(toolbar.context, "set", Toast.LENGTH_LONG).show()
//                        toolbar.context.startActivity(
//                            Intent(
//                                toolbar.context,
//                                SettingActivity::class.java
//                            )
//                        )
//                    }
//                }
//                return true
//            }
//        })
    }


    fun initSettingToolBar() {
        toolbar.title = "设置"
    }

}