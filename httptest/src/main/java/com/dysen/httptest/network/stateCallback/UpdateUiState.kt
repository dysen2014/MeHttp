package com.dysen.mehttp.network.stateCallback

/**
 * dysen.
 * dy.sen@qq.com    9/16/20  11:11 AM

 * Info：操作数据的状态类
 */
data class UpdateUiState<T>(
    //请求是否成功
    var isSuccess: Boolean = true,
    //操作的对象
    var data : T,
    //请求失败的错误信息
    var errorMsg: String = ""
)