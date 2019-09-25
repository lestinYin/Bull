package com.jinniu.my.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jinniu.my.R
import com.lestin.yin.base.ABase
import kotlinx.android.synthetic.main.activity_axiao_epay.*

/**
 * 小额免密支付页面
 */
class AXiaoEPay : ABase() {
    override fun layoutId(): Int = R.layout.activity_axiao_epay

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.white).init()

    }

    override fun initData() {
        iv_back.setOnClickListener {
            finish()
        }
    }

    override fun start() {
    }


}
