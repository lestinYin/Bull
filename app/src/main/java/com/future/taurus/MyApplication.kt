package com.future.taurus

import android.app.Application
import android.content.Context
import android.content.Intent
import com.alibaba.android.arouter.launcher.ARouter
import com.facebook.stetho.Stetho
import com.future.taurus.BuildConfig
import com.lestin.yin.Constants
import kotlin.properties.Delegates


/**

 * @Description: java类作用描述
 * @Author: Lestin.Yin
 * @CreateDate: 2019/7/19 15:19
 * @UpdateUser: Lestin.Yin
 * @UpdateDate: 2019/7/19 15:19
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

class MyApplication : Application() {

    companion object {

        private val TAG = "MyApplication"

        @JvmStatic
        var context: Context by Delegates.notNull()
            private set


    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        com.lestin.yin.MyApplication.context = context


        // chrome调试：Stetho初始化
        if (Constants.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
        if (!com.lestin.yin.BuildConfig.isReleaseEnv) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this) // 尽可能早，推荐在Application中初始化
    }

    fun instance(): MyApplication {
        return this
    }

    fun exitApp() {
        var intent: Intent?
        intent = Intent(BuildConfig.APPLICATION_ID + ".intent.action.FINISH")
        sendBroadcast(intent)
    }



}