package com.jinniu.my.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jinniu.my.R
import com.lestin.yin.base.ABase
import kotlinx.android.synthetic.main.activity_aadd_bank_card.*
import kotlinx.android.synthetic.main.include_card_input.view.*

class AAddBankCard : ABase() {
    override fun layoutId(): Int = R.layout.activity_aadd_bank_card

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.white).init()

        include_id_card.tv_name.text = "身份证"
        include_card_number.tv_name.text = "卡号"
        include_phone_number.tv_name.text = "手机号"
        include_verfi_code.tv_name.text = "验证码"

        include_id_card.et_hint_message.hint = "请输入身份证号码"
        include_card_number.et_hint_message.hint = "请输入银行卡号"
        include_phone_number.et_hint_message.hint = "请输入银行预留手机号"
        include_verfi_code.et_hint_message.hint = "请输入验证码"

        include_verfi_code.tv_verfication_code.visibility = View.VISIBLE
    }

    override fun initData() {
        iv_back.setOnClickListener {
            finish()
        }
    }

    override fun start() {
    }


}
