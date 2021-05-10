package com.hx.player.model

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/10
 * Copyright © 川大智胜
 */
data class YueDanBean(
    val author: Author,
    val code: Int,
    val `data`: List<Data>,
    val msg: String
) {

    data class Author(
        val desc: String,
        val name: String
    )

    data class Data(
        val ctime: String,
        val picUrl: String,
        val source: String,
        val title: String,
        val url: String
    )

}
