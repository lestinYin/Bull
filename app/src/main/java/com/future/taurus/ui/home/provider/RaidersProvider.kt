package com.future.taurus.ui.home.provider

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.future.taurus.MyApplication
import com.future.taurus.R
import com.future.taurus.ui.home.adapter.HomeAdapter
import com.lestin.yin.entity.IHomeType
import com.lestin.yin.entity.ReviewInfo
import com.lestin.yin.utils.image.ShowImage
import com.lestin.yin.widget.photoview.ImageDetailsActivity
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import java.util.ArrayList

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
//        helper!!.setText(R.id.tv_raiders_name,"Sandy小花猫咪")
//        helper!!.setText(R.id.tv_raiders_detail,reviewInfo.reviewContent)
//        helper!!.setText(R.id.tv_home_raiders_like_counts,reviewInfo.likeCount.toString())
//        helper!!.setText(R.id.tv_home_raiders_location,reviewInfo.storeName)
//
//        val head4 = helper.getView<ImageView>(R.id.iv_raiders_head)
//        ShowImage.showCircle(MyApplication.context,"https://upload.jianshu.io/users/upload_avatars/3333716/14924725-5e8f-4e3c-b77e-20a80f9b2ee3.png",head4)
//
        val rvPic = helper!!.getView<RecyclerView>(R.id.rv_home_raiders_img)
//
        rvPic.layoutManager = GridLayoutManager(MyApplication.context, 3,
                LinearLayoutManager.VERTICAL, false)
//
//        val reviewPics = reviewInfo.reviewPics
        val reviewPics = arrayOf("http://b.hiphotos.baidu.com/image/h%3D300/sign=ad628627aacc7cd9e52d32d909032104/32fa828ba61ea8d3fcd2e9ce9e0a304e241f5803.jpg", "http://f.hiphotos.baidu.com/image/h%3D300/sign=d985fb87d81b0ef473e89e5eedc551a1/b151f8198618367aa7f3cc7424738bd4b31ce525.jpg",
                "http://b.hiphotos.baidu.com/image/h%3D300/sign=92afee66fd36afc3110c39658318eb85/908fa0ec08fa513db777cf78376d55fbb3fbd9b3.jpg")
        var adapter = object : CommonAdapter<String>(MyApplication.context, R.layout.item_home_raiders_image, reviewPics.toMutableList()) {
            override fun convert(holder: ViewHolder?, t: String?, position: Int) {
                val view = holder!!.getView<ImageView>(R.id.iv_raiders_image)

                ShowImage.showRoundCorners(MyApplication.context,reviewPics[position],view,10)
            }
        }
        rvPic.adapter = adapter
//        adapter.setOnItemClickListener(object : MultiItemTypeAdapter.OnItemClickListener{
//
//            override fun onItemLongClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int): Boolean = false
//
//
//            override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
//                //查看图片详情
//                ImageDetailsActivity.start(1,mContext,reviewPics.toMutableList() as ArrayList<String>,false,position,"")
//            }
//
//        })
    }
}