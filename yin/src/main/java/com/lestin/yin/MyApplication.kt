package com.lestin.yin

import android.app.Application
import android.content.Context
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
            set


    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext


    }
}