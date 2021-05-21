package com.hx.player.model.bmobModel

import cn.bmob.v3.BmobObject

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/11
 * Copyright © 川大智胜
 */
class MusicList(
    var typeId: String?,
    var size: String?,
    var author: String?,
    var title: String?,
    var Lyric: String?,
    var audio: String?
) :
    BmobObject()