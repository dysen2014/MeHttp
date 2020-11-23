package com.dysen.mehttp.ext.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.dysen.mehttp.callback.livedata.UnPeekNotNullLiveData

/**
 * dysen.
 * dy.sen@qq.com    9/16/20  11:11 AM

 * Info：
 */
object KtxAppLifeObserver : LifecycleObserver {

    var isForeground =
        UnPeekNotNullLiveData<Boolean>()

    //在前台
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private  fun onForeground() {
        isForeground.value = true
    }

    //在后台
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onBackground() {
        isForeground.value = false
    }

}