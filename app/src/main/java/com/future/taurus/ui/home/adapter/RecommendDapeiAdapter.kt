package com.future.taurus.ui.home.adapter

import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.future.taurus.R
import com.future.taurus.ui.home.entity.EHome
import com.lestin.yin.base.AdapterInterface

/**
 *
 * @ProjectName:    Taurus
 * @Package:        com.jinniu.my.adapter
 * @ClassName:      MyAddressAdapter
 * @Description:    我的地址构造器
 * @Author:         lestin.yin
 * @CreateDate:     2019-09-03 14:15
 * @Version:        1.0
 */
class RecommendDapeiAdapter : BaseQuickAdapter<EHome, BaseViewHolder>,AdapterInterface<EHome>{

    constructor(): super(R.layout.item_recommend_dapei)

    override fun setData(data: MutableList<EHome>?) {
        super.setNewData(data)
    }

    override fun addData(data: MutableList<EHome>?) {
        super.addData(data!!)
    }

    override fun convert(helper: BaseViewHolder?, item: EHome?) {
    }

    override fun noData() {

    }
}