package com.dysen.mehttp.data.repository.local

import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

/**
 * @package com.dysen.test.utils
 * @email dy.sen@qq.com
 * created by dysen on 2018/12/19 - 5:14 PM
 * @info
 */
object JsonUtils {

    fun isJson(content: String): Boolean {
        try {
            JSONObject(content)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    /**
     * 从 json 对象中取出相应的 element
     */
    fun getJsonElement(json: JSONObject?,element:String):String{
        return json!!.opt(element)?.toString() ?: ""
    }

    /**
     * 取出字符串里某个json 对象 并返回这个对象
     *
     * @param jsonData
     * @param name
     * @return
     * @throws JSONException
     */
    @Throws(JSONException::class)
    fun getJsonObject(jsonData: String?, vararg name: String): JSONObject? {
        if (!TextUtils.isEmpty(jsonData) && jsonData != null) {
            val json = JSONObject(jsonData)
            return if (name.isNotEmpty()) {
//                LogUtils.d("http response parse", "jsonObject: " + json.toString());
                json.optJSONObject(name[0])
            } else {
                json
            }
        } else
            return null
    }

    @Throws(JSONException::class)
    fun getJsonArray(jsonObject: JSONObject?, name: String): JSONArray? {
        return jsonObject?.optJSONArray(name)
    }

    /**
     * json字符串 转成实体类
     *
     * @param <T>
     * @param jsonData
     * @return
    </T> */
    fun <T> parseList(jsonData: String?, cls: Class<T>): List<T>? {
        if (!TextUtils.isEmpty(jsonData) || jsonData != null) {
            val gson = Gson()
            val list = ArrayList<T>()
            val arry = JsonParser().parse(jsonData!!).asJsonArray
            for (jsonElement in arry) {
                list.add(gson.fromJson(jsonElement, cls))
            }
            return list
        } else
            return null
    }

    fun <T> parseObject(jsonData: String?, jsonElement: String?, cls: Class<T>): T? {
        if (!TextUtils.isEmpty(jsonData) || jsonData != null) {
            val gson = Gson()
            val jsonObject = JsonParser().parse(jsonData!!)
            println("${jsonObject!!.asString}====${jsonObject}")
            return gson.fromJson(jsonObject!!.asString, cls)
        } else
            return null
    }

    fun <T> parseObject(jsonData: String?, cls: Class<T>): T? {
        if (jsonData != null) {
            val gson = Gson()
            return gson.fromJson(jsonData, cls)
        } else
            return null
    }

    //:Map<String, Objects>
    fun jsonObject2Map(jsonObject: JsonObject) {


    }

}
