package com.future.taurus

import android.app.Application
import android.content.Context
import android.content.Intent
import com.future.taurus.BuildConfig
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

        @JvmStatic var context: Context by Delegates.notNull()
            private set


    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        com.lestin.yin.MyApplication.context = context



        // chrome调试：Stetho初始化
//        if (Constants.DEBUG) {
//            Stetho.initializeWithDefaults(this)
//        }
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