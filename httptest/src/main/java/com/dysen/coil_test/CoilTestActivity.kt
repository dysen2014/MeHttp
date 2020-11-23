package com.dysen.coil_test

import android.os.Bundle
import coil.load
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.dysen.baselib.ui.image_paper.BigImagePagerActivity
import com.dysen.mehttp.R
import com.dysen.mehttp.base.XActivity
import kotlinx.android.synthetic.main.activity_coil_test.*

class CoilTestActivity : XActivity() {

    override fun layoutId(): Int {
        return R.layout.activity_coil_test
    }

    override fun initView(savedInstanceState: Bundle?) {
//        val imgUrl = "https://cdn.pixabay.com/photo/2020/06/14/22/31/the-caucasus-5299599__480.jpg"
        tvCoil.text = "wow failed"
        tvCoil.isAllCaps = false

        val imgUrl = "https://cdn.pixabay.com/photo/2020/10/17/11/55/elephants-5661842_1280.jpg"
        iv.load(imgUrl){
            crossfade(true)
        }
        iv.setOnClickListener {
            BigImagePagerActivity.startImagePagerActivity(this,imgUrl)
        }
        iv2.load(imgUrl){
            crossfade(false)
            placeholder(R.mipmap.ic_launcher)
            transformations(RoundedCornersTransformation(10f))
        }
        iv3.load(imgUrl){
            crossfade(true)
            placeholder(R.mipmap.ic_launcher)
            transformations(BlurTransformation(this@CoilTestActivity, 10f))
        }
        iv4.load(imgUrl){
            crossfade(true)
            placeholder(R.mipmap.ic_launcher)
            transformations(CircleCropTransformation())
        }
    }
}