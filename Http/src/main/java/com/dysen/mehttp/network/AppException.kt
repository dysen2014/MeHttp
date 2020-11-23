package com.dysen.mehttp.network

import me.hgj.jetpackmvvm.network.Error

/**
 * dysen.
 * dy.sen@qq.com    9/16/20  11:11 AM

 * Info：
 * 描述　:自定义错误信息异常
 */
class AppException : Exception {

    var errorMsg: String //错误消息
    var errCode: Int = 0 //错误码
    var errorLog: String? //错误日志

    constructor(errCode: Int, error: String?, errorLog: String? = "") : super(error) {
        this.errorMsg = error ?: "请求失败，请稍后再试"
        this.errCode = errCode
        this.errorLog = errorLog?:this.errorMsg
    }

    constructor(error: Error, e: Throwable?) {
        errCode = error.getKey()
        errorMsg = error.getValue()
        errorLog = e?.message
    }
}