package com.jinniu.login.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jinniu.login.R
import com.lestin.yin.Constants
import com.lestin.yin.base.ABase
import com.lestin.yin.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_test.*


@Route(path = "/login/login")
class ALogin : ABase() {


    override fun layoutId(): Int = R.layout.activity_test

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.transparent).init()

    }

    override fun initData() {
        tv_regist.setOnClickListener {
            var  intent = Intent(applicationContext, ARegister::class.java)
            startActivity(intent)
        }
        tv_login.setOnClickListener {
            duageLogin()
        }
    }
    //验证登陆
    private fun duageLogin() {
        val userName = et_user_name.text.toString()
        val userPass = et_user_pass.text.toString()
        if (TextUtils.isEmpty(userName)) {
            ToastUtils.showShort("账号不能为空")
            return
        }
        if (TextUtils.isEmpty(userPass)) {
            ToastUtils.showShort("密码不能为空")
            return
        }
        val disposable = mainModel.login(userName!!, userPass, "").subscribe { result ->
            val code = result.code.toString()
            val content = result.content
            val status = result.status
            if (Constants.SUCCESS.equals(code)) {
                spManager.save(Constants.USER_INFO, content.userInfo)
                spManager.save(Constants.TOKEN, result.content.token)
                ToastUtils.showShort(status)
//             1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
                ARouter.getInstance().build("/app/home").navigation()
                finish()
            } else {
                ToastUtils.showShort(status)
            }
        }

        addSubscription(disposable)
    }

    override fun start() {
    }


}
