package com.future.taurus.ui.home.provider

import android.content.Intent
import android.widget.ImageView
import android.widget.RelativeLayout
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.future.taurus.MyApplication
import com.future.taurus.R
import com.future.taurus.ui.home.activity.AStoreDetail
import com.future.taurus.ui.home.adapter.HomeAdapter
import com.lestin.yin.entity.IHomeType
import com.lestin.yin.entity.Store
import com.lestin.yin.utils.image.ShowImage

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
class StoreProvider : BaseItemProvider<IHomeType, BaseViewHolder>() {
    override fun layout(): Int = R.layout.item_home_store

    override fun viewType(): Int = HomeAdapter.TYPE_TEXT

    override fun convert(helper: BaseViewHolder?, data: IHomeType?, position: Int) {
        var stores = data as Store

        helper!!.setText(R.id.tv_home_store_title,"“"+stores.recommendReason+"”")
        helper.setText(R.id.tv_home_store_tag,stores.storeTag)
        helper.setText(R.id.tv_home_store_name,stores.recommenderName)
        helper.setText(R.id.tv_home_store_location,stores.storeName)
        helper.setText(R.id.tv_home_store_review_count,stores.reviewCount.toString()+"条")
        helper.setText(R.id.tv_home_store_distance,stores.toStoreDistance.toString() +"米")

        val view = helper.getView<ImageView>(R.id.iv_store_img)
        ShowImage.showRoundCorners(MyApplication.context,stores.storeCover,view,14)
//        ShowImage.showRoundCorners(MyApplication.context,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566480822513&di=ae919cfb38d852d4226da392f1788da3&imgtype=0&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F0eb30f2442a7d9337119f7dba74bd11372f001e0.jpg",view,14)

        val head1headImage = helper.getView<ImageView>(R.id.iv_head_img)
        ShowImage.showCircle(MyApplication.context,stores.recommenderPhoto,head1headImage)

        val rootView = helper.getView<RelativeLayout>(R.id.rl_root)
        rootView.translationZ = 20F

        val head1 = helper.getView<ImageView>(R.id.iv_head1)
        val head2 = helper.getView<ImageView>(R.id.iv_head2)
        val head3 = helper.getView<ImageView>(R.id.iv_head3)
        val head4 = helper.getView<ImageView>(R.id.iv_head4)
        val reviewersPhotos = stores.reviewersPhotos
        ShowImage.showCircle(MyApplication.context, reviewersPhotos[0],head1)
        ShowImage.showCircle(MyApplication.context,reviewersPhotos[1],head2)
        ShowImage.showCircle(MyApplication.context,reviewersPhotos[2],head3)
        ShowImage.showCircle(MyApplication.context,reviewersPhotos[3],head4)

    }

    override fun onClick(helper: BaseViewHolder?, data: IHomeType?, position: Int) {
        super.onClick(helper, data, position)
        //跳转到详情页
        var  intent = Intent(MyApplication.context, AStoreDetail::class.java)
        var stores = data as Store
        intent.putExtra("id",stores.storeId)
        mContext.startActivity(intent)
    }
}