package com.future.taurus.ui.home.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.future.taurus.MyApplication
import com.future.taurus.R
import com.lestin.yin.base.ABase
import kotlinx.android.synthetic.main.activity_ajubao_store.*

class AJubaoDaren : ABase() {
    val titleText = TextView(MyApplication.context)

    override fun layoutId(): Int = R.layout.activity_ajubao_daren

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.color_f3f3f3).init()
        toolbar.title = ""
        setSupportActionBar(toolbar)
        //无接口时
        setTitleCenter(toolbar,"举报达人")
    }

    override fun initData() {
    }

    override fun start() {
    }

    //设置标题居中
    private fun setTitleCenter(toolbar: Toolbar, title : String) {
        titleText.setTextColor(ContextCompat.getColor(applicationContext, R.color.color_333333))
//        titleText.setText(R.string.brvah_app_name)
        titleText.text = title
        titleText.textSize = 18f
        titleText.gravity = Gravity.CENTER
        titleText.visibility = View.VISIBLE
        val layoutParams = Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER
        titleText.layoutParams = layoutParams
        toolbar.addView(titleText)
    }


}
