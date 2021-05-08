package com.hx.player.utils

import androidx.fragment.app.Fragment
import com.hx.player.R
import com.hx.player.ui.fragment.HomeFragment
import com.hx.player.ui.fragment.MvFragment
import com.hx.player.ui.fragment.VBangFragment
import com.hx.player.ui.fragment.YueDanFragment

/**
 * Desc 单例
 *
 * @author zhangxiaolin
 * Date 2021/5/8
 * Copyright © 川大智胜
 */
class FragmentUtil private constructor() {
    //私有化构造方法

    val homeFragment by lazy { HomeFragment() }
    val mvFragment by lazy { MvFragment() }
    val vBangFragment by lazy { VBangFragment() }
    val yueDanFragment by lazy { YueDanFragment() }

    companion object {
        val fragmentUtil by lazy { FragmentUtil() }
    }

    fun getFragment(tabId: Int): Fragment {
        when (tabId) {
            R.id.tab_home -> return homeFragment
            R.id.tab_mv -> return mvFragment
            R.id.tab_vbang -> return vBangFragment
            R.id.tab_yuedan -> return yueDanFragment
        }
        return homeFragment
    }


}