package com.jinniu.my.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jinniu.my.R
import com.lestin.yin.base.ABase
import kotlinx.android.synthetic.main.activity_amy_card.*

/**
 * 我的银行卡页面
 *
 */
class AMyCard : ABase() {
    override fun layoutId(): Int = R.layout.activity_amy_card

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.white).init()
    }

    override fun initData() {

        var cityAdapter = CardAdapter(arrayOf("kk","").toMutableList())
        recycler_card_list.adapter = cityAdapter
    }

    override fun start() {
        iv_back.setOnClickListener {
            finish()
        }
        rl_add_card.setOnClickListener {
            startActivity(Intent(this,AAddBankCard::class.java))
        }
    }

}

//城市列表
class CardAdapter(data: List<String>) : BaseQuickAdapter<String, BaseViewHolder>(data) {

    init {
        mLayoutResId = R.layout.item_my_card
    }
    override fun convert(helper: BaseViewHolder?, item: String?) {

    }

}
