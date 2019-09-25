package com.jinniu.my.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jinniu.my.R
import com.lestin.yin.base.ABase
import kotlinx.android.synthetic.main.activity_asetting.*

class ASetting : ABase() {
    override fun layoutId(): Int = R.layout.activity_asetting

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.transparent).init()
    }

    override fun initData() {
        rl_account_safe.setOnClickListener {
            startActivity(Intent(this,AAcountSafe::class.java))
        }
        rl_feed_back.setOnClickListener {
            startActivity(Intent(this,AFeedBack::class.java))
        }
        rl_aboat_us.setOnClickListener {
            startActivity(Intent(this,AAboatUs::class.java))
        }
        iv_back.setOnClickListener {
            finish()
        }
    }

    override fun start() {
    }


}
