package com.future.taurus.ui.home.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.MultipleItemRvAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.future.taurus.R
import com.future.taurus.ui.home.provider.*
import com.lestin.yin.base.AdapterInterface

/**
 *
 * @ProjectName:    Taurus
 * @Package:        com.jinniu.my.adapter
 * @ClassName:      QuestionAdapter
 * @Description:     问题adapter
 * @Author:         lestin.yin
 * @CreateDate:     2019-09-12 16:26
 * @Version:        1.0
 */
class NewStoreListAdapter : MultipleItemRvAdapter<MultiItemEntity, BaseViewHolder>, AdapterInterface<MultiItemEntity> {

    constructor(data: MutableList<MultiItemEntity>?) : super(data) {
        finishInitialize()
    }

    override fun registerItemProvider() {
        this.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        mProviderDelegate.registerProvider(StoreListLunboProvider())
        mProviderDelegate.registerProvider(StoreListContentProvider())

    }

    companion object {
        val TYPE_TEXT = 100
        val TYPE_IMG = 200
    }

    override fun getViewType(t: MultiItemEntity?): Int {
        if (t!!.itemType === 100) {
            return HomeAdapter.TYPE_TEXT
        } else if (t!!.itemType === 200) {
            return HomeAdapter.TYPE_IMG
        }
        return 0
    }


    override fun setData(data: MutableList<MultiItemEntity>?) {
        super.setNewData(data)

    }

    override fun addData(data: MutableList<MultiItemEntity>?) {
        super.addData(data!!)

    }

    override fun noData() {
    }



}
