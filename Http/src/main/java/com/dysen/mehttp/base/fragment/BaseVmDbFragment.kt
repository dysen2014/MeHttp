package com.dysen.mehttp.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dysen.mehttp.network.manager.NetState
import com.dysen.mehttp.network.manager.NetworkStateManager
import com.dysen.mehttp.base.viewmodel.BaseViewModel
import com.dysen.mehttp.ext.getVmClazz

/**
 * dysen.
 * dy.sen@qq.com    9/16/20  11:11 AM

 * Info： ViewModelFragment基类，自动把ViewModel注入Fragment和Databind注入进来了
 * 需要使用Databind的清继承它
 */
abstract class BaseVmDbFragment<VM : BaseViewModel, DB : ViewDataBinding> : Fragment() {

    //是否第一次加载
    private var isFirst: Boolean = true
    //该类负责绑定视图数据的Viewmodel
    lateinit var mViewModel: VM
    //该类绑定的ViewDataBinding
    lateinit var mDatabind: DB

    /**
     * 当前Fragment绑定的视图布局
     */
    abstract fun layoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDatabind = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        mDatabind.lifecycleOwner = this
        return mDatabind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = createViewModel()
        initView(savedInstanceState)
        onVisible()
        registorDefUIChange()
        initData()
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
            ViewModelProvider.AndroidViewModelFactory(this.requireActivity().application)
        ).get(getVmClazz(this))
    }

    /**
     * 初始化view
     */
    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 懒加载
     */
    abstract fun lazyLoadData()

    /**
     * 创建观察者
     */
    abstract fun createObserver()


    override fun onResume() {
        super.onResume()
        onVisible()
    }

    /**
     * 是否需要懒加载
     */
    private fun onVisible() {
        if (lifecycle.currentState == Lifecycle.State.STARTED && isFirst) {
            lazyLoadData()
            isFirst = false
            createObserver()
            NetworkStateManager.instance.mNetworkStateCallback.observe(this, Observer {
                onNetworkStateChanged(it)
            })
        }
    }

    /**
     * Fragment执行onCreate后触发的方法
     */
    open fun initData() {}

    abstract fun showLoading(message: String = "请求网络中...")

    abstract fun dismissLoading()


    /**
     * 注册 UI 事件
     */
    private fun registorDefUIChange() {
        mViewModel.loadingChange.showDialog.observe(viewLifecycleOwner, Observer {
            showLoading()
        })
        mViewModel.loadingChange.dismissDialog.observe(viewLifecycleOwner, Observer {
            dismissLoading()
        })
    }
}