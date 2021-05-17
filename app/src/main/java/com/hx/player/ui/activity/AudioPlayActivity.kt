package com.hx.player.ui.activity

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.drawable.AnimationDrawable
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.view.View
import android.widget.SeekBar
import androidx.lifecycle.Observer
import com.hx.player.R
import com.hx.player.base.BaseActivity
import com.hx.player.config.EventBusConstant
import com.hx.player.model.AudioBean
import com.hx.player.service.AudioService
import com.hx.player.service.IService
import com.hx.player.utils.StringUtil
import com.jeremyliao.liveeventbus.LiveEventBus
import kotlinx.android.synthetic.main.activity_music_player_bottom.*
import kotlinx.android.synthetic.main.activity_music_player_middle.*
import kotlinx.android.synthetic.main.activity_music_player_top.*


/**
 * 音乐播放页
 */
class AudioPlayActivity : BaseActivity(), View.OnClickListener, SeekBar.OnSeekBarChangeListener {


    private val connection by lazy { AudioConnection() }
    private val anim by lazy { audio_anim.drawable as AnimationDrawable }
    private var iService: IService? = null
    private var audioBean: AudioBean? = null
    private var duration: Int = 0
    private val MSG_PROGRESS = 0

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
        bindService(sIntent, connection, Context.BIND_AUTO_CREATE)
        startService(sIntent)

    }

    override fun initListener() {
        super.initListener()
        state.setOnClickListener(this)
        back.setOnClickListener(this)
        next.setOnClickListener(this)
        pre.setOnClickListener(this)
        progress_sk.setOnSeekBarChangeListener(this)
        initEventBus()
    }

    private val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == MSG_PROGRESS) {
                startUpdateProgress()
            }
        }
    }

    private fun initEventBus() {
        //接收事件 更新UI
        LiveEventBus
            .get(EventBusConstant.NOTIFY_CHANGE_UI, AudioBean::class.java)
            .observe(this, Observer<AudioBean> { audioBean -> updateSongInfo(audioBean) })

    }

    private fun updateSongInfo(audioBean: AudioBean?) {
        this.audioBean = audioBean
        //更新歌曲信息
        audio_title.text = audioBean?.display_name
        artist.text = audioBean?.artist
        //跟新播放按钮状态
        updatePlayStateButton()
        //更新帧动画
        anim.start()
        //获取总进度
        duration = iService?.getDuration() ?: 0
        //设置最大进度
        progress_sk?.max = duration
        //更新播放进度
        startUpdateProgress()

    }

    /**
     * 更新播放进度
     */
    private fun startUpdateProgress() {
        val progress = iService?.getProgress() ?: 0
        //更新进度数据
        updateProgress(progress)
        //定时发送获取进度
        handler.sendEmptyMessage(MSG_PROGRESS)

    }

    /**
     * 更新进度
     */
    private fun updateProgress(pro: Int) {
        //更新进度数值
        val current = StringUtil.parseDuration(pro)
        val total = StringUtil.parseDuration(duration)
        progress.text = "$current/$total"
        //更新进度条
        progress_sk?.progress = pro
        //定时获取播放进度

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
        handler.removeCallbacksAndMessages(null)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.state -> updatePlayState()
            R.id.back -> finish()
            R.id.next -> iService?.playNext()
            R.id.pre -> iService?.playPre()
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
        //获取当前播放状态 这个是点击后的状态 执行了上一个方法后已经改变了播放状态
        val isPlaying = iService?.isPlaying()
        //根据状态更新图标
        isPlaying?.let {
            if (it) {
                //正在播放 展示播放的按钮
                state.setImageResource(R.drawable.selector_btn_audio_play)
                anim.start()
                handler.sendEmptyMessage(MSG_PROGRESS)
            } else {
                //正在暂停 展示暂停的按钮
                state.setImageResource(R.drawable.selector_btn_audio_pause)
                anim.stop()
                handler.removeMessages(MSG_PROGRESS)
            }
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (!fromUser) {
            return
        }

        //更新播放进度
        iService?.seekTo(progress)
        //更新界面进度
        updateProgress(progress)

    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }


}
