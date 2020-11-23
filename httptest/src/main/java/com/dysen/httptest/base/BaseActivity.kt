package com.dysen.mehttp.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.dysen.mehttp.base.activity.BaseVmDbActivity
import com.dysen.mehttp.base.viewmodel.BaseViewModel
import com.kongzue.dialog.v3.MessageDialog
import com.kongzue.dialog.v3.TipDialog
import com.kongzue.dialog.v3.WaitDialog

/**
 * dysen.
 * dy.sen@qq.com    9/16/20  11:11 AM

 * Info：你项目中的Activity基类，在这里实现显示弹窗，吐司，还有加入自己的需求操作 ，如果不想用Databind，请继承
 * BaseVmActivity例如
 * abstract class BaseActivity<VM : BaseViewModel> : BaseVmActivity<VM>() {
 */
abstract class BaseActivit <VM : BaseViewModel, DB : ViewDataBinding> : BaseVmDbActivity<VM, DB>() {

    lateinit var messageDialog: MessageDialog
    lateinit var tipDialog: TipDialog

    companion object {
        fun newInstance(
            aty: AppCompatActivity,
            cls: Class<*>,
            isFinish: Boolean = false
        ) {
            val intent = Intent(aty, cls)
            aty.startActivity(intent)
            if (isFinish)
                aty.finish()
//            aty.startActivityForResult(intent, Constant.ADD_PEOPLE)
        }
    }

    abstract override fun layoutId(): Int

    abstract override fun initView(savedInstanceState: Bundle?)

    /**
     * 创建liveData观察者
     */
    override fun createObserver() {}


    /**
     * 打开等待框
     */
    override fun showLoading(message: String) {
        tipDialog = showTip(message)
    }

   fun showTip(message: String, duration:Int = 4000): TipDialog {
           return WaitDialog.show(this, message).setTipTime(duration)
   }

    /**
     * 关闭等待框
     */
    override fun dismissLoading() {
        tipDialog?.setTipTime(200)
    }



}