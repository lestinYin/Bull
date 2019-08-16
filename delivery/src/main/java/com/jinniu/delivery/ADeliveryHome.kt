package com.jinniu.delivery

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.jinniu.delivery.fragment.FDeliveryHome
import com.lestin.yin.base.ABase
import com.lestin.yin.utils.SetIndicater
import com.lestin.yin.utils.ViewUtil
import com.lestin.yin.widget.CommonPagerAdapter
import kotlinx.android.synthetic.main.activity_adelivery_home.*
import java.util.ArrayList
import android.support.v4.widget.NestedScrollView
import android.view.View
import android.widget.RadioGroup
import com.lestin.yin.utils.LogUtil
import kotlinx.android.synthetic.main.bar_delivery_home.*
import kotlinx.android.synthetic.main.include_delivery_choice.*
import kotlinx.android.synthetic.main.fragment_delivery_home.*


@Route(path = "/delivery/home")
class ADeliveryHome : ABase() {
    internal var toolBarPositionY = 0

    override fun layoutId(): Int = R.layout.activity_adelivery_home

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.transparent).init()
    }

    override fun initData() {
        val titlesDis = ArrayList<CharSequence>()
        titlesDis.add("附近商家")
        titlesDis.add("精品推荐")

        val fragmentsDis = ArrayList<Fragment>()
        fragmentsDis.add(FDeliveryHome.newInstance(titlesDis[0].toString()))
        fragmentsDis.add(FDeliveryHome.newInstance(titlesDis[1].toString()))


        val adapterDis = CommonPagerAdapter(applicationContext, this.supportFragmentManager, fragmentsDis, titlesDis)
        vp_delivery.adapter = adapterDis
        tb_delivery.tabMode = TabLayout.MODE_SCROLLABLE
        tb_delivery.setupWithViewPager(vp_delivery)

        SetIndicater.setIndicator(tb_delivery)
        SetIndicater.setTabTextStyle(tb_delivery, true, 0)
        tb_delivery.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val position = tab.position
                //选中加粗字体
                SetIndicater.setTabTextStyle(tb_delivery, true, position)
                vp_delivery.setCurrentItem(tab.position, false)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val position = tab.position
                //不选中细字体
                SetIndicater.setTabTextStyle(tb_delivery, false, position)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
        //重绘viewpager高度
        tb_delivery.post(Runnable {

            toolBarPositionY = tb_delivery.getHeight()
            val params = vp_delivery.getLayoutParams()
            ViewUtil
            params.height = ViewUtil.getScreenHeight(applicationContext) - toolBarPositionY - tb_delivery.height - tool_bar_delivery_home.height - ViewUtil.dp2px(15) + 2
            vp_delivery.setLayoutParams(params)

        })

        dealData()


    }

    //deal with
    private fun dealData() {
        scroll_delivery.setOnScrollChangeListener(object : NestedScrollView.OnScrollChangeListener {
            override fun onScrollChange(v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
                val location = IntArray(2)

                val locations = IntArray(2)
                //筛选位置
                tb_delivery.getLocationOnScreen(location)
                //标题title位置
                tool_bar_delivery_home.getLocationOnScreen(locations)
                var yIncludeChoice: Int = location[1]

                var yTitle: Int = locations[1] + tool_bar_delivery_home.height

                val gapHeight = ViewUtil.dp2px(2) + 1

//              滑到底部  固定tab
                if (yIncludeChoice - gapHeight < yTitle) {
                    recylerView.isNestedScrollingEnabled = true//禁止滑动
                    scroll_delivery.setNeedScroll(false)

                } else {
                    recylerView.isNestedScrollingEnabled = false//禁止滑动
                    scroll_delivery.setNeedScroll(true)
                }
            }

        })



    }

    override fun start() {


    }


}
