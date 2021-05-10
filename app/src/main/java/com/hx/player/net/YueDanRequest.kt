package com.hx.player.net

import com.hx.player.model.YueDanBean
import com.hx.player.utils.URLProviderUtils

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/10
 * Copyright © 川大智胜
 */
class YueDanRequest(offset: Int, handler: ResponseHandler<YueDanBean>) :
    MRequest<YueDanBean>(URLProviderUtils.getYueDanUrl(offset), handler)