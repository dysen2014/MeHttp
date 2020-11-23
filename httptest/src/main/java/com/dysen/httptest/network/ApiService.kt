package com.dysen.mehttp.network

import com.dysen.mehttp.data.model.ApiPagerResponse
import com.dysen.mehttp.data.model.ApiResponse
import com.dysen.mehttp.data.model.AriticleResponse
import com.dysen.mehttp.data.model.UserInfo
import retrofit2.http.*

/**
 * dysen.
 * dy.sen@qq.com    9/16/20  11:11 AM

 * Info：网络API
 */
interface ApiService {

    companion object {
        const val SERVER_URL = "https://wanandroid.com/"
    }


    /**
     * 获取最新项目数据
     */
    @GET("article/listproject/{page}/json")
    suspend fun getProjecNewData(@Path("page") pageNo: Int): ApiResponse<ApiPagerResponse<ArrayList<AriticleResponse>>>

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(@Field("username") username: String, @Field("password") pwd: String): ApiResponse<UserInfo>

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("user/register")
    suspend fun register(
        @Field("username") username: String, @Field("password") pwd: String, @Field(
            "repassword"
        ) rpwd: String
    ): ApiResponse<Any>


    /**
     * 收藏文章
     */
    @POST("lg/collect/{id}/json")
    suspend fun collect(@Path("id") id: Int): ApiResponse<Any?>

    /**
     * 取消收藏文章
     */
    @POST("lg/uncollect_originId/{id}/json")
    suspend fun uncollect(@Path("id") id: Int): ApiResponse<Any?>

    /**
     * 取消收藏网址
     */
    @POST("lg/collect/deletetool/json")
    suspend fun deletetool(@Query("id") id: Int): ApiResponse<Any?>

    /**
     * 添加一个TODO
     */
    @POST("/lg/todo/add/json")
    @FormUrlEncoded
    suspend fun addTodo(@Field("title") title: String,
                @Field("content") content: String,
                @Field("date") date: String,
                @Field("type") type: Int,
                @Field("priority") priority: Int): ApiResponse<Any?>

    /**
     * 修改一个TODO
     */
    @POST("/lg/todo/update/{id}/json")
    @FormUrlEncoded
    suspend fun updateTodo(@Field("title") title: String,
                   @Field("content") content: String,
                   @Field("date") date: String,
                   @Field("type") type: Int,
                   @Field("priority") priority: Int,
                   @Path("id") id: Int): ApiResponse<Any?>

    /**
     * 删除一个TODO
     */
    @POST("/lg/todo/delete/{id}/json")
    suspend fun deleteTodo(@Path("id") id: Int): ApiResponse<Any?>

    /**
     * 完成一个TODO
     */
    @POST("/lg/todo/done/{id}/json")
    @FormUrlEncoded
    suspend fun doneTodo(@Path("id") id: Int, @Field("status") status: Int): ApiResponse<Any?>


}