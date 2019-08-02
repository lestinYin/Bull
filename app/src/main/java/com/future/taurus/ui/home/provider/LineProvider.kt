package com.future.taurus.ui.home.provider

import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.future.taurus.R
import com.future.taurus.ui.home.adapter.HomeAdapter
import com.future.taurus.ui.home.entity.EHome

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
class LineProvider : BaseItemProvider<EHome, BaseViewHolder>() {
    override fun layout(): Int = R.layout.item_home_line

    override fun viewType(): Int = HomeAdapter.TYPE_IMG

    override fun convert(helper: BaseViewHolder?, data: EHome?, position: Int) {
//        helper!!.setText(R.id.tv_home_store_title,"fdksalfdklsjakfldjsk")
    }
}