package com.jinniu.delivery.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.jinniu.delivery.R
import com.lestin.yin.base.ABase
import kotlinx.android.synthetic.main.activity_axuan_gui_ge.*

class AXuanGuiGe : ABase() {
    val mSpeList :Array<String> = arrayOf("不辣","微辣","中辣","特辣")
    var choiceList: ArrayList<TextView> = ArrayList()

    override fun layoutId(): Int = R.layout.activity_axuan_gui_ge

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.transparent).init()
    }

    override fun initData() {
        root_choice_speci.setOnClickListener {
            onBackPressed()
        }

        fl_speci.removeAllViews()
        val mInflater = LayoutInflater.from(this)
        for (label in mSpeList) {
            val tv = mInflater.inflate(R.layout.tv_specification,
                    fl_speci, false) as TextView
            tv.text = label
            tv.setOnClickListener(View.OnClickListener {
                if (choiceList.size > 0) {
                    choiceList[0].setBackgroundResource(R.drawable.shape_specification_false_bg)
                    choiceList[0].setTextColor(resources.getColor(R.color.color_999999))
                }
                choiceList.clear()
                tv.setTextColor(resources.getColor(R.color.colorPrimary))
                tv.setBackgroundResource(R.drawable.shape_specification_true_bg)
                choiceList.add(tv)
                tv_selected.text = "Selected:"+tv.text.toString()
            })
            fl_speci.addView(tv)
        }
    }

    override fun start() {
    }
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }


}
