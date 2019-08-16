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
import com.lestin.yin.entity.IHomeType

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
class HomeAdapter : MultipleItemRvAdapter<IHomeType, BaseViewHolder> {
    companion object {
        val TYPE_TEXT = 100
        val TYPE_IMG = 200
        val TYPE_TEXT_IMG = 300
    }

    constructor(data: MutableList<IHomeType>?) : super(data) {
        finishInitialize()
    }

    override fun registerItemProvider() {
        this.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        mProviderDelegate.registerProvider(StoreProvider())
        mProviderDelegate.registerProvider(LineProvider())
        mProviderDelegate.registerProvider(RaidersProvider())

    }


    override fun getViewType(t: IHomeType?): Int {
        if (t!!.getType() === 100) {
            return TYPE_TEXT
        } else if (t!!.getType() === 200) {
            return TYPE_IMG
        } else if (t!!.getType() === 300) {
            return TYPE_TEXT_IMG
        }
        return 0
    }




}