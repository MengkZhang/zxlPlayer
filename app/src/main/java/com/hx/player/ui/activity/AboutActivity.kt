package com.hx.player.ui.activity

import android.util.Log
import androidx.appcompat.widget.Toolbar
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.SaveListener
import com.hx.player.R
import com.hx.player.base.BaseActivity
import com.hx.player.model.bmobModel.*
import com.hx.player.net.ResponseHandler
import com.hx.player.net.TestRequest
import com.hx.player.net.TestVideoListByTabRequest
import com.hx.player.utils.ToolBarManager
import kotlinx.android.synthetic.main.activity_about.*
import org.jetbrains.anko.find

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
        getTestMvTabEvent()
        getTestVideoListEvent()
        getMusic()
    }

    private fun getMusic() {

        //MusicList
        btnGetMusicList.setOnClickListener {
            val bmobQuery = BmobQuery<MusicList>()

            bmobQuery.findObjects(object : FindListener<MusicList>() {
                override fun done(list: MutableList<MusicList>?, e: BmobException?) {
                    if (e == null) {
                        list?.let {
                            Log.e("===z ", "size=" + list.size)
                            Log.e("===z ", "title=" + list[0].title)
                            Log.e("===z ", "audio=" + list[0].audio)


//                            it.forEach { musicList ->
//                                val audio = musicList.audio
//                                Log.e("===z ", "audio=$audio")
//                                Log.e("===z ", "title=$musicList.title")
//
//                            }
                        }

                    } else {
                        val message = e.message
                        message?.let {
                            myToast(message)
                        }
                    }

                }
            })
        }
    }

    private fun getTestVideoListEvent() {
        btnGetTestVideoList.setOnClickListener {
            //查询到Tab数据
            val bmobQuery = BmobQuery<VideoTab>()

            bmobQuery.findObjects(object : FindListener<VideoTab>() {
                override fun done(list: MutableList<VideoTab>?, e: BmobException?) {
                    if (e == null) {
                        list?.let {
                            Log.e("===z ", "size=" + list.size)
                            list.forEach {
                                Log.e("===z", "tile=${it.itemId}")
                                getVideoListByTable(it.itemId)
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
        }

    }

    private fun getVideoListByTable(itemId: String) {
        TestVideoListByTabRequest(itemId, object : ResponseHandler<TestVideoListByTabBean> {
            override fun onError(msg: String?) {
                msg?.let {
                    myToast(msg)
                }
            }

            override fun onSuccess(result: TestVideoListByTabBean?) {
                result?.let {
                    Log.e("===z", "-----------------")
                    Log.e("===z", it.result?.size.toString())
                    //把查到的野生数据插入到bmob数据表中
                    it.result?.forEach {
                        val data = it.data
                        val videoTab = VideoListByTab(
                            itemId,
                            data.playUrl,
                            data.descriptionPgc,
                            data.cover?.detail
                        )
                        videoTab.save(object : SaveListener<String>() {
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

            }
        }).executed()

    }

    private fun getTestMvTabEvent() {
        btnGetTestMvTab.setOnClickListener {
            getTestMvTab()
        }
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

    private fun getTestMvTab() {
        TestRequest(object : ResponseHandler<TestTable> {
            override fun onError(msg: String?) {
                msg?.let {
                    myToast(msg)
                }
            }

            override fun onSuccess(result: TestTable?) {
                myToast(result?.result?.itemList?.size.toString())
                //把获取的数据插入到bmob数据库中
                insertIntoVideoTab(result?.result?.itemList)


            }
        }).executed()
    }

    private fun insertIntoVideoTab(data: List<TestTable.Item>?) {
        data?.let {
            //0~15
            for (index in 0 until data.size) {
                Log.e("===z", "data index=$index")
                var videoTab: VideoTab
                when {
                    index < 10 -> videoTab = VideoTab("12738$index", data[index].data?.title)
                    index == 10 -> videoTab = VideoTab("127390", data[index].data?.title)
                    else -> videoTab = VideoTab("1273$index", data[index].data?.title)
                }
                videoTab.save(object : SaveListener<String>() {
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

    }

}