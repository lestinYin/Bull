package com.jinniu.my.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.jinniu.my.R
import com.jinniu.my.fragment.FMyAddress
import com.lestin.yin.base.ABase

class AMyReceiptAddress : ABase() {
    override fun layoutId(): Int = R.layout.activity_common_fragment

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.white).init()
    }

    override fun initData() {
        var fMyAddress = FMyAddress()
        val message = Bundle()
        fMyAddress.setArguments(message)
        supportFragmentManager.beginTransaction().replace(R.id.frame, fMyAddress).commit()

        var back = findViewById<ImageView>(R.id.iv_back)
        back.setOnClickListener {
            finish()
        }

    }


    override fun start() {
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = ""
        var title = findViewById<TextView>(R.id.tv_title)
        title.text = resources.getString(R.string.my_receipt_address)

        var tv_right_title = findViewById<TextView>(R.id.tv_right_title)
        tv_right_title.text = resources.getString(R.string.my_create_address)


    }






}