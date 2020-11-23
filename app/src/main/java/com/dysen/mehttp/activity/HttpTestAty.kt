package com.dysen.mehttp.activity

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dysen.mehttp.R
import com.dysen.mehttp.base.XActivity
import com.dysen.mehttp.data.model.UserInfo
import com.dysen.mehttp.viewmodel.request.RequestLoginRegisterViewModel
import com.dysen.mehttp.state.ResultState
import com.kongzue.dialog.v3.TipDialog
import com.dysen.mehttp.ext.getViewModel
import kotlinx.android.synthetic.main.activity_http_test.*
import com.dysen.mehttp.network.AppException

class HttpTestAty : XActivity() {

    /** 注意，在by lazy中使用getViewModel一定要使用泛型，虽然他提示不报错，但是你不写是不行的 */
    private val requestLoginRegisterViewModel: RequestLoginRegisterViewModel by lazy { getViewModel<RequestLoginRegisterViewModel>() }

    override fun layoutId(): Int {
        return R.layout.activity_http_test
    }

    var loginResult = MutableLiveData<ResultState<UserInfo>>()

    override fun initView(savedInstanceState: Bundle?) {
        initClick()

        requestLoginRegisterViewModel.loginResult.observe(this, Observer {
            parseState(it, {
                //登录成功
                TipDialog.show(this, "${it.nickname}", TipDialog.TYPE.SUCCESS)
            }, {
                //登录失败
                TipDialog.show(this, "${it.errorMsg}", TipDialog.TYPE.ERROR)
            })
        })

    }

    private fun initClick() {
        tvRequest.setOnClickListener {
            TipDialog.show(this, "login", TipDialog.TYPE.OTHER)

            requestLoginRegisterViewModel.loginReq("17020099956", "dy,.11211")
        }
    }

    fun <T> parseState(
        resultState: ResultState<T>,
        onSuccess: (T) -> Unit,
        onError: ((AppException) -> Unit)? = null,
        onLoading: (() -> Unit)? = null
    ) {
        when (resultState) {
            is ResultState.Loading -> {
                showLoading(resultState.loadingMessage)
                onLoading?.run { this }
            }
            is ResultState.Success -> {
                dismissLoading()
                onSuccess(resultState.data)
            }
            is ResultState.Error -> {
                dismissLoading()
                onError?.run { this(resultState.error) }
            }
        }
    }
}