package com.dysen.mehttp.data.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class DataRes(
    /**
    {
    "apkLink": "",
    "audit": 1,
    "author": "iielse",
    "canEdit": false,
    "chapterId": 380,
    "chapterName": "ImageView",
    "collect": false,
    "courseId": 13,
    "desc": "提供查看缩略视图到原视图的无缝过渡转变的视觉效果，优雅的浏览普通图、长图、动图.\r\n主要功能\r\n过渡动画 缩略图到大图或大图到缩略图时提供无缝衔接动画\r\n浏览手势 浏览大图时可使用常势操用手.如缩放图片等.\r\n超大图 图片区块加载.避免OOM\r\nVideo 支持Video加载\r\n拖拽关闭 对大图进行上/下滑操作退出浏览.\r\n数据分页加载 在浏览大图的情况下异步加载百万数据.",
    "descMd": "",
    "envelopePic": "https://www.wanandroid.com/blogimgs/23ca370f-ea42-4354-a4f4-5a69c4019232.png",
    "fresh": false,
    "id": 15184,
    "link": "https://www.wanandroid.com/blog/show/2789",
    "niceDate": "2020-09-09 23:44",
    "niceShareDate": "2020-09-09 23:44",
    "origin": "",
    "prefix": "",
    "projectLink": "https://github.com/iielse/imageviewer",
    "publishTime": 1599666299000,
    "realSuperChapterId": 293,
    "selfVisible": 0,
    "shareDate": 1599666299000,
    "shareUser": "",
    "superChapterId": 294,
    "superChapterName": "开源项目主Tab",
    "tags": [
    {
    "name": "项目",
    "url": "/project/list/1?cid=380"
    }
    ],
    "title": "一个简单且可自定义的Android全屏图像浏览器",
    "type": 0,
    "userId": -1,
    "visible": 1,
    "zan": 0
    }
     */
    val apkLink: String,
    val audit: Int,
    val author: String,
    val canEdit: Boolean,
    val chapterId: Int,
    val chapterName: String,
    val collect: Boolean,
    val courseId: Int,
    val desc: String,
    val descMd: String,
    val envelopePic: String,
    val fresh: Boolean,
    val id: Int,
    val link: String,
    val niceDate: String,
    val niceShareDate: String,
    val origin: String,
    val prefix: String,
    val projectLink: String,
    val publishTime: Long,
    val realSuperChapterId: Int,
    val selfVisible: Int,
    val shareDate: Long,
    val shareUser: String,
    val superChapterId: Int,
    val superChapterName: String,
    val tags: MutableList<Tag>,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Tag(
    val name: String,
    val url: String
):Parcelable