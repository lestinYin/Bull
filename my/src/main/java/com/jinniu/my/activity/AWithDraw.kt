package com.jinniu.my.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jinniu.my.R
import com.lestin.yin.base.ABase
import kotlinx.android.synthetic.main.activity_awith_draw.*

/**
 * 提现页面
 */
class AWithDraw : ABase() {
    var isDuihuan = false

    override fun layoutId(): Int = R.layout.activity_awith_draw

    override fun initView() {
        isDuihuan = intent.getBooleanExtra("isDuihuan",false)
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.transparent).init()
        if(isDuihuan) {
            tv_title.text = "兑出"
            tv_all_withdraw.text = "全部兑出"
            tv_withdraw_dollar.text = "收取17%手续费"
            tv_tixian.text = "确认兑换"
            tv_xieyi.visibility = View.GONE
            rl_duihuan_niu.visibility = View.VISIBLE

        }

    }

    override fun initData() {
        iv_back.setOnClickListener {
            finish()
        }
        tv_bind_card.setOnClickListener {
            startActivity(Intent(this,AMyCard::class.java))
        }
    }

    override fun start() {
    }


}
