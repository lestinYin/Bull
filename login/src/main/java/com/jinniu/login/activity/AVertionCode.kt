package com.jinniu.login.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.*
import android.widget.EditText
import com.jinniu.login.R
import com.lestin.yin.base.ABase
import com.lestin.yin.widget.VerificationCodeView
import kotlinx.android.synthetic.main.activity_alogin.*
import kotlinx.android.synthetic.main.activity_avertion_code.*
import android.view.inputmethod.InputMethodManager as InputMethodManager



/**
 * 获取验证码
 */
class AVertionCode : ABase() {
    var phone : String = ""
    var code : String = ""
    var getmEtOne : EditText? = null

    override fun layoutId(): Int = R.layout.activity_avertion_code

    override fun initView() {

        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.transparent).init()

        phone = this.intent.getStringExtra("phone")

        getmEtOne = verification_codeview.getmEtOne()

        //显示软键盘
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);


    }

    @SuppressLint("WrongConstant")
    override fun initData() {
        verification_codeview.setOnCodeFinishListener(object : VerificationCodeView.OnCodeFinishListener{

            override fun onComplete(content: String) {

                tv_vertion_error.visibility = View.GONE
                code = content
                //同时关闭软键盘
                closeKeyBord(getmEtOne!!,applicationContext)
            }

        })



        tv_next_step.setOnClickListener {
            juadgeCode()

        }


    }
    //点击判断验证码是否正确
    private fun juadgeCode() {
        if (TextUtils.isEmpty(code)) {
            tv_vertion_error.visibility = View.VISIBLE
            tv_vertion_error.text = "验证码不能为空"
            return
        }
        if ("888888".equals(code)){
            var  intent = Intent(applicationContext, ASetPass::class.java)
            intent.putExtra("phone",phone)
            startActivity(intent)
        } else {
            tv_vertion_error.visibility = View.VISIBLE
            tv_vertion_error.text = "验证码不正确"
            return
        }
    }

    override fun start() {
    }




}
