package com.jinniu.my.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jinniu.my.R
import com.jinniu.my.fragment.FMyAddress
import com.jinniu.my.fragment.FMyComment
import com.lestin.yin.base.ABase
import kotlinx.android.synthetic.main.activity_acomment.*

/**
 * 评论页面
 */
class AComment : ABase() {

    override fun layoutId(): Int = R.layout.activity_acomment

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.transparent).init()

        var fMyComment = FMyComment()
        val message = Bundle()
        fMyComment.setArguments(message)
        supportFragmentManager.beginTransaction().replace(R.id.frame, fMyComment).commit()
    }

    override fun initData() {
        iv_back.setOnClickListener {
            finish()
        }
    }

    override fun start() {
    }


}
