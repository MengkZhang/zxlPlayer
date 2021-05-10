package com.hx.player.net

import com.hx.player.model.HomeItemBean
import com.hx.player.utils.URLProviderUtils

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/10
 * Copyright © 川大智胜
 */
class HomeRequest(offset: Int, handler: ResponseHandler<HomeItemBean>) :
    MRequest<HomeItemBean>(URLProviderUtils.getHomeUrl(offset), handler)