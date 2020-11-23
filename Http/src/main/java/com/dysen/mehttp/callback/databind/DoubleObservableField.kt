package com.dysen.mehttp.callback.databind

import androidx.databinding.ObservableField

/**
 * dysen.
 * dy.sen@qq.com    9/16/20  11:11 AM

 * Info：
 * 描述　:自定义的Int类型 ObservableField  提供了默认值，避免取值的时候还要判空
 */
class DoubleObservableField(value: Double = 0.0) : ObservableField<Double>(value) {

    override fun get(): Double {
        return super.get()!!
    }

}