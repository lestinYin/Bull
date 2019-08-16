package com.jinniu.delivery.provider

import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.jinniu.delivery.R
import com.jinniu.delivery.adapter.DeliveryHomeAdapter
import com.lestin.yin.entity.IHomeType

/**
 *
 * @ProjectName:    Taurus
 * @Package:        com.future.taurus.ui.home
 * @ClassName:      StoreProvider
 * @Description:     java类作用描述
 * @Author:         lestin.yin
 * @CreateDate:     2019-07-30 15:31
 * @Version:        1.0
 */
class LineProvider : BaseItemProvider<IHomeType, BaseViewHolder>() {
    override fun layout(): Int = R.layout.item_delivery_home

    override fun viewType(): Int = DeliveryHomeAdapter.TYPE_IMG

    override fun convert(helper: BaseViewHolder?, data: IHomeType?, position: Int) {
//        helper!!.setText(R.id.tv_home_store_title,"fdksalfdklsjakfldjsk")
    }
}