package com.future.taurus.ui.home

import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.*
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.future.taurus.R
import com.future.taurus.ui.home.fragment.FHome
import com.lestin.yin.base.ABase
import com.lestin.yin.utils.SetIndicater
import com.lestin.yin.utils.ToastUtils
import com.lestin.yin.utils.ViewUtil
import com.lestin.yin.widget.CommonPagerAdapter
import kotlinx.android.synthetic.main.activity_atop.*
import java.util.ArrayList

/**
 * @Description: 主页
 * @Author: Lestin.Yin
 * @CreateDate: 2019/7/23 11:56
 * @Version: 1.0
 */
@Route(path = "/app/top")
class ATop : ABase() {
    private var mAlertDialog: AlertDialog? = null
    private var mDialogWindow: Window? = null

    override fun layoutId(): Int = R.layout.activity_atop

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.transparent).init()
    }

    override fun initData() {
        val titlesDis = ArrayList<CharSequence>()
        titlesDis.add(" KTV ")
        titlesDis.add("酒吧")
        titlesDis.add("餐厅")
        titlesDis.add("按摩/洗浴")
        titlesDis.add("夜景/夜市")
        titlesDis.add("赌场")
        ViewUtil
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

        iv_home_my.setOnClickListener {
            // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
            ARouter.getInstance().build("/my/setting").navigation()
        }


    }

    override fun start() {
        showIntegralDialog()

    }

    private fun showIntegralDialog() {
        //弹出Dialog
        val builder = AlertDialog.Builder(this, R.style.dialogNeed)
        mAlertDialog = builder.create()
        mAlertDialog!!.show()
        val dialogView = LayoutInflater.from(this).inflate(R.layout.popu_delivery_home_choice, null)
        dialogView.setOnClickListener {
            mAlertDialog!!.dismiss()
        }

        mAlertDialog!!.setContentView(dialogView)
        mDialogWindow = mAlertDialog!!.getWindow()
        mDialogWindow!!.setWindowAnimations(R.style.alphaAnimation)
        mDialogWindow!!.setGravity(Gravity.CENTER)
        mDialogWindow!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        mAlertDialog!!.setCanceledOnTouchOutside(true)


    }
    //显示积分弹框
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    fun showIntegral(event:Event.HomeShowDialog) {
//        LogUtil.e("showshow---------------")
//        showIntegralDialog()
//    }


    private var mExitTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
//                var app = MyApplication()
//                app.instance().exitApp()
                sendBroadcast(Intent(ACTION_FINISH))
            } else {
                mExitTime = System.currentTimeMillis()
                ToastUtils.showShort("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}
