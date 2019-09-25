package com.future.taurus.ui.home.entity

import com.chad.library.adapter.base.entity.MultiItemEntity
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
class LStoreList : BaseEntity<LStoreList.Home>(), DataMode<MultiItemEntity>{
    override fun isPageLast(): Boolean  = false

    override fun getList(): List<MultiItemEntity>  = base.getList()!!

    override fun getResultMessage(): String = reason


    class Home : EBaseData() {
        var caseDtoList: List<MultiItemEntity>? = null

        fun getList(): List<MultiItemEntity>? {

            return caseDtoList

        }
        fun setList(list : List<MultiItemEntity> ) {

            caseDtoList = list

        }
    }
}