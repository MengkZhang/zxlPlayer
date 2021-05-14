package com.hx.player.ui.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.view.View
import androidx.lifecycle.Observer
import com.hx.player.R
import com.hx.player.base.BaseActivity
import com.hx.player.config.EventBusConstant
import com.hx.player.model.AudioBean
import com.hx.player.service.AudioService
import com.hx.player.service.IService
import com.jeremyliao.liveeventbus.LiveEventBus
import kotlinx.android.synthetic.main.activity_music_player_bottom.*
import kotlinx.android.synthetic.main.activity_music_player_top.*

/**
 * 音乐播放页
 */
class AudioPlayActivity : BaseActivity(), View.OnClickListener {


    private val connection by lazy { AudioConnection() }
    private var iService: IService? = null
    private var audioBean: AudioBean? = null

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


    }

    override fun initListener() {
        super.initListener()
        state.setOnClickListener(this)
        initEventBus()
    }

    private fun initEventBus() {
        //接收事件 更新UI
        LiveEventBus
            .get(EventBusConstant.NOTIFY_CHANGE_UI, AudioBean::class.java)
            .observe(this, Observer<AudioBean> { audioBean -> updateSongInfo(audioBean) })

    }

    private fun updateSongInfo(audioBean: AudioBean?) {
        this.audioBean = audioBean
        //更新歌曲名
        audio_title.text = audioBean?.display_name
        artist.text = audioBean?.artist

    }

    inner class AudioConnection : ServiceConnection {
        /**
         * 服务意外断开连接
         */
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        /**
         * 服务连接成功
         */
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            iService = service as IService
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.state -> updatePlayState()
        }

    }

    /**
     * 更新播放状态
     */
    private fun updatePlayState() {
        //改变播放状态
        iService?.updatePlayState()

        //改变图标
        updatePlayStateButton()

    }

    /**
     * 更新图标
     */
    private fun updatePlayStateButton() {
        //获取当前播放状态 这个是点击后的状态
        val isPlaying = iService?.isPlaying()
        //根据状态更新图标
        isPlaying?.let {
            if (it) {
                //正在播放 展示播放的按钮
                state.setImageResource(R.drawable.selector_btn_audio_play)
            } else {
                //正在暂停 展示暂停的按钮
                state.setImageResource(R.drawable.selector_btn_audio_pause)
            }
        }
    }

}
