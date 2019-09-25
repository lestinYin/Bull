package com.future.taurus.ui.home.provider

import android.support.v4.content.ContextCompat
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.future.taurus.R
import com.future.taurus.ui.home.adapter.HomeAdapter
import com.jude.rollviewpager.RollPagerView
import com.jude.rollviewpager.hintview.ColorPointHintView
import com.lestin.yin.adapter.BannerAdapter

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
class StoreListLunboProvider : BaseItemProvider<MultiItemEntity, BaseViewHolder>() {

    val list = mutableListOf("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566999272467&di=5a34c50eef55f0df14303006e078cb1b&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fln%2Fpics%2Fhv1%2F197%2F114%2F1750%2F113823017.jpg",
    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566999272624&di=1deed7ed084e1720678ca2882ff75aba&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201411%2F26%2F20141126160858_dWwxt.jpeg",
    "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=364055337,609376702&fm=27&gp=0.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1566989229&di=d7cc35d81cf849351643ebd1a0a239d8&src=http://img02.tooopen.com/images/20151128/tooopen_sy_149759881365.jpg")

    override fun layout(): Int = R.layout.item_roll_view

    override fun viewType(): Int = HomeAdapter.TYPE_TEXT

    override fun convert(helper: BaseViewHolder?, data: MultiItemEntity?, position: Int) {
        val rollpager : RollPagerView = helper!!.getView(R.id.rollpager)

        val mBannerAdapter = BannerAdapter(rollpager, mContext, list)
        rollpager.setAdapter(mBannerAdapter)
        rollpager.setPlayDelay(3000)
        rollpager.setAnimationDurtion(500)
        rollpager.setHintPadding(5, 0, 15, 28)
        rollpager.setHintView(ColorPointHintView(mContext, ContextCompat.getColor(mContext, com.jinniu.delivery.R.color.white),
                ContextCompat.getColor(mContext, com.jinniu.delivery.R.color.transparent_five)))



    }

    override fun onClick(helper: BaseViewHolder?, data: MultiItemEntity?, position: Int) {
        super.onClick(helper, data, position)
//
    }
}