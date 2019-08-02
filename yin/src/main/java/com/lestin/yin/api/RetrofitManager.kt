package com.future.taurus.api

import com.lestin.yin.Constants
import com.lestin.yin.MyApplication
import com.lestin.yin.api.ApiAuthnterceptor
import com.lestin.yin.utils.jsonUtils.SPManager
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import com.lestin.yin.utils.NetworkUtil
import com.lestin.yin.utils.Preference as Preference1
import com.lestin.yin.api.utils.SslUtils
import java.security.KeyStore
import java.util.*
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager


/**
 * Created by xuhao on 2017/11/16.
 *
 */

object RetrofitManager{

    private var client: OkHttpClient? = null
    private var retrofit: Retrofit? = null





    val service: ApiService by lazy { getRetrofit()!!.create(ApiService::class.java)}

    private var token:String by Preference1("token","")

    /**
     * 设置公共参数
     */
    private fun addQueryParameterInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val request: Request
            val modifiedUrl = originalRequest.url().newBuilder()
                    // Provide your custom parameter here
                    .addQueryParameter("phoneSystem", "")
                    .addQueryParameter("phoneModel", "")
                    .build()
            request = originalRequest.newBuilder().url(modifiedUrl).build()
            chain.proceed(request)
        }
    }

    /**
     * 设置头
     */
    private fun addHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            var spManager = SPManager(MyApplication.context)
            var tokens = if(spManager.get(Constants.TOKEN)==null){""}else{spManager.get(Constants.TOKEN).toString()}
            val requestBuilder = originalRequest.newBuilder()
                    // Provide your custom header here
                    .header("token",tokens.replace("\"","") )
//                    .header("token", "bdvmk5ksoq3bqere5qlg")
                    .header("Origin", "test")
                    .method(originalRequest.method(), originalRequest.body())
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    /**
     * 设置缓存
     */
    private fun addCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            if (!NetworkUtil.isNetworkAvailable(MyApplication.context)) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build()
            }
            val response = chain.proceed(request)
            if (NetworkUtil.isNetworkAvailable(MyApplication.context)) {
                val maxAge = 0
                // 有网络时 设置缓存超时时间0个小时 ,意思就是不读取缓存数据,只对get有用,post没有缓冲
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Retrofit")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build()
            } else {
                // 无网络时，设置超时为4周  只对get有用,post没有缓冲
                val maxStale = 60 * 60 * 24 * 28
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("nyn")
                        .build()
            }
            response
        }
    }

    private fun getRetrofit(): Retrofit? {
        if (retrofit == null) {
            synchronized(RetrofitManager::class.java) {
                if (retrofit == null) {
                    //添加一个log拦截器,打印所有的log
                    val httpLoggingInterceptor = HttpLoggingInterceptor()
                    val apiAuthnterceptor = ApiAuthnterceptor()
                    //可以设置请求过滤的水平,body,basic,headers
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

                    //设置 请求的缓存的大小跟位置
                    val cacheFile = File(MyApplication.context.cacheDir, "cache")
                    val cache = Cache(cacheFile, 1024 * 1024 * 50) //50Mb 缓存的大小

                    //证书相关
                    val sslContext = SslUtils.getSslContextForCertificateFile("api_ssl_debug.cer")

                    val trustManagerFactory = TrustManagerFactory.getInstance(
                            TrustManagerFactory.getDefaultAlgorithm())
                    trustManagerFactory.init(null as KeyStore?)
                    val trustManagers = trustManagerFactory.getTrustManagers()
                    if (trustManagers.size != 1 || trustManagers[0] !is X509TrustManager) {
                        throw IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers))
                    }
                    val trustManager = trustManagers[0] as X509TrustManager




                    client = OkHttpClient.Builder()
                            .addInterceptor(addQueryParameterInterceptor())  //参数添加
                            .addInterceptor(addHeaderInterceptor()) // token过滤
//                            .addInterceptor(addCacheInterceptor())
                            .addInterceptor(httpLoggingInterceptor) //日志,所有的请求响应度看到
//                            .addInterceptor(apiAuthnterceptor)  //添加数据解密拦截
                            .cache(cache)  //添加缓存
                            .connectTimeout(60L, TimeUnit.SECONDS)
                            .readTimeout(60L, TimeUnit.SECONDS)
                            .writeTimeout(60L, TimeUnit.SECONDS)
                            .sslSocketFactory(sslContext.socketFactory, trustManager) //设置证书
                            .build()
                            //.hostnameVerifier((hostname, session) -> true)

                    // 获取retrofit的实例
                    retrofit = Retrofit.Builder()
                            .baseUrl(UriConstant.BASE_URL)  //自己配置
                            .client(client!!)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                }
            }
        }
        return retrofit
    }


}
