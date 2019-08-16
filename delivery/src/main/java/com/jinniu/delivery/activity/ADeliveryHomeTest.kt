package com.jinniu.delivery.activity

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.jinniu.delivery.fragment.FDeliveryHome
import com.lestin.yin.base.ABase
import com.lestin.yin.utils.SetIndicater
import com.lestin.yin.widget.CommonPagerAdapter
import java.util.ArrayList
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AlertDialog
import android.view.*
import com.lestin.yin.widget.listener.AppBarStateChangeListener
import kotlinx.android.synthetic.main.activity_adelivery_home.tb_delivery
import kotlinx.android.synthetic.main.activity_adelivery_home.vp_delivery
import kotlinx.android.synthetic.main.activity_adelivery_home_test.*
import kotlinx.android.synthetic.main.bar_delivery_home.*
import kotlinx.android.synthetic.main.include_delivery_choice.*
import android.widget.PopupWindow
import android.widget.TextView
import android.os.Handler
import com.jinniu.delivery.R


@Route(path = "/delivery/hometest")
class ADeliveryHomeTest : ABase() {
    private var mAlertDialog: AlertDialog? = null
    private var mDialogWindow: Window? = null
    internal var toolBarPositionY = 0
    private lateinit var popupWindow: PopupWindow;
    private var isShowPopu = false


    override fun layoutId(): Int = R.layout.activity_adelivery_home_test

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
        dealBar()
    }

    private fun dealBar() {
        // 监听滚动状态
        app_bar.addOnOffsetChangedListener(object : AppBarStateChangeListener() {

            override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
                if (state === State.EXPANDED) {
                    tool_bar_delivery_home_one.visibility = View.VISIBLE
                    tool_bar_delivery_home_two.visibility = View.GONE
                    //展开状态
                } else if (state === State.COLLAPSED) {
                    tool_bar_delivery_home_one.visibility = View.GONE
                    tool_bar_delivery_home_two.visibility = View.VISIBLE
                    if (isShowPopu) {

                    }
                    //折叠状态
                } else {
                    //中间状态
//                    toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material)
                }
            }
        })

        radio_group.setOnCheckedChangeListener { group, checkedId ->
            //            include_delivery_top.visibility = View.GONE
            isShowPopu = true
            app_bar.setExpanded(false)
            popupWindow.showAsDropDown(include_choice)
            // 延迟15秒
            // do something
        }
    }

    //创建dialog
    private fun createDialogs() {
        //准备PopupWindow的布局
        var popupView = LayoutInflater.from(this).inflate(R.layout.popu_delivery_home_choice, null)
        val finish = popupView.findViewById<TextView>(R.id.delivery_popu_finish)
        // 初始化一个PopupWindow，width和height都是WRAP_CONTENT
        popupWindow = PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//设置PopupWindow的视图内容
        popupWindow.contentView = popupView;
        //点击空白区域PopupWindow消失，这里必须先设置setBackgroundDrawable，否则点击无反应
        popupWindow.setBackgroundDrawable(resources.getDrawable(R.color.transparent));
        popupWindow.isOutsideTouchable = false;
        //设置PopupWindow动画popupWindow.setAnimationStyle(R.style.AnimDown);
        // 设置是否允许PopupWindow的范围超过屏幕范围
        popupWindow.isClippingEnabled = false

//        popupWindow.showAsDropDown(include_choice)

        finish.setOnClickListener {
            isShowPopu = false
            popupWindow.dismiss()
        }

    }

    override fun start() {
        createDialogs()

    }

    /**
     * 控制appbar的滑动
     * @param isScroll true 允许滑动 false 禁止滑动
     */
    private fun banAppBarScroll(isScroll: Boolean) {
        val mAppBarChildAt = app_bar.getChildAt(0)
        val mAppBarParams = mAppBarChildAt.getLayoutParams() as AppBarLayout.LayoutParams
        if (isScroll) {
            mAppBarParams.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
            mAppBarChildAt.setLayoutParams(mAppBarParams)
        } else {
            mAppBarParams.scrollFlags = 0
        }

    }

}
