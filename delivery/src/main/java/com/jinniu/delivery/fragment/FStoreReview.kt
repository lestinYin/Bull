package com.jinniu.delivery.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jinniu.delivery.R
import com.lestin.yin.base.FBase
import com.lestin.yin.entity.IHomeType
import com.lestin.yin.entity.Line
import com.lestin.yin.utils.image.ShowImage
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import kotlinx.android.synthetic.main.fragment_store_review.*
import java.util.ArrayList

/**
 *
 * @ProjectName:    Taurus
 * @Package:        com.jinniu.delivery.fragment
 * @ClassName:      FStoreReview
 * @Description:     商店主页Review
 * @Author:         lestin.yin
 * @CreateDate:     2019-08-21 17:50
 * @Version:        1.0
 */
class FStoreReview : FBase(){
    override fun layoutId(): Int = R.layout.fragment_store_review

    override fun initData() {
        var list: MutableList<IHomeType> = ArrayList()
        list.add(Line("eeee"))
        list.add(Line("eeee"))

        var homeAdapter = RaidersAdapter(list)
        rv_raiders.adapter = homeAdapter
        rv_raiders.isFocusable = false

    }

    override fun initView() {
    }

    override fun start() {
    }


    companion object {
        private const val PARAM_STATUS = "status"

        fun newInstance(status: String): FStoreReview {
            val fragment = FStoreReview()
            val args = Bundle()
            args.putString(PARAM_STATUS, status)
            fragment.arguments = args
            return fragment
        }
    }
}

class RaidersAdapter(data: List<IHomeType>) : BaseQuickAdapter<IHomeType, BaseViewHolder>(data) {
    init {
        mLayoutResId = R.layout.item_home_raiders
    }

    override fun convert(helper: BaseViewHolder, item: IHomeType) {
//        item.apply { helper.setText(R.id.vTitle, title) }
        val head4 = helper.getView<ImageView>(R.id.iv_raiders_head)
        ShowImage.showCircle(mContext,"https://upload.jianshu.io/users/upload_avatars/3333716/14924725-5e8f-4e3c-b77e-20a80f9b2ee3.png",head4)

        val rvPic = helper.getView<RecyclerView>(R.id.rv_home_raiders_img)
        rvPic.layoutManager = GridLayoutManager(mContext, 3,
                LinearLayoutManager.VERTICAL, false)

        val reviewPics = arrayOf("https://upload.jianshu.io/users/upload_avatars/3333716/14924725-5e8f-4e3c-b77e-20a80f9b2ee3.png","https://upload.jianshu.io/users/upload_avatars/3333716/14924725-5e8f-4e3c-b77e-20a80f9b2ee3.png","https://upload.jianshu.io/users/upload_avatars/3333716/14924725-5e8f-4e3c-b77e-20a80f9b2ee3.png","https://upload.jianshu.io/users/upload_avatars/3333716/14924725-5e8f-4e3c-b77e-20a80f9b2ee3.png")
        var adapter = object : CommonAdapter<String>(mContext, R.layout.item_home_raiders_image, reviewPics.toMutableList()) {
            override fun convert(holder: ViewHolder?, t: String?, position: Int) {
                val view = holder!!.getView<ImageView>(R.id.iv_raiders_image)

                ShowImage.showRoundCorners(mContext,reviewPics[position],view,10)


            }
        }
        rvPic.adapter = adapter

    }
}