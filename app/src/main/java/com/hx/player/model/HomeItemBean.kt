package com.hx.player.model

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/8
 * Copyright © 川大智胜
 */

data class HomeItemBean(
    val code: Int,
    val `data`: Data
)

data class Data(
    val artistsname: String,
    val name: String,
    val picurl: String,
    val url: String
)