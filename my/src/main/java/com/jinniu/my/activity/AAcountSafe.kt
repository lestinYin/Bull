package com.jinniu.my.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jinniu.my.R
import com.lestin.yin.base.ABase
import kotlinx.android.synthetic.main.activity_aacount_safe.*

/**
 * 账号安全页
 */
class AAcountSafe : ABase() {
    override fun layoutId(): Int = R.layout.activity_aacount_safe

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.transparent).init()
    }

    override fun initData() {
        rl_mianmi.setOnClickListener {
            startActivity(Intent(this,AXiaoEPay::class.java))
        }
        iv_back.setOnClickListener {
            finish()
        }
    }

    override fun start() {
    }


}
