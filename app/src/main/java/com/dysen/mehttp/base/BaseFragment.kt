package com.dysen.mehttp.base

import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * dysen.
 * dy.sen@qq.com    9/16/20  11:11 AM

 * Info：你项目中的Fragment基类，在这里实现显示弹窗，吐司，还有自己的需求操作 ，如果不想用Databind，请继承
 * BaseVmFragment例如
 * abstract class BaseFragment<VM : BaseViewModel> : BaseVmFragment<VM>() {
 */
abstract class BaseFragment : Fragment() {

    /**
     * 当前Fragment绑定的视图布局
     */
    abstract fun layoutId(): Int


    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 懒加载 只有当前fragment视图显示时才会触发该方法
     */
    abstract fun lazyLoadData()


    /**
     * Fragment执行onViewCreated后触发的方法
     */
     fun initData() {

    }

    /**
     * 打开等待框
     */
     fun showLoading(message: String) {

    }

    /**
     * 关闭等待框
     */
     fun dismissLoading() {
//        dialog?.dismiss()
    }

    override fun onPause() {
        super.onPause()
    }
}