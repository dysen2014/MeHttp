package com.dysen.mehttp.network.stateCallback

/**
 * dysen.
 * dy.sen@qq.com    9/16/20  11:11 AM

 * Info：收藏数据状态类
 */
data class CollectUiState(
    //请求是否成功
    var isSuccess: Boolean = true,
    //收藏
    var collect: Boolean = false,
    //收藏Id
    var id: Int = -1,
    //请求失败的错误信息
    var errorMsg: String = ""
)