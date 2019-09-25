package com.jinniu.my.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.jinniu.my.R
import com.lestin.yin.activity.ACutImg
import com.lestin.yin.base.ABase
import com.lestin.yin.utils.LogUtil
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_achange_user_name.*

/**
 * 修改用户名页面
 */
class AChangeUserName : ABase() {

    override fun layoutId(): Int = R.layout.activity_achange_user_name

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.white).init()
        //同时打开软键盘
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    override fun initData() {
        iv_my_back.setOnClickListener {
            finish()
            closeKeyBord(et_user_name, this)
        }

    }

    override fun start() {
    }




}
