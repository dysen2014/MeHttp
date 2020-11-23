package com.dysen

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dysen.baselib.base.AppContext
import com.dysen.baselib.base.BaseActivity
import com.dysen.baselib.common.base_recycler_adapter.SuperRecyclerAdapter
import com.dysen.baselib.common.base_recycler_adapter.SuperRecyclerHolder
import com.dysen.baselib.data.Keys
import com.dysen.baselib.data.entity.CountryData
import com.dysen.baselib.model.LiveDataManager
import com.dysen.baselib.ui.country_code.CountryActivity
import com.dysen.baselib.ui.scan.CustomScanActivity
import com.dysen.baselib.utils.SharedPreUtils
import com.dysen.baselib.utils.UIHelper
import com.dysen.coil_test.CoilTestActivity
import com.dysen.mehttp.R
import com.dysen.mehttp.ui.activity.WelcomeActivity
import com.dysen.optionbarview.OptionBarView
import com.dysen.room_test.RoomTestActy
import com.dysen.widgets_test.OptionBarViewAty
import com.dysen.widgets_test.PercentTestAty
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private var menuItems =
        mutableListOf("Requst", "Room", "Coil", "OptinBarView", "Percent", "扫一扫", "CountryCode")
    private var clzzs = mutableListOf<Class<*>>(
        WelcomeActivity::class.java,
        RoomTestActy::class.java,
        CoilTestActivity::class.java,
        OptionBarViewAty::class.java,
        PercentTestAty::class.java,
        CustomScanActivity::class.java,
        CountryActivity::class.java
    )
    private var mAdapter: SuperRecyclerAdapter<String>? = null
    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        LiveDataManager.instance?.with(Keys.SCAN_CONTENT, String::class.java)
            ?.observer(this, Observer {
                it?.let {

                    if (it.isNotEmpty()) showTip( "扫描的内容：${it}")
                }
                println("扫描的内容$mScanContent")

            })
        LiveDataManager.instance?.with(Keys.COUNTRY_CODE, CountryData::class.java)
            ?.observer(this, Observer {
                var name =
                    if (SharedPreUtils.en == AppContext.mSpUtils[SharedPreUtils.KEY_APP_LANGUAGE, SharedPreUtils.cn])
                        it?.shortNameEn else it?.shortName

                showTip("选择的内容：${it.code}----$name")
            })
        opv.setRightText("关于")
        opv.setTitleText("首页")
        initAdapter()
    }

    private fun initAdapter() {
        mAdapter = object : SuperRecyclerAdapter<String>(this@MainActivity, menuItems) {
            override fun convert(
                holder: SuperRecyclerHolder?,
                t: String?,
                layoutType: Int,
                position: Int
            ) {
                val opv: OptionBarView = holder?.getViewById(R.id.tv_item) as OptionBarView
                t?.let {
                    opv.setTitleText(it)
                    opv.setOnClickListener { _ ->
//                        showTip(it)
                        if (position == 5)
                            customScan(this@MainActivity)
                        if (position == 6)
                            UIHelper.showCountry(this@MainActivity)
                        else
                            newInstance(this@MainActivity, clzzs[position])
                    }
                }
            }

            override fun getLayoutAsViewType(t: String?, position: Int): Int {
                return R.layout.layout_common_item
            }

        }

//        rcl.layoutManager = FlexboxLayoutManager(this)
        rcl.layoutManager = StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
        rcl.adapter = mAdapter
    }
}