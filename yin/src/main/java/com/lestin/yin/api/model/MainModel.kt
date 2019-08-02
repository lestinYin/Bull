package com.future.taurus.api.model
import com.future.taurus.api.RetrofitManager
import com.lestin.yin.entity.ECode
import com.lestin.yin.scheduler.SchedulerUtils

import io.reactivex.Observable


/**
 * @ClassName:
 * @Description: java类作用描述
 * @Author: Lestin.Yin
 * @CreateDate: 2019/7/23 11:14
 * @Version: 1.0
 */

class MainModel {
    /**
     * 请求热门关键词的数据
     */
    fun testPhoneExist(phone: String): Observable<ECode> {
        return RetrofitManager.service.isRegistPhone(phone)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 注册
     */
    fun regist(phone: String,password: String): Observable<ECode> {
        return RetrofitManager.service.regist(phone,password)
                .compose(SchedulerUtils.ioToMain())
    }


}