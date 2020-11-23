package com.dysen.mehttp.callback.databind

import androidx.databinding.ObservableField

/**
 * dysen.
 * dy.sen@qq.com    9/16/20  11:11 AM

 * Info：
 * 描述　:自定义的String类型 ObservableField  提供了默认值，避免取值的时候还要判空
 */
class StringObservableField(value: String = "") : ObservableField<String>(value) {

    override fun get(): String {
        return super.get()!!
    }

}