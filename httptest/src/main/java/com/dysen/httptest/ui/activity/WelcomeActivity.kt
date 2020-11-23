package com.dysen.mehttp.ui.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import com.dysen.mehttp.R
import com.dysen.mehttp.base.BaseActivit
import com.dysen.mehttp.databinding.ActivityWelcomeBinding
import com.dysen.mehttp.viewmodel.request.WelcomeViewModel
import com.dysen.mehttp.base.viewmodel.BaseViewModel
import com.dysen.mehttp.ext.getViewModel
import com.dysen.mehttp.ext.parseState

class WelcomeActivity : BaseActivit<BaseViewModel, ActivityWelcomeBinding>() {

    private val welcomeViewModel:WelcomeViewModel by lazy { getViewModel<WelcomeViewModel>() }

    private var resList = arrayOf("唱", "跳", "r a p")


    override fun layoutId(): Int {
        return R.layout.activity_welcome
    }

    override fun initView(savedInstanceState: Bundle?) {

        welcomeViewModel.getData(0)

        welcomeViewModel.welcomeResult.observe(this, Observer {
            parseState(it, {
                showTip("${it.total}")
            }, {})
        })

        newInstance(this, HttpTestAty::class.java, true)
        //带点渐变动画
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

}