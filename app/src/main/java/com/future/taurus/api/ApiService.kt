package com.hazz.kotlinmvp.api



import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap


/**
 * @Description: Api服务类
 * @Author: Lestin.Yin
 * @CreateDate: 2019/7/19 17:58
 * @Version: 1.0
 */

interface ApiService {

    /**
     */
    @POST("")
    fun isRegistPhone(@Query("") phone: String): Observable<Object>



}