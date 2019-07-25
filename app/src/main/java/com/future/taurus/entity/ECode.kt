package com.future.taurus.entity

import java.io.Serializable


/**
 * @name Pinche
 * @class name：com.xiaozhu.pinche.ui.login.entity
 * @class describe
 * @author lestin.yin yinmaolin8@gmail.com
 * @time 2018/7/23 下午5:56
 * @change
 * @chang time
 * @class describe
 */
data class ECode(val code: Int, val message : String, val data: Data) : Serializable
    data class Data(val status: Boolean,val exit: Boolean,val guid: String,val phone: String): Serializable