package com.jinniu.delivery.provider

import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
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
class StoreListProvider : BaseItemProvider<IHomeType, BaseViewHolder>() {
    override fun layout(): Int = R.layout.item_delivery_home

    override fun viewType(): Int = DeliveryHomeAdapter.TYPE_IMG

    override fun convert(helper: BaseViewHolder?, data: IHomeType?, position: Int) {
        var tag = helper!!.getView<RelativeLayout>(R.id.rl_delivery_show_tag)
        var detail = helper!!.getView<RelativeLayout>(R.id.rl_delivery_show_tag_detail)
        helper.getView<ImageView>(R.id.iv_store_list_expand_false).setOnClickListener {
                tag.visibility = View.GONE
                detail.visibility = View.VISIBLE

        }
        helper.getView<ImageView>(R.id.iv_store_list_expand_true).setOnClickListener {
                tag.visibility = View.VISIBLE
                detail.visibility = View.GONE
        }
    }

    override fun onClick(helper: BaseViewHolder?, data: IHomeType?, position: Int) {
        super.onClick(helper, data, position)

    }
}