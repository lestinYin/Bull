package com.future.taurus.ui.login

import android.content.Intent
import android.view.WindowManager
import com.future.taurus.R
import com.future.taurus.ui.home.AHome
import com.lestin.yin.base.ABase
import com.lestin.yin.utils.statubar.BarHide


class ASplash : ABase() {
    override fun layoutId(): Int {
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        return R.layout.activity_asplash
    }

    override fun initView() {
        if (mImmersionBar!!.hasNavigationBar(this)) {
            mImmersionBar!!.hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR).init()
            mImmersionBar!!.navigationBarColor(R.color.transparent).init()
        }
    }

    override fun initData() {
        android.os.Handler().postDelayed({
            startActivity(Intent(applicationContext, AHome::class.java))
            finish()
        }, 2000)

    }

    override fun start() {
    }


}
