package com.jinniu.delivery.entity

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.jinniu.delivery.activity.ShowAddAdapter

/**
 *
 * @ProjectName:    Taurus
 * @Package:        com.jinniu.delivery.entity
 * @ClassName:      ETestAdd
 * @Description:     选择地址实体
 * @Author:         lestin.yin
 * @CreateDate:     2019-08-27 18:56
 * @Version:        1.0
 */
data class ETestAddTitle(val title: String, val pic: Int) : MultiItemEntity {
    override fun getItemType(): Int = ShowAddAdapter.TYPE_HEAD
}

data class ETestAddMy(val title: String) : MultiItemEntity {
    override fun getItemType(): Int = ShowAddAdapter.TYPE_MY_ADD
}

data class ETestAddClose(val title: String) : MultiItemEntity {
    override fun getItemType(): Int = ShowAddAdapter.TYPE_CLOSE_ADD
}

data class ETestSearchAddressResult(val title: String) : MultiItemEntity {
    override fun getItemType(): Int = ShowAddAdapter.TYPE_RESEARCH_RESULT
}