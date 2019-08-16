package com.future.taurus.ui.home.provider

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.future.taurus.MyApplication
import com.future.taurus.R
import com.future.taurus.ui.home.adapter.HomeAdapter
import com.future.taurus.ui.home.entity.EHome
import com.lestin.yin.entity.IHomeType
import com.lestin.yin.entity.ReviewInfo
import com.lestin.yin.utils.image.ShowImage
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder

/**
 *
 * @ProjectName:    Taurus
 * @Package:        com.future.taurus.ui.home
 * @ClassName:      StoreProvider
 * @Description:     攻略布局
 * @Author:         lestin.yin
 * @CreateDate:     2019-07-30 15:31
 * @Version:        1.0
 */
class RaidersProvider : BaseItemProvider<IHomeType, BaseViewHolder>() {

    override fun layout(): Int = R.layout.item_home_raiders

    override fun viewType(): Int = HomeAdapter.TYPE_TEXT_IMG

    override fun convert(helper: BaseViewHolder?, data: IHomeType?, position: Int) {
        var reviewInfo = data as ReviewInfo
        helper!!.setText(R.id.tv_raiders_name,"Sandy小花猫咪")
        helper!!.setText(R.id.tv_raiders_detail,reviewInfo.reviewContent)
        helper!!.setText(R.id.tv_home_raiders_like_counts,reviewInfo.likeCount.toString())
        helper!!.setText(R.id.tv_home_raiders_location,reviewInfo.storeName)

        val head4 = helper.getView<ImageView>(R.id.iv_raiders_head)
        ShowImage.showCircle(MyApplication.context,"https://upload.jianshu.io/users/upload_avatars/3333716/14924725-5e8f-4e3c-b77e-20a80f9b2ee3.png",head4)

        val rvPic = helper.getView<RecyclerView>(R.id.rv_home_raiders_img)

        rvPic.layoutManager = GridLayoutManager(MyApplication.context, 3,
                LinearLayoutManager.VERTICAL, false)

        val reviewPics = reviewInfo.reviewPics
        var adapter = object : CommonAdapter<String>(MyApplication.context, R.layout.item_home_raiders_image, reviewPics.toMutableList()) {
            override fun convert(holder: ViewHolder?, t: String?, position: Int) {
                val view = holder!!.getView<ImageView>(R.id.iv_raiders_image)

                ShowImage.showRoundCorners(MyApplication.context,reviewPics[position],view,10)


            }
        }
        rvPic.adapter = adapter
    }
}