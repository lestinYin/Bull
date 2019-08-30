package com.jinniu.delivery.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jinniu.delivery.R
import com.jinniu.delivery.fragment.FoodContent
import com.lestin.yin.base.ABase
import com.lestin.yin.utils.image.ShowImage
import kotlinx.android.synthetic.main.activity_afood_detail.*

class AFoodDetail : ABase() {

    override fun layoutId(): Int = R.layout.activity_afood_detail

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.transparent).init()

        var food = intent.getSerializableExtra("food") as FoodContent
        ShowImage.showRes(this,food.icon,iv_food_pic,30)
//        ShowImage.showRes(this,3343,iv_food_pic,100)
    }

    override fun initData() {
        root_food_detail.setOnClickListener {
            onBackPressed()
        }
    }

    override fun start() {
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.anim_botteom_silent, R.anim.fade_out)
    }


}
