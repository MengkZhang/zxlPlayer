package com.hx.player.adapter

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.hx.player.model.bmobModel.VideoTab
import com.hx.player.ui.fragment.MvPageFragment

class MvPageAdapter(
    val context: Context?,
    val data: List<VideoTab>?,
    childFragmentManager: FragmentManager
) : FragmentPagerAdapter(childFragmentManager) {
    override fun getItem(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putString("id", data?.get(position)?.itemId)
        return Fragment.instantiate(context, MvPageFragment::class.java.name, bundle)
    }

    override fun getCount(): Int {
        return data?.size ?: 0
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return data?.get(position)?.title
    }


}
