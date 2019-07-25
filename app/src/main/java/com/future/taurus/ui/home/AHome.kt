package com.future.taurus.ui.home

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import com.future.taurus.R
import com.future.taurus.base.ABase
import com.future.taurus.ui.home.adapter.CommonPagerAdapter
import com.future.taurus.ui.home.fragment.FHome
import com.future.taurus.utils.SetIndicater
import kotlinx.android.synthetic.main.activity_ahome.*
import java.util.ArrayList

/**
 * @Description: 主页
 * @Author: Lestin.Yin
 * @CreateDate: 2019/7/23 11:56
 * @Version: 1.0
 */

class AHome : ABase() {
    override fun layoutId(): Int {
        return R.layout.activity_ahome
    }

    override fun initView() {
        mImmersionBar!!.statusBarView(top_white).init()
    }

    override fun initData() {
        val titlesDis = ArrayList<CharSequence>()
        titlesDis.add(" KTV ")
        titlesDis.add("酒吧")
        titlesDis.add("餐厅")
        titlesDis.add("按摩/洗浴")
        titlesDis.add("夜景/夜市")
        titlesDis.add("赌场")
        val fragmentsDis = ArrayList<Fragment>()
        fragmentsDis.add(FHome.newInstance(titlesDis[0].toString()))
        fragmentsDis.add(FHome.newInstance(titlesDis[1].toString()))
        fragmentsDis.add(FHome.newInstance(titlesDis[2].toString()))
        fragmentsDis.add(FHome.newInstance(titlesDis[3].toString()))
        fragmentsDis.add(FHome.newInstance(titlesDis[4].toString()))
        fragmentsDis.add(FHome.newInstance(titlesDis[5].toString()))

        val adapterDis = CommonPagerAdapter(applicationContext, this.supportFragmentManager, fragmentsDis, titlesDis)
        vp.adapter = adapterDis
        lay_tabs.tabMode = TabLayout.MODE_SCROLLABLE
        lay_tabs.setupWithViewPager(vp)


        SetIndicater.setIndicator(lay_tabs)
        SetIndicater.setTabTextStyle(lay_tabs, true, 0)
        lay_tabs.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val position = tab.position
                //选中加粗字体
                SetIndicater.setTabTextStyle(lay_tabs, true, position)
                vp.setCurrentItem(tab.position, false)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val position = tab.position
                //不选中细字体
                SetIndicater.setTabTextStyle(lay_tabs, false, position)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })


    }

    override fun start() {


    }
}
