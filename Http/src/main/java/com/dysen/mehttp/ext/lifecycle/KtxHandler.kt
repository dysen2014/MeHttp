package com.dysen.mehttp.ext.lifecycle

import android.os.Handler
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * dysen.
 * dy.sen@qq.com    9/16/20  11:11 AM

 * Info：
 */
class KtxHandler(lifecycleOwner: LifecycleOwner, callback: Callback) : Handler(callback), LifecycleObserver {

    private val mLifecycleOwner: LifecycleOwner = lifecycleOwner

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        removeCallbacksAndMessages(null)
        mLifecycleOwner.lifecycle.removeObserver(this)
    }
}