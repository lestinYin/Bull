package com.xiaozhu.pinche

import com.future.taurus.BuildConfig


/**
 * @Description: gongyou
 * @Author: Lestin.Yin
 * @CreateDate: 2019/7/19 16:01
 * @UpdateUser: Lestin.Yin
 * @Version: 1.0
 */

class Constants private constructor() {

    companion object {
        val DEBUG = !BuildConfig.isReleaseEnv
        val retxtpwd = "^(?=.*[0-9].*)(?=.*[A-Z].*)(?=.*[a-z].*).{6,20}$"
        //用户信息
        val USER_INFO: String = "user_info"
    }

}