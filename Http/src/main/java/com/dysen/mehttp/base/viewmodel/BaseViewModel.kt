package com.dysen.mehttp.base.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.dysen.mehttp.state.SingleLiveEvent

/**
 * dysen.
 * dy.sen@qq.com    9/16/20  11:11 AM

 * Info： ViewModel的基类
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    val loadingChange: UiLoadingChange by lazy { UiLoadingChange() }

    /**
     * 内置封装好的可通知Activity/fragment 显示隐藏加载框 因为需要跟网络请求显示隐藏loading配套才加的，不然我加他个鸡儿加
     */
    inner class UiLoadingChange {
        //显示加载框
        val showDialog by lazy { SingleLiveEvent<String>() }
        //隐藏
        val dismissDialog by lazy { SingleLiveEvent<Void>() }
    }

}