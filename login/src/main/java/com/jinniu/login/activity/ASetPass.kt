package com.jinniu.login.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.alibaba.android.arouter.launcher.ARouter
import com.jinniu.login.R
import com.lestin.yin.Constants
import com.lestin.yin.Constants.Companion.retxtpwd
import com.lestin.yin.base.ABase
import com.lestin.yin.utils.LogUtil
import com.lestin.yin.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_aset_pass.*

class ASetPass : ABase() {
    private var phone: String? = null

    override fun layoutId(): Int = R.layout.activity_aset_pass

    override fun initView() {
        phone = this.intent.getStringExtra("phone").toString()
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.transparent).init()
    }

    override fun initData() {
        tv_next_step.setOnClickListener {
            regist()
        }
        iv_back.setOnClickListener {
            finish()
        }
    }

    //点击注册
    private fun regist() {
        val textPass = et_pass.text.toString()
        if (TextUtils.isEmpty(textPass)) {
            ToastUtils.showShort("密码不能为空")
            return
        }
        if (!textPass.matches(retxtpwd.toRegex())) {
            //验证不通过
            ToastUtils.showShort("密码不规范")
            return
        }
        val disposable = mainModel.regist(phone!!, textPass, "").subscribe { result ->
            val code = result.code.toString()
            val content = result.content
            val status = result.status
            if (Constants.USER_EXIST_STATE.equals(code)) {
                ToastUtils.showShort("用户已存在")
            } else if(Constants.SUCCESS.equals(code)){
                spManager.save(Constants.USER_INFO, content.userInfo)
                spManager.save(Constants.TOKEN, result.content.token)
                ToastUtils.showShort("注册成功")
//             1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
                ARouter.getInstance().build("/app/home").navigation()
                finish()
            } else {
                ToastUtils.showShort("注册失败 请重试")
            }
        }

        addSubscription(disposable)


    }
    override fun start() {
    }
}
