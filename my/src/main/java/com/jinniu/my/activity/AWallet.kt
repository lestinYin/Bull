package com.jinniu.my.activity

import android.content.Intent
import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.jinniu.my.R
import com.jinniu.my.fragment.FMyAddress
import com.jinniu.my.fragment.FWallet
import com.lestin.yin.base.ABase
import com.lestin.yin.utils.SetIndicater
import com.lestin.yin.widget.CommonPagerAdapter
import com.lestin.yin.widget.listener.AppBarStateChangeListener
import kotlinx.android.synthetic.main.activity_awallet.*
import kotlinx.android.synthetic.main.activity_awith_draw.*
import java.util.ArrayList

class AWallet : ABase() {
    var titleText : TextView? = null

    override fun layoutId(): Int = R.layout.activity_awallet

    override fun initView() {
        titleText = TextView(this)
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.white).init()
        toolbar.setNavigationIcon(R.mipmap.my_back)
    }

    override fun initData() {
        val titlesDis = ArrayList<CharSequence>()
        titlesDis.add("全部")
        titlesDis.add("收入")
        titlesDis.add("支出")

        val fragmentsDis = ArrayList<Fragment>()
        fragmentsDis.add(FWallet.newInstance(titlesDis[0].toString()))
        fragmentsDis.add(FWallet.newInstance(titlesDis[1].toString()))
        fragmentsDis.add(FWallet.newInstance(titlesDis[2].toString()))

        setTitleCenter(toolbar,"余额")
        // 监听滚动状态
        app_bar.addOnOffsetChangedListener(object : AppBarStateChangeListener() {

            override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
                if (state === State.EXPANDED) {
                    //展开状态
//                    toolbar.setNavigationIcon(R.drawable.my_yaoqing)
                    if (titleText != null)
                        titleText!!.visibility = View.GONE
                } else if (state === State.COLLAPSED) {
                    //折叠状态
//                    toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material)
                    titleText!!.visibility = View.VISIBLE
                } else {
                    //中间状态
//                    toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material)
                }
            }
        })



        val adapterDis = CommonPagerAdapter(applicationContext, this.supportFragmentManager, fragmentsDis, titlesDis)
        vp_score.adapter = adapterDis
        tb_score.tabMode = TabLayout.MODE_FIXED
        tb_score.setupWithViewPager(vp_score)

        SetIndicater.setIndicator(tb_score)
        SetIndicater.setTabTextStyle(tb_score, true, 0)
        tb_score.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val position = tab.position
                //切换到review和Detail不显示购物车


                //选中加粗字体
                SetIndicater.setTabTextStyle(tb_score, true, position)
                vp_score.setCurrentItem(tab.position, false)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val position = tab.position
                //不选中细字体
                SetIndicater.setTabTextStyle(tb_score, false, position)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    override fun start() {
        tv_liji_withdraw.setOnClickListener {
            startActivity(Intent(this,AWithDraw::class.java))
        }
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }



    //设置标题居中
    private fun setTitleCenter(toolbar: Toolbar, title : String) {
        titleText!!.setTextColor(ContextCompat.getColor(applicationContext, R.color.color_333333))
//        titleText.setText(R.string.brvah_app_name)
        titleText!!.text = title
        titleText!!.textSize = 18f
        titleText!!.gravity = Gravity.CENTER
        titleText!!.visibility = View.GONE
        val layoutParams = Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER
        titleText!!.layoutParams = layoutParams
        toolbar.addView(titleText)
    }

}
