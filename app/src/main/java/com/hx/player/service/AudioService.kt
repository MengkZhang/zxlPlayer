package com.hx.player.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.hx.player.config.EventBusConstant
import com.hx.player.model.AudioBean
import com.jeremyliao.liveeventbus.LiveEventBus

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/14
 * Copyright © 川大智胜
 */
class AudioService : Service() {


    private var list: ArrayList<AudioBean>? = null
    private var position: Int = -1
    private var mediaPlayer: MediaPlayer? = null
    private val audioBinder by lazy { AudioBinder() }

    override fun onCreate() {
        super.onCreate()
    }

    /**
     * onStartCommand
     *
     *START_STICKY   粘性的  service强制杀死之后 会尝试重新启动service 不会传递原来的intent(null)
     *START_NOT_STICKY 非粘性的 service强制杀死之后 不会尝试重新启动service
     *START_REDELIVER_INTENT service强制杀死之后 会尝试重新启动service  会传递原来的intent
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        list = intent?.getParcelableArrayListExtra("list")
        position = intent?.getIntExtra("position", -1) ?: -1
        //开始播放
        audioBinder.playItem()
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return audioBinder
    }

    inner class AudioBinder : Binder(), IService, MediaPlayer.OnPreparedListener {

        /**
         * 获取总进度
         */
        override fun getDuration(): Int {
            return mediaPlayer?.duration ?: 0
        }

        /**
         * 获取当前进度
         */
        override fun getProgress(): Int {
            return mediaPlayer?.currentPosition ?: 0
        }

        override fun updatePlayState() {
            //获取当前播放状态
            val isPlay = isPlaying()
            //切换播放状态
            isPlay?.let {
                if (it) {
                    //正在播放 暂停
                    mediaPlayer?.pause()
                } else {
                    //正在暂停 播放
                    mediaPlayer?.start()
                }
            }

        }

        override fun isPlaying(): Boolean? {
            return mediaPlayer?.isPlaying
        }

        override fun onPrepared(mp: MediaPlayer?) {
            //播放音乐
            mediaPlayer?.start()
            //通知界面更新
            notifyChangeUI(list?.get(position))
        }

        override fun playItem() {
            //重置media 解决叠加播放的bug
            if (mediaPlayer != null) {
                mediaPlayer?.reset()
                mediaPlayer?.release()
                mediaPlayer = null
            }
            mediaPlayer = MediaPlayer()
            mediaPlayer?.let {
                it.setOnPreparedListener(this)
                it.setDataSource(list?.get(position)?.data)
                it.prepareAsync()
            }
        }

        /**
         * 通知界面更新UI
         */
        private fun notifyChangeUI(value: AudioBean?) {
            LiveEventBus.get(EventBusConstant.NOTIFY_CHANGE_UI).post(value)
        }

    }
}