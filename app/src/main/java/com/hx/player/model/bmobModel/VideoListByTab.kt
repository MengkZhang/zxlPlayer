package com.hx.player.model.bmobModel

import cn.bmob.v3.BmobObject

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/13
 * Copyright © 川大智胜
 */
class VideoListByTab(
    var itemId: String,
    var playUrl: String?,
    var descriptionPgc: String?,
    var detail: String?
) :
    BmobObject()