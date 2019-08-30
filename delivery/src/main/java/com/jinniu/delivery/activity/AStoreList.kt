package com.jinniu.delivery.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.View
import android.widget.LinearLayout
import com.jinniu.delivery.R
import com.jinniu.delivery.fragment.FDeliveryHome
import com.jinniu.delivery.fragment.FStoreList
import com.lestin.yin.base.ABase
import com.lestin.yin.utils.SetIndicater
import com.lestin.yin.widget.CommonPagerAdapter
import kotlinx.android.synthetic.main.activity_adelivery_home.*
import kotlinx.android.synthetic.main.activity_astore_list.*
import kotlinx.android.synthetic.main.bar_delivery_home.*
import kotlinx.android.synthetic.main.fragment_store_list.*
import java.util.ArrayList

/**
  * @ProjectName:    ${PACKAGE_NAME}
  * @Package:
  * @ClassName:      
  * @Description:     
  * @Author:         lestin.yin
  * @CreateDate:      
  * @Version:        1.0
 */
class AStoreList : ABase() {


    override fun layoutId(): Int = R.layout.activity_astore_list

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.transparent).init()
        tool_bar_delivery_home_one.visibility = View.GONE
        tool_bar_delivery_home_two.visibility = View.VISIBLE
        tv_category.visibility = View.VISIBLE
    }

    override fun initData() {
        val tabList = resources.getStringArray(R.array.tab_store_list)


        val fragmentsDis = ArrayList<Fragment>()
        fragmentsDis.add(FStoreList.newInstance(tabList[0].toString()))
        fragmentsDis.add(FStoreList.newInstance(tabList[1].toString()))
        fragmentsDis.add(FStoreList.newInstance(tabList[2].toString()))


        val adapterDis = CommonPagerAdapter(applicationContext, this.supportFragmentManager, fragmentsDis, tabList.toMutableList())
        vp_store_list.adapter = adapterDis
        tb_store_list.tabMode = TabLayout.MODE_FIXED
        tb_store_list.setupWithViewPager(vp_store_list)

        SetIndicater.setIndicator(tb_store_list)
        SetIndicater.setTabTextStyle(tb_store_list, true, 0)
        tb_store_list.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val position = tab.position
                //选中加粗字体
                SetIndicater.setTabTextStyle(tb_store_list, true, position)
                vp_store_list.setCurrentItem(tab.position, false)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val position = tab.position
                //不选中细字体
                SetIndicater.setTabTextStyle(tb_store_list, false, position)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    override fun start() {
        iv_back.setOnClickListener {
            finish()
        }
    }



}
