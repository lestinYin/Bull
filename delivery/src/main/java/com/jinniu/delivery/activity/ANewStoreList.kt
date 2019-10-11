package com.jinniu.delivery.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.jinniu.delivery.R
import com.jinniu.delivery.fragment.FChoiceFood
import com.lestin.yin.base.ABase

@Route(path = "/delivery/foodList")
class ANewStoreList : ABase() {
    private var type: Int = 0

    override fun layoutId(): Int = R.layout.activity_common_fragment

    override fun initView() {

        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.colorPrimary).init()

        var fMyAddress: Fragment = FChoiceFood()

        val message = Bundle()
        fMyAddress.setArguments(message)
        supportFragmentManager.beginTransaction().replace(R.id.frame, fMyAddress).commit()
    }

    override fun initData() {
        var back = findViewById<ImageView>(R.id.iv_back)
        back.setOnClickListener {
            finish()
        }
    }

    override fun start() {
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        var title = findViewById<TextView>(R.id.tv_title)
        toolbar.title = ""
        toolbar.setBackgroundColor(resources.getColor(R.color.colorPrimary))

        title.text = "张三的店"


//        var rightImage = findViewById<ImageView>(R.id.iv_rigth)
//        rightImage.visibility = View.VISIBLE
//        rightImage.background = resources.getDrawable(R.mipmap.black_clock)
    }


}