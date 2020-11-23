package com.dysen.widgets_test

import android.os.Bundle
import com.dysen.baselib.base.BaseActivity
import com.dysen.mehttp.R
import com.dysen.mehttp.base.XActivity
import com.dysen.optionbarview.OptionBarView
import com.dysen.optionbarview.OptionStringUtils.checkStr
import kotlinx.android.synthetic.main.activity_option_bar_view.*

class OptionBarViewAty : BaseActivity() {

    override fun layoutId(): Int {
        return R.layout.activity_option_bar_view
    }

    override fun initView(savedInstanceState: Bundle?) {
        initData()
        initClick()
    }

    private fun initData() {
        opv.setLeftText(checkStr("Name:hd fajv ja jvjav  v  vjen 阿胶驴车恐惧而ver鹅v好热v把v吧",10))
        opv.setTitleText("--")
        opv.setRightText("sendy")
        opv.setRightTextColor(R.color.notificationError)
    }

    private fun initClick() {
//        opv.setOnClickListener { showTip("hehehe") }
        opv.splitMode = true
        opv.setOnOptionItemClickListener(object : OptionBarView.MeOnOptionItemClickListener() {
            override fun leftOnClick() {
                showTip("hello ")
            }

            override fun centerOnClick() {
                showTip("centerOnClick ")
            }
        })

        opvSw.splitMode = true
        opvSw.setOnSwitchCheckedChangeListener(object :OptionBarView.OnSwitchCheckedChangeListener{
            override fun onCheckedChanged(view: OptionBarView?, isSwitchChecked: Boolean) {
//                showTip("$isSwitchChecked")
                opvSw.setRightText(if (isSwitchChecked) "NO" else "OFF")
            }

        })
        opvSw.setOnOptionItemClickListener(object : OptionBarView.MeOnOptionItemClickListener() {
            override fun leftOnClick() {
                showTip("sw  leftOnClick")
            }

            override fun centerOnClick() {
                showTip("sw centerOnClick ")
            }
        })
    }
}