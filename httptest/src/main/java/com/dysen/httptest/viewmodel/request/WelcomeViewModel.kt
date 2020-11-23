package com.dysen.mehttp.viewmodel.request

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.dysen.mehttp.data.model.ApiPagerResponse
import com.dysen.mehttp.data.model.AriticleResponse
import com.dysen.mehttp.data.repository.request.HttpRequestManger
import com.dysen.mehttp.base.viewmodel.BaseViewModel
import com.dysen.mehttp.ext.request
import com.dysen.mehttp.state.ResultState

/**
 * dysen.
 * dy.sen@qq.com    10/20/20  11:36 AM

 * Info：
 */
class WelcomeViewModel(app:Application):BaseViewModel(app) {

    var welcomeResult = MutableLiveData<ResultState<ApiPagerResponse<ArrayList<AriticleResponse>>>>()

    fun getData(pageInt: Int) {
        request(
            { HttpRequestManger.instance.getData(pageInt) }
            , welcomeResult,
            true,
            "正在获取最新项目数据..."
        )
    }
}