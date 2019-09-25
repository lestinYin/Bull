package com.jinniu.my.adapter

import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.future.taurus.ui.home.entity.EHome
import com.jinniu.my.R
import com.lestin.yin.base.AdapterInterface

/**
 *
 * @ProjectName:    Taurus
 * @Package:        com.jinniu.my.adapter
 * @ClassName:      MyAddressAdapter
 * @Description:    我的地址构造器
 * @Author:         lestin.yin
 * @CreateDate:     2019-09-06 14:15
 * @Version:        1.0
 */
class MyAddressAdapter : BaseQuickAdapter<EHome, BaseViewHolder>,AdapterInterface<EHome>{

    constructor(): super(R.layout.item_my_address)

    override fun setData(data: MutableList<EHome>?) {
        super.setNewData(data)
    }

    override fun addData(data: MutableList<EHome>?) {
        super.addData(data!!)
    }

    override fun convert(helper: BaseViewHolder?, item: EHome?) {
        helper!!.getView<ImageView>(R.id.iv_edit).visibility = View.VISIBLE
    }

    override fun noData() {

    }
}