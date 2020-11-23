package com.dysen.mehttp.viewmodel.request

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.dysen.mehttp.data.model.ApiPagerResponse
import com.dysen.mehttp.data.model.AriticleResponse
import com.dysen.mehttp.data.repository.request.HttpRequestManger
import com.dysen.mehttp.base.viewmodel.BaseViewModel
import com.dysen.mehttp.data.model.DataRes
import com.dysen.mehttp.ext.request
import com.dysen.mehttp.state.ResultState

/**
 * dysen.
 * dy.sen@qq.com    10/20/20  11:36 AM

 * Info：
 */
class WelcomeViewModel(app:Application):BaseViewModel(app) {

    var welcomeResult = MutableLiveData<ResultState<ApiPagerResponse<MutableList<DataRes>>>>()

    fun getData(pageInt: Int) {
        request(
            { HttpRequestManger.instance.getData(pageInt) }
            , welcomeResult,
            true,
            "正在获取最新项目数据..."
        )
    }
    fun getData2(pageInt: Int) {
        request(
            { HttpRequestManger.instance.getData(pageInt) },
            {
                //请求成功 已自动处理了 请求结果是否正常
                println("pageCount=${it.pageCount }")
            },
            {
                //请求失败 网络异常，或者请求结果码错误都会回调在这里
            }
        )
    }
}