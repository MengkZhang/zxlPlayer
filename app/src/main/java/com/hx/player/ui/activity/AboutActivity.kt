package com.hx.player.ui.activity

import android.util.Log
import androidx.appcompat.widget.Toolbar
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.QueryListListener
import cn.bmob.v3.listener.QueryListener
import cn.bmob.v3.listener.SaveListener
import com.hx.player.R
import com.hx.player.base.BaseActivity
import com.hx.player.config.AppConfig
import com.hx.player.model.bmobModel.Home
import com.hx.player.utils.ToolBarManager
import kotlinx.android.synthetic.main.activity_about.*
import org.jetbrains.anko.find
import android.util.Log.e as e1

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/8
 * Copyright © 川大智胜
 */
class AboutActivity : BaseActivity(), ToolBarManager {
    override val toolbar by lazy {
        find<Toolbar>(R.id.toolbar)
    }

    override fun getLayoutView(): Int {
        return R.layout.activity_about
    }

    override fun initData() {
        super.initData()
        toolbar.title = "关于"
    }

    override fun initListener() {
        super.initListener()
        findData()
        addData()
    }

    private fun addData() {
        btnAddData.setOnClickListener {
            val title = "hanshfsjaf"
            val desc = "skjafosd"
            val url = "http://k.sina.com.cn/article_6837948944_19792d21000100v6a5.html"
            val img =
                "http://n.sinaimg.cn/sinakd20210302ac/4/w480h324/20210302/1057-kksmnwv2434756.jpg"
            val home = Home(url, desc, title, img)
            home.save(object : SaveListener<String>() {
                override fun done(objectId: String?, e: BmobException?) {
                    if (e == null) {
                        myToast("添加数据成功id=$objectId")
                    } else {
                        myToast("添加数据失败：e=${e.message}")
                    }
                }
            })
        }

    }

    private fun findData() {
        btnFindData.setOnClickListener {
            val bmobQuery = BmobQuery<Home>()

            bmobQuery.findObjects(object : FindListener<Home>() {
                override fun done(list: MutableList<Home>?, e: BmobException?) {
                    if (e == null) {
                        list?.let {
                            Log.e("===z ", "size=" + list.size)
                            list.forEach {
                                Log.e("===z", "tile=${it.title}")
                            }
                        }

                    } else {
                        val message = e.message
                        message?.let {
                            myToast(message)
                        }
                    }

                }
            })

//            bmobQuery.getObject("5IwGLLLf", object : QueryListener<Home>() {
//                override fun done(data: Home?, p1: BmobException?) {
//                    if (p1 == null) {
//                        myToast(data.toString())
//                    } else {
//                        val message = p1.message
//                        message?.let {
//                            myToast(message)
//                        }
//                    }
//                }
//            })
        }
    }

}