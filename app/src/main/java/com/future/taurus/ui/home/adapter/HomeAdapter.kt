package com.future.taurus.ui.home.adapter

import android.os.Parcel
import android.os.Parcelable
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.MultipleItemRvAdapter
import com.future.taurus.ui.home.provider.StoreProvider
import com.future.taurus.ui.home.entity.EHome
import com.future.taurus.ui.home.provider.LineProvider
import com.future.taurus.ui.home.provider.RaidersProvider
import com.lestin.yin.base.AdapterInterface

/**
 *
 * @ProjectName:    Taurus
 * @Package:        com.future.taurus.ui.home.adapter
 * @ClassName:      HomeAdapter
 * @Description:     java类作用描述
 * @Author:         lestin.yin
 * @CreateDate:     2019-07-30 14:54
 * @Version:        1.0
 */
@Suppress("DEPRECATED_IDENTITY_EQUALS")
class HomeAdapter : MultipleItemRvAdapter<EHome, BaseViewHolder>, AdapterInterface<EHome> {
    companion object {
        val TYPE_TEXT = 1
        val TYPE_IMG = 2
        val TYPE_TEXT_IMG = 3
    }

    constructor(data: MutableList<EHome>?) : super(data) {
    }

    override fun registerItemProvider() {

        this.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        mProviderDelegate.registerProvider(StoreProvider())
        mProviderDelegate.registerProvider(LineProvider())
        mProviderDelegate.registerProvider(RaidersProvider())

    }

    override fun setData(data: MutableList<EHome>?) {

        super.setNewData(data)
        finishInitialize()
    }

    override fun addData(data: MutableList<EHome>?) {
        if (data != null) {
            super.addData(data)
        }
    }

    override fun getViewType(t: EHome?): Int {
        if (t!!.code === 1) {
            return TYPE_TEXT
        } else if (t!!.code === 2) {
            return TYPE_IMG
        } else if (t!!.code === 3) {
            return TYPE_TEXT_IMG
        }
        return 0
    }

    override fun noData() {
    }


}