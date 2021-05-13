package com.hx.player.net

import com.hx.player.model.bmobModel.TestTable
import com.hx.player.model.bmobModel.TestVideoListByTabBean
import com.hx.player.utils.URLProviderUtils

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/10
 * Copyright © 川大智胜
 */
class TestRequest(handler: ResponseHandler<TestTable>) :
    MRequest<TestTable>(URLProviderUtils.getTestMvTab(), handler)

class TestVideoListByTabRequest(var id: String, handler: ResponseHandler<TestVideoListByTabBean>) :
    MRequest<TestVideoListByTabBean>(URLProviderUtils.getTestMvListByTab(id), handler)