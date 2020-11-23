package com.dysen.mehttp.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dysen.mehttp.network.manager.NetState
import com.dysen.mehttp.network.manager.NetworkStateManager
import com.dysen.mehttp.base.viewmodel.BaseViewModel
import com.dysen.mehttp.ext.getVmClazz

/**
 * dysen.
 * dy.sen@qq.com    9/16/20  11:11 AM

 * Info：包含Viewmodel 和Databind ViewModelActivity基类，把ViewModel 和Databind注入进来了
 * 需要使用Databind的清继承它
 */
abstract class BaseVmDbActivity<VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {

    lateinit var mViewModel: VM

    lateinit var mDatabind: DB

    abstract fun layoutId(): Int

    abstract fun initView(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createViewDataBinding()
        mViewModel = createViewModel()
        registorDefUIChange()
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

    abstract fun showLoading(message: String = "请求网络中...")

    abstract fun dismissLoading()

    /**
     * 创建DataBinding
     */
    private fun createViewDataBinding() {
        mDatabind = DataBindingUtil.setContentView(this, layoutId())
        mDatabind.lifecycleOwner = this
    }

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
     * 创建观察者
     */
    abstract fun createObserver()

    /**
     * 注册 UI 事件
     */
    private fun registorDefUIChange() {
        mViewModel.loadingChange.showDialog.observe(this, Observer {
            showLoading(
                if (it.isEmpty()) {
                    "请求网络中..."
                } else it
            )
        })
        mViewModel.loadingChange.dismissDialog.observe(this, Observer {
            dismissLoading()
        })
    }
}