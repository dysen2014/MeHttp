package com.dysen.mehttp.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kongzue.dialog.v3.TipDialog

/**
 * dysen.
 * dy.sen@qq.com    9/16/20  11:11 AM

 * Info：你项目中的Activity基类，在这里实现显示弹窗，吐司，还有加入自己的需求操作 ，如果不想用Databind，请继承
 * BaseVmActivity例如
 * abstract class BaseActivity<VM : BaseViewModel> : BaseVmActivity<VM>() {
 */
abstract class XActivity : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initView(savedInstanceState)
    }
    abstract fun layoutId(): Int

    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 打开等待框
     */
    fun showLoading(message: String, duration: Int = 4000) {
//        tipDialog = showTip(message, type = TipDialog.TYPE.OTHER).setTip(R.mipmap.icon_submit_success)
        tipDialog = TipDialog.showWait(this, message)
        TipDialog.dismiss(duration)
    }

    fun showTip(message: String, duration:Int = 4000): TipDialog {
        return TipDialog.show(this, message,TipDialog.TYPE.WARNING).setTipTime(duration)
    }

    /**
     * 关闭等待框
     */
    fun dismissLoading(duration: Int = 2000) {
        TipDialog.dismiss(duration)
    }

}