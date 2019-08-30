package com.jinniu.delivery.adapter

import android.graphics.Paint
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jinniu.delivery.R
import com.lestin.yin.entity.IHomeType



/**
 *
 * @ProjectName:    Taurus
 * @Package:        com.jinniu.delivery.adapter
 * @ClassName:      StoreListAdapter
 * @Description:     店铺列表Adapter
 * @Author:         lestin.yin
 * @CreateDate:     2019-08-19 14:05
 * @Version:        1.0
 */
class StoreListAdapter : BaseQuickAdapter<IHomeType, BaseViewHolder>{
    override fun convert(helper: BaseViewHolder?, item: IHomeType?) {
        val tvFormerPrice = helper!!.getView<TextView>(R.id.tv_tv_store_list_dish_price_former)
        tvFormerPrice.getPaint().flags = Paint.STRIKE_THRU_TEXT_FLAG or Paint.ANTI_ALIAS_FLAG // 设置中划线并加清晰


    }

    constructor(deliveryList: MutableList<IHomeType>) : super(R.layout.item_store_list,deliveryList)
}