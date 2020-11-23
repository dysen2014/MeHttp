package com.dysen.mehttp.base

import com.dysen.mehttp.base.app.BaseApp
import com.kongzue.dialog.util.DialogSettings
import com.tencent.mmkv.MMKV

/**
 * dysen.
 * dy.sen@qq.com    11/17/20  16:32 PM

 * Info：
 */
 class App : BaseApp() {

    companion object {
        val instance: App by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            App()
        }
    }
    override fun onCreate() {
        super.onCreate()

        DialogSettings.style = DialogSettings.STYLE.STYLE_IOS

        MMKV.initialize(this.filesDir.absolutePath + "/mmkv")

        //初始化Bugly
        val context = applicationContext
        // 获取当前包名
        val packageName = context.packageName
    }
}
