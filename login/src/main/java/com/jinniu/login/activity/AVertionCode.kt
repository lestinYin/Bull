package com.jinniu.login.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.CountDownTimer
import android.text.TextUtils
import android.view.*
import android.widget.EditText
import com.jinniu.login.R
import com.lestin.yin.base.ABase
import com.lestin.yin.widget.VerificationCodeView
import kotlinx.android.synthetic.main.activity_avertion_code.*
import android.view.inputmethod.InputMethodManager as InputMethodManager



/**
 * 获取验证码
 */
class AVertionCode : ABase() {
    var phone : String = ""
    var code : String = ""
    var getmEtOne : EditText? = null
    var timeCount : TimeCount ? = null

    override fun layoutId(): Int = R.layout.activity_avertion_code

    @SuppressLint("SetTextI18n")
    override fun initView() {

        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.transparent).init()

        phone = this.intent.getStringExtra("phone")

        getmEtOne = verification_codeview.getmEtOne()

        tv_tishi_message.text = "短信验证码已发送至+86 "+phone

        //显示软键盘
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);


        timeCount = TimeCount(60000, 1000)
        timeCount!!.start()
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
        iv_back.setOnClickListener {
            finish()

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


    //计时器
    inner class TimeCount(millisInFuture: Long, countDownInterval: Long)//参数依次为总时长,和计时的时间间隔
        : CountDownTimer(millisInFuture, countDownInterval) {

        //计时过程显示
        override fun onTick(millisUntilFinished: Long) {
            tv_second_time.text = "(" + millisUntilFinished / 1000 + "秒)"
        }
        override fun onFinish() {
            tv_second_time.text = "重新发送"
        }
    }


}
