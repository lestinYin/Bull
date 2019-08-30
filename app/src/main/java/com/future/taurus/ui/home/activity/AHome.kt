package com.future.taurus.ui.home.activity

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.lestin.yin.base.ABase
import com.lestin.yin.utils.ToastUtils
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import kotlinx.android.synthetic.main.activity_ahome.*
import java.util.ArrayList
import android.widget.Toast
import android.support.annotation.NonNull
import com.lestin.yin.utils.LogUtil
import android.support.v4.app.ActivityCompat.shouldShowRequestPermissionRationale
import com.future.taurus.R
import com.tbruyelle.rxpermissions2.RxPermissions
import com.tbruyelle.rxpermissions2.Permission
import io.reactivex.functions.Consumer


/**
 * 主页
 */
@Route(path = "/app/home")
class AHome : ABase() {


    val info = arrayOf("汇兑信息", "安全电话", "应急卡", "小费助手", "电话充值", "帮助中心")
    val iconImg = arrayOf(R.mipmap.home_page_three_icon_1, R.mipmap.home_page_three_icon_2, R.mipmap.home_page_three_icon_3, R.mipmap.home_page_three_icon_4, R.mipmap.home_page_three_icon_5, R.mipmap.home_page_three_icon_6)


    override fun layoutId(): Int = R.layout.activity_ahome

    override fun initView() {
        //获取权限
        testRxPermission()

        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.transparent).init()
        rv_home_page_three_icon.layoutManager = GridLayoutManager(this, 3,
                LinearLayoutManager.VERTICAL, false)
    }

    override fun initData() {
        setTools()

        ll_home_page_two.setOnClickListener {
            ARouter.getInstance().build("/app/top").navigation()
        }
        ll_home_page_one.setOnClickListener {
            ARouter.getInstance().build("/delivery/hometest").navigation()
        }

        tv_home_location.setOnClickListener {
            startActivityForResult(Intent(this, AChoiceCity::class.java), 1)
        }

    }

    override fun start() {
    }

    /**
     * 设置工具集
     */
    private fun setTools() {
        var adapter = object : CommonAdapter<String>(this, R.layout.item_home_page_three_icon, info.toMutableList()) {
            override fun convert(holder: ViewHolder?, t: String?, position: Int) {
                holder!!.getView<ImageView>(com.future.taurus.R.id.iv_icon_bg).background = getDrawable(iconImg[position])


            }
        }
        rv_home_page_three_icon.adapter = adapter
    }

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

    /**
     * 测试：RxPermission
     * https://github.com/tbruyelle/RxPermissions
     */
    @SuppressLint("CheckResult")
    private fun testRxPermission() {
        val rxPermissions = RxPermissions(this) // where this is an Activity instance

        rxPermissions
                .requestEach(
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe { permission ->

                    if (permission.granted) {
                        LogUtil.e("testRxPermission CallBack onPermissionsGranted() : " + permission.name +
                                " request granted , to do something...")
                        //todo somthing
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        LogUtil.e("testRxPermission CallBack onPermissionsDenied() : " + permission.name + "request denied")
                        //                            ToastUtil.showShort(instance, "拒绝权限，等待下次询问哦")
                        //todo request permission again
                    } else {
                        LogUtil.e("testRxPermission CallBack onPermissionsDenied() : this " + permission.name + " is denied " +
                                "and never ask again")
                        //                            ToastUtil.showShort(instance, "拒绝权限，不再弹出询问框，请前往APP应用设置中打开此权限")
                        //todo nothing
                    }
                }

    }


}
