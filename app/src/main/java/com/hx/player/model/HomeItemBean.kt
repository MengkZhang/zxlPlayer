package com.hx.player.model

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/8
 * Copyright © 川大智胜
 */

data class HomeItemBean(
    val author: Author,
    val code: Int,
    val `data`: List<Data>,
    val msg: String
)

data class Author(
    val desc: String,
    val name: String
)

data class Data(
    val digest: String,
    val docid: String,
    val imgsrc: String,
    val m_url: String,
    val pc_url: String,
    val source: String,
    val time: String,
    val title: String
)