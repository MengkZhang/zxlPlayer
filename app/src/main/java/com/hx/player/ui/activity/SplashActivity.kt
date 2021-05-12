package com.hx.player.ui.activity

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import com.hx.player.R
import com.hx.player.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity(), ViewPropertyAnimatorListener {

    override fun onAnimationEnd(view: View?) {
        startActivityAndFinish<MainActivity>()
    }

    override fun onAnimationCancel(view: View?) {
    }

    override fun onAnimationStart(view: View?) {
    }

    override fun getLayoutView(): Int {
        return R.layout.activity_splash
    }


    override fun initData() {
        super.initData()
        ViewCompat.animate(imageView).scaleX(1.0f).scaleY(1.0f).setListener(this).duration = 2000
    }


}
