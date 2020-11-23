package com.dysen.mehttp.callback.databind

import androidx.databinding.ObservableField

/**
 * dysen.
 * dy.sen@qq.com    9/16/20  11:11 AM

 * Info：
 * 描述　: 自定义的Boolean类型ObservableField 提供了默认值，避免取值的时候还要判空
 */
class BooleanObservableField(value: Boolean = false) : ObservableField<Boolean>(value) {
    override fun get(): Boolean {
        return super.get()!!
    }

}