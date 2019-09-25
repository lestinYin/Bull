package com.jinniu.my.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import com.jinniu.my.R
import com.jinniu.my.fragment.*
import com.lestin.yin.base.ABase
import com.lestin.yin.utils.SetIndicater
import com.lestin.yin.widget.CommonPagerAdapter
import kotlinx.android.synthetic.main.activity_acoupon.*
import kotlinx.android.synthetic.main.activity_awallet.*
import java.util.ArrayList

/**
 * 优惠卷页面
 */

class ACoupon : ABase() {
    var type = ""  //是否是积分明细
    override fun layoutId(): Int = R.layout.activity_acoupon

    override fun initView() {
        type = intent.getStringExtra("type")

        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.white).init()

        val titlesDis = ArrayList<CharSequence>()
        val fragmentsDis = ArrayList<Fragment>()
        when (type) {
            "integralDetail" ->{
                tv_title.text = "积分明细"
                titlesDis.add("全部")
                titlesDis.add("获取")
                titlesDis.add("使用")
                fragmentsDis.add(FIntegralDetail.newInstance(titlesDis[0].toString()))
                fragmentsDis.add(FIntegralDetail.newInstance(titlesDis[1].toString()))
                fragmentsDis.add(FIntegralDetail.newInstance(titlesDis[2].toString()))
            }
            "coupon" -> {
                titlesDis.add("满减劵")
                titlesDis.add("已失效")

                fragmentsDis.add(FCoupon.newInstance(titlesDis[0].toString()))
                fragmentsDis.add(FCoupon.newInstance(titlesDis[1].toString()))
            }
            "like" -> {
                titlesDis.add("店铺")
                titlesDis.add("攻略")

                fragmentsDis.add(FLikeStore.newInstance(titlesDis[0].toString()))
                fragmentsDis.add(FLikeRaiders.newInstance(titlesDis[1].toString()))
            }

        }





        val adapterDis = CommonPagerAdapter(applicationContext, this.supportFragmentManager, fragmentsDis, titlesDis)
        vp_coupon.adapter = adapterDis
        tb_coupon.tabMode = TabLayout.GRAVITY_CENTER
        tb_coupon.setupWithViewPager(vp_coupon)

        SetIndicater.setIndicator(tb_coupon)
        SetIndicater.setTabTextStyle(tb_coupon, true, 0)
        tb_coupon.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val position = tab.position
                //切换到review和Detail不显示购物车


                //选中加粗字体
                SetIndicater.setTabTextStyle(tb_coupon, true, position)
                vp_coupon.setCurrentItem(tab.position, false)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val position = tab.position
                //不选中细字体
                SetIndicater.setTabTextStyle(tb_coupon, false, position)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    override fun initData() {
        iv_back.setOnClickListener {
            finish()
        }
    }

    override fun start() {
     }
}
