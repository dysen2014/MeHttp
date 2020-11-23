package com.dysen.room_test

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dysen.mehttp.R
import com.dysen.mehttp.base.XActivity
import com.dysen.room_test.dao.PeopleDao
import com.dysen.room_test.database.MeDataBase
import com.dysen.room_test.entity.People
import kotlinx.android.synthetic.main.activity_room_test_acty.*

class RoomTestActy : XActivity() {
    val TAG = "RoomTestActy"
    var peopleDao: PeopleDao? = null

//    companion object {
//        fun newInstance(context: Context) {
//            val intent = Intent(context, RoomTestActy::class.java)
//            context.startActivity(intent)
//        }
//    }

    override fun layoutId(): Int {
        return R.layout.activity_room_test_acty
    }

    override fun initView(savedInstanceState: Bundle?) {
        peopleDao = MeDataBase.getDatabase(this).peopleDao()

        val permissions = arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, 1)
        }
        initClick()
    }

    private fun initClick() {
        var people = People("sendy", 31, "广东中山丽江")
        btnInsert.setOnClickListener {
        for (i in 1..100){
            people = People("sendy-$i", i, "广东中山丽江-${i}号")
            peopleDao?.insertPeople(people)
        }
            queryData()
        }
        btnDel.setOnClickListener {
            peopleDao?.deletePeople(people)
            queryData()
            people = People("sendy", 31, "广东中山丽江")
        }
        btnUpdate.setOnClickListener {
            people.age = 35
            peopleDao?.updatePeople(people)
            queryData()
        }
        btnQuery.setOnClickListener {
            queryData()
        }
    }

    private fun queryData() {
        val p = peopleDao?.getPeople()
        tvContent.text = p.toString()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        if (requestCode == 1) {
            Log.d(
                TAG,
                "onRequestPermissionsResult: $requestCode"
            )
            for (i in grantResults.indices) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "onRequestPermissionsResult-需要开启存储权限")
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions!!, grantResults)
    }
}