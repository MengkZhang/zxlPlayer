package com.hx.player.ui.fragment

import android.view.View
import androidx.fragment.app.Fragment
import com.hx.player.R
import com.hx.player.adapter.VbPageAdapter
import com.hx.player.base.BaseFragment
import com.hx.player.model.bmobModel.VideoTab
import kotlinx.android.synthetic.main.fragment_mv.*

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/8
 * Copyright © 川大智胜
 */
class VBangFragment : BaseFragment() {


    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_mv, null)
    }

    override fun initData() {
        super.initData()
        val data = ArrayList<VideoTab>()
        data.add(VideoTab("1", "抖音热门"))
        data.add(VideoTab("2", "本地"))
        val fragments = ArrayList<Fragment>()
        fragments.add(MusicNetFragment())
        fragments.add(MusicLocalFragment())
        viewPager?.adapter = VbPageAdapter(context, data, fragments, childFragmentManager)
        tabLayout?.setupWithViewPager(viewPager)
    }


}