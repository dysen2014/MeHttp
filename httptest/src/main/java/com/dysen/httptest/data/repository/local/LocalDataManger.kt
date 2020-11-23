package com.dysen.mehttp.data.repository.local


/**
 * dysen.
 * dy.sen@qq.com    9/16/20  11:11 AM

 * Info：从本地获取的数据，可以从数据库，Sp等等中获取
 */
class LocalDataManger {

    companion object {
        val instance: LocalDataManger by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            LocalDataManger()
        }
    }

    /**
     * 获取本地存储的搜索数据
     */
//    fun getHistoryData(): ArrayList<String> {
//        return CacheUtil.getSearchHistoryData()
//    }
}