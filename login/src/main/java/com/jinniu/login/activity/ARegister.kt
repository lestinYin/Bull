package com.jinniu.login.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.jinniu.login.R
import com.lestin.yin.base.ABase
import com.lestin.yin.utils.SnackbarUtils
import kotlinx.android.synthetic.main.activity_aregister.*

class ARegister : ABase() {
    override fun layoutId(): Int = R.layout.activity_aregister

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.transparent).init()
    }

    override fun initData() {
        tv_regist.setOnClickListener {
            duage()
        }
        iv_back.setOnClickListener {
            finish()
        }
        tv_regist_login.setOnClickListener {
            finish()
        }


    }

    @SuppressLint("WrongConstant")
    private fun duage() {
        val phone : String = et_phone_number.text.toString()
        if (TextUtils.isEmpty(phone)) {
            tv_error_info.visibility = View.VISIBLE
            tv_error_info.text = "手机号不能为空"
            return
        }
        if (!phone.matches("^1[3,4,5,7,8,9]\\d{9}$".toRegex())) {
            tv_error_info.visibility = View.VISIBLE
            tv_error_info.text = "输入的手机号码不合法"
            return
        }

        startActivity(Intent(this,AVertionCode::class.java).putExtra("phone",phone))
    }

    override fun start() {
    }


}
