package com.hx.player.ui.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.os.IBinder
import com.hx.player.R
import com.hx.player.base.BaseActivity
import com.hx.player.model.AudioBean
import com.hx.player.service.AudioService

/**
 * 音乐播放页
 */
class AudioPlayActivity : BaseActivity() {
    private val connection by lazy { AudioConnection() }

    override fun getLayoutView(): Int {
        return R.layout.activity_audio_player
    }

    override fun initData() {
        super.initData()
        val list = intent.getParcelableArrayListExtra<AudioBean>("item")
        val position = intent.getIntExtra("position", -1)
        val sIntent = Intent(this, AudioService::class.java)
        sIntent.putExtra("list", list)
        sIntent.putExtra("position", position)
        //开启服务 绑定服务
        startService(sIntent)
        bindService(sIntent, connection, Context.BIND_AUTO_CREATE)

//        val mediaPlayer = MediaPlayer()
//        mediaPlayer.setOnPreparedListener {
//            mediaPlayer.start()
//        }
//        mediaPlayer.setDataSource(list?.get(position)?.data)
//        mediaPlayer.prepareAsync()

    }

    class AudioConnection : ServiceConnection {
        /**
         * 服务意外断开连接
         */
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        /**
         * 服务连接成功
         */
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {

        }
    }

}
