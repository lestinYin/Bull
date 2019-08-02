package com.jinniu.login

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.jinniu.login.MyApplication.Companion.context
import com.jinniu.login.activity.ARegister
import com.lestin.yin.base.ABase
import kotlinx.android.synthetic.main.activity_alogin.*

class ALogin : ABase() {
    override fun layoutId(): Int = R.layout.activity_alogin

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.transparent).init()

    }

    override fun initData() {
        tv_regist.setOnClickListener {
            var  intent = Intent(applicationContext, ARegister::class.java)
            startActivity(intent)
        }
    }

    override fun start() {
    }


}
