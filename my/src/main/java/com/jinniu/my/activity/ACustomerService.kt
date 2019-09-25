package com.jinniu.my.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jinniu.my.R
import com.lestin.yin.base.ABase
import kotlinx.android.synthetic.main.activity_acustomer_service.*

class ACustomerService : ABase() {
    override fun layoutId(): Int = R.layout.activity_acustomer_service

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.white).init()
    }

    override fun initData() {
        iv_back.setOnClickListener {
            finish()
        }
        miv_normal_question.setOnClickListener{
            startActivity(Intent(this,AQuestion::class.java).putExtra("question",0))
        }
        miv_pay.setOnClickListener{
            startActivity(Intent(this,AQuestion::class.java).putExtra("question",1))
        }
        miv_delivery.setOnClickListener{
            startActivity(Intent(this,AQuestion::class.java).putExtra("question",2))
        }
        miv_other_question.setOnClickListener{
            startActivity(Intent(this,AQuestion::class.java).putExtra("question",3))
        }
    }

    override fun start() {
    }


}
