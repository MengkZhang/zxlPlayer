package com.hx.player.ui.fragment

import android.Manifest
import android.content.AsyncQueryHandler
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.database.Cursor
import android.provider.MediaStore
import android.view.View
import androidx.core.app.ActivityCompat
import com.hx.player.R
import com.hx.player.adapter.VbangAdapter
import com.hx.player.base.BaseActivity
import com.hx.player.base.BaseFragment
import com.hx.player.model.AudioBean
import com.hx.player.ui.activity.AudioPlayActivity
import com.hx.player.utils.CursorUtil
import kotlinx.android.synthetic.main.fragment_vbang.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.yesButton

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/8
 * Copyright © 川大智胜
 */
class MusicLocalFragment : BaseFragment() {
    private var adapter: VbangAdapter? = null

    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_vbang, null)
    }

    override fun initData() {
        super.initData()
        initPermission()
    }

    override fun initListener() {
        super.initListener()
        adapter = VbangAdapter(context, null)
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            val cursor = adapter?.getItem(position) as Cursor
            val list: ArrayList<AudioBean> = AudioBean.getAudioBeans(cursor)
            (context as BaseActivity).startActivity<AudioPlayActivity>(
                "item" to list,
                "position" to position
            )
        }
    }

    /**
     * 检查动态权限
     */
    private fun initPermission() {
        val permission = Manifest.permission.READ_EXTERNAL_STORAGE
        val checkSelfPermission =
            ActivityCompat.checkSelfPermission(requireContext(), permission)
        if (checkSelfPermission == PackageManager.PERMISSION_GRANTED) {
            //已有权限
            //获取本地文件
            getLocalSongs()
        } else {
            //没有权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    (context as BaseActivity),
                    permission
                )
            ) {
                //需要弹出
                (context as BaseActivity).alert("改权限只访问音乐数据不涉及个人数据", "友情提示") {
                    yesButton { requestPermission() }
                }

            } else {
                //不需要弹出 获取权限
                requestPermission()
            }
        }


    }

    /**
     * 请求权限
     */
    private fun requestPermission() {
        val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        requestPermissions(permission, 1)
    }

    /**
     * 动态权限回调
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //读取本地文件
            getLocalSongs()
        }
    }

    /**
     * 获取本地音乐文件
     */
    private fun getLocalSongs() {
        val contentResolver = context?.contentResolver
        val handler = SongHandler(contentResolver)
        handler.startQuery(
            0, adapter, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.SIZE,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.ARTIST
            ), null, null, null
        )

    }

    companion object
    class SongHandler(cr: ContentResolver?) : AsyncQueryHandler(cr) {
        override fun onQueryComplete(token: Int, cookie: Any?, cursor: Cursor?) {
            super.onQueryComplete(token, cookie, cursor)
            CursorUtil.logCursor(cursor)
            (cookie as VbangAdapter).swapCursor(cursor)
        }
    }


}