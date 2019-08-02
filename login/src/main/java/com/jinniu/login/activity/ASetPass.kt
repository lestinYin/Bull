package com.jinniu.login.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jinniu.login.R
import com.lestin.yin.base.ABase

class ASetPass : ABase() {
    private var phone: String? = null

    override fun layoutId(): Int = R.layout.activity_aset_pass

    override fun initView() {
        phone = this.intent.getStringExtra("phone")
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.transparent).init()
    }

    override fun initData() {
    }

    override fun start() {
    }


}
