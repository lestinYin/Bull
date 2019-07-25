package com.future.taurus.net.model;

import com.future.taurus.entity.ECode
import com.future.taurus.net.RetrofitManager
import com.hazz.kotlinmvp.rx.scheduler.SchedulerUtils

import io.reactivex.Observable
import retrofit2.http.Query


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


}