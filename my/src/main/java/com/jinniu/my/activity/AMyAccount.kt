package com.jinniu.my.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jinniu.my.R
import com.lestin.yin.Constants
import com.lestin.yin.activity.ACutImg
import com.lestin.yin.base.ABase
import com.lestin.yin.utils.LogUtil
import com.lestin.yin.utils.image.ShowImage
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_amy_account.*
import java.io.File

/**
 * 我的账号页面
 */
class AMyAccount : ABase() {
    override fun layoutId(): Int = R.layout.activity_amy_account

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.white).init()
    }

    override fun initData() {
        rl_title.setOnClickListener {
            finish()
        }
        rl_change_username.setOnClickListener {
            startActivity(Intent(this,AChangeUserName::class.java))
        }
        rl_head_img.setOnClickListener {
            checkRxPermission()
        }
        rl_receipt_address.setOnClickListener {
            startActivity(Intent(this,AMyReceiptAddress::class.java))
        }

    }

    override fun start() {
    }

    @SuppressLint("CheckResult")
    private fun checkRxPermission() {
        val rxPermissions = RxPermissions(this) // where this is an Activity instance

        rxPermissions
                .requestEach(
                        Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe { permission ->

                    if (permission.granted) {
                        // 已授予权限
                        startActivityForResult(Intent(this, ACutImg::class.java).putExtra(Constants.IS_NORMAL_PIC,true), Constants.RESULT_CODE_GET_PIC)
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
    //返回图片修改图片
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (Constants.RESULT_CODE_GET_PIC === resultCode && data != null) {

            val picName = data.getStringExtra("picName")
            val file = File(externalCacheDir, picName)
            ShowImage.showCircle(this, file.absolutePath, iv_head)
        }

    }


}
