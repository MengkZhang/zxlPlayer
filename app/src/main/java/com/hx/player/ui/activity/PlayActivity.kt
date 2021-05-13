package com.hx.player.ui.activity

import cn.jzvd.JZVideoPlayer
import com.hx.player.R
import com.hx.player.base.BaseActivity
import com.hx.player.model.bmobModel.VideoListByTab
import cn.jzvd.JZVideoPlayerStandard
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_video_player_jiecao.*


class PlayActivity : BaseActivity() {

    override fun getLayoutView(): Int {
        return R.layout.activity_video_player_jiecao
    }

    override fun initData() {
        super.initData()
        val data = intent.getParcelableExtra<VideoListByTab>("item")
        videoplayer.setUp(
            data?.playUrl
            , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,
            data?.descriptionPgc
        )
        Picasso.with(this).load(data?.detail).into(videoplayer.thumbImageView)
        videoplayer.startVideo()

    }

    override fun onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        JZVideoPlayer.releaseAllVideos()
    }

}
