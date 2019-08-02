package com.future.taurus.ui.home.entity

import com.lestin.yin.base.BaseEntity
import com.lestin.yin.base.DataMode
import com.lestin.yin.base.EBaseData

/**
 *
 * @ProjectName:    Taurus
 * @Package:        com.future.taurus.ui.home.entity
 * @ClassName:      LHome
 * @Description:     java类作用描述
 * @Author:         lestin.yin
 * @CreateDate:     2019-07-30 14:00
 * @Version:        1.0
 */
class LHome : BaseEntity<LHome.Home>(), DataMode<EHome>{
    override fun isPageLast(): Boolean  = false

    override fun getList(): List<EHome>  = base.getList()!!

    override fun getResultMessage(): String = reason


    class Home : EBaseData() {
        var caseDtoList: List<EHome>? = null

        fun getList(): List<EHome>? {

            return caseDtoList

        }
        fun setList(list : List<EHome> ) {

            caseDtoList = list

        }
    }
}