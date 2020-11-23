package com.dysen.mehttp.data.repository.request

import com.dysen.mehttp.data.model.*
import com.dysen.mehttp.network.NetworkApi
import com.dysen.mehttp.network.AppException

/**
 * dysen.
 * dy.sen@qq.com    9/16/20  11:11 AM

 * Info： 从网络中获取数据
 */
class HttpRequestManger {

    companion object {
        val instance: HttpRequestManger by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            HttpRequestManger()
        }
    }


    /**
     *
     */
    suspend fun getData( pageInt: Int): ApiResponse<ApiPagerResponse<MutableList<DataRes>>> {
        return NetworkApi.instance.service.getProjecNewData(pageInt)
    }
    /**
     * 登录
     */
    suspend fun login(username: String, password: String): ApiResponse<UserInfo> {
        return NetworkApi.instance.service.login(username, password)
    }

    /**
     * 注册并登陆
     */
    suspend fun register(username: String, password: String): ApiResponse<UserInfo> {
        val registerData = NetworkApi().service.register(username, password, password)
        //判断注册结果 注册成功，调用登录接口
        if (registerData.isSucces()) {
            return login(username, password)
        } else {
            //抛出错误异常
            throw AppException(registerData.errorCode, registerData.errorMsg)
        }
    }

    /**
     * 收藏文章
     */
    suspend fun collect(id: Int): ApiResponse<Any?> {
        return NetworkApi.instance.service.collect(id)
    }

    /**
     * 取消收藏文章
     */
    suspend fun uncollect(id: Int): ApiResponse<Any?> {
        return NetworkApi.instance.service.uncollect(id)
    }

    /**
     * 取消收藏网址
     */
    suspend fun uncollectUrl(id: Int): ApiResponse<Any?> {
        return NetworkApi.instance.service.deletetool(id)
    }

    /**
     * 删除TODo数据
     */
    suspend fun delTodotData(id: Int): ApiResponse<Any?> {
        return NetworkApi.instance.service.deleteTodo(id)
    }

    /**
     * 完成TODo
     */
    suspend fun doneTodotData(id: Int): ApiResponse<Any?> {
        //1完成
        return NetworkApi.instance.service.doneTodo(id, 1)
    }

    /**
     * 添加TODo
     */
    suspend fun addTodotData(
        title: String,
        content: String,
        date: String,
        type: Int,
        priority: Int
    ): ApiResponse<Any?> {
        return NetworkApi.instance.service.addTodo(title, content, date, type, priority)
    }

    /**
     * 修复TODo
     */
    suspend fun updateTodotData(
        title: String,
        content: String,
        date: String,
        type: Int,
        priority: Int,
        id: Int
    ): ApiResponse<Any?> {
        return NetworkApi.instance.service.updateTodo(title, content, date, type, priority, id)
    }

}