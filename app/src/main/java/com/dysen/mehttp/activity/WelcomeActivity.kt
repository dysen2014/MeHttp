package com.dysen.mehttp.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import com.dysen.mehttp.R
import com.dysen.mehttp.base.BaseActivit
import com.dysen.mehttp.databinding.ActivityWelcomeBinding
import com.dysen.mehttp.viewmodel.request.WelcomeViewModel
import com.dysen.mehttp.base.viewmodel.BaseViewModel
import com.dysen.mehttp.data.model.ApiPagerResponse
import com.dysen.mehttp.data.model.AriticleResponse
import com.dysen.mehttp.data.model.DataRes
import com.dysen.mehttp.data.repository.local.CacheUtil
import com.dysen.mehttp.data.repository.local.Keys
import com.dysen.mehttp.ext.getViewModel
import com.dysen.mehttp.ext.parseState
import com.dysen.mehttp.ext.util.toJson
import com.dysen.mehttp.network.AppException
import com.dysen.mehttp.state.ResultState
import java.util.ArrayList

class WelcomeActivity : BaseActivit<BaseViewModel, ActivityWelcomeBinding>() {

    private val welcomeViewModel:WelcomeViewModel by lazy { getViewModel<WelcomeViewModel>() }

    private var resList = arrayOf("唱", "跳", "r a p")

    private var mDatas = mutableListOf<DataRes>()

    override fun layoutId(): Int {
        return R.layout.activity_welcome
    }

    override fun initView(savedInstanceState: Bundle?) {

        welcomeViewModel.getData(0)

        welcomeViewModel.welcomeResult.observe(this, Observer {
            parseState(it, {
                showTip("${it.total}")
                val a: DataRes = it.datas[0]
                val listDats:MutableList<DataRes> = it.datas
                CacheUtil.sObj(Keys.KEY_DATA, listDats)
                CacheUtil.sObj(Keys.KEY_LOGIN, a)
                val data : DataRes? = CacheUtil.gObj(Keys.KEY_LOGIN, DataRes::class.java)
                val list = CacheUtil.gList<DataRes>(Keys.KEY_DATA)
                list?.forEach {
                println("*************${it.author}")
                    mDatas.add(it)
            }
                println("${data}\n====\n${mDatas[0].author}")
                newInstance(this, HttpTestAty::class.java, true)
            }, {})
        })

        //带点渐变动画
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}