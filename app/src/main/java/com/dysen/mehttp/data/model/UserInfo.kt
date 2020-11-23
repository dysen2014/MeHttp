package com.dysen.mehttp.data.model
import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * dysen.
 * dy.sen@qq.com    10/16/20  16:11 PM

 * Info： 账户信息
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class UserInfo(var admin: Boolean = false,
                    var chapterTops: List<String> = listOf(),
                    var collectIds: MutableList<String> = mutableListOf(),
                    var email: String="",
                    var icon: String="",
                    var id: String="",
                    var nickname: String="",
                    var password: String="",
                    var token: String="",
                    var type: Int =0,
                    var username: String="") : Parcelable
