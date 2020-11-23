package com.dysen.mehttp.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dysen.mehttp.network.manager.NetState
import com.dysen.mehttp.network.manager.NetworkStateManager
import com.dysen.mehttp.base.viewmodel.BaseViewModel
import com.dysen.mehttp.ext.getVmClazz

/**
 * dysen.
 * dy.sen@qq.com    9/16/20  11:11 AM

 * Info： ViewModelActivity基类，把ViewModel注入进来了
 */
abstract class BaseVmActivity<VM : BaseViewModel> : AppCompatActivity() {

    lateinit var mViewModel: VM

    abstract fun layoutId(): Int

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun showLoading(message: String = "请求网络中...")

    abstract fun dismissLoading()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        mViewModel = createViewModel()
        registerUiChange()
        initView(savedInstanceState)
        createObserver()
        lifecycle.addObserver(NetworkStateManager.instance)
        NetworkStateManager.instance.mNetworkStateCallback.observe(this, Observer {
            onNetworkStateChanged(it)
        })
    }

    /**
     * 网络变化监听 子类重写
     */
    open fun onNetworkStateChanged(netState: NetState) {}

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        ).get(getVmClazz(this))
    }

    /**
     * 创建LiveData数据观察者
     */
    abstract fun createObserver()

    /**
     * 注册 UI 事件
     */
    private fun registerUiChange() {
        //显示弹窗
        mViewModel.loadingChange.showDialog.observe(this, Observer {
            showLoading(
                if (it.isEmpty()) {
                    "请求网络中..."
                } else it
            )
        })
        //关闭弹窗
        mViewModel.loadingChange.dismissDialog.observe(this, Observer {
            dismissLoading()
        })
    }
}