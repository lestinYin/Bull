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
import com.future.taurus.ui.home.entity.EHome
import com.lestin.yin.utils.ToastUtils
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
class StoreProvider : BaseItemProvider<EHome, BaseViewHolder>() {
    override fun layout(): Int = R.layout.item_home_store

    override fun viewType(): Int = HomeAdapter.TYPE_TEXT

    override fun convert(helper: BaseViewHolder?, data: EHome?, position: Int) {

        helper!!.setText(R.id.tv_home_store_title,"“温莎是市中心最好的KTV，我们都来这里唱歌喝酒”")
        val view = helper.getView<ImageView>(R.id.iv_store_img)
        ShowImage.showRoundCorners(MyApplication.context,"https://tse1-mm.cn.bing.net/th?id=OET.1ec0d4ee50f84be8a26861b31fc065e6&w=272&h=135&c=7&rs=1&o=5&pid=1.9",view,50)

        val head1headImage = helper.getView<ImageView>(R.id.iv_head_img)
        ShowImage.showCircle(MyApplication.context,"https://upload.jianshu.io/users/upload_avatars/3333716/14924725-5e8f-4e3c-b77e-20a80f9b2ee3.png",head1headImage)

        val rootView = helper.getView<RelativeLayout>(R.id.rl_root)
        rootView.translationZ = 20F

        val head1 = helper.getView<ImageView>(R.id.iv_head1)
        val head2 = helper.getView<ImageView>(R.id.iv_head2)
        val head3 = helper.getView<ImageView>(R.id.iv_head3)
        val head4 = helper.getView<ImageView>(R.id.iv_head4)
        ShowImage.showCircle(MyApplication.context,"https://upload.jianshu.io/users/upload_avatars/3333716/14924725-5e8f-4e3c-b77e-20a80f9b2ee3.png",head1)
        ShowImage.showCircle(MyApplication.context,"https://upload.jianshu.io/users/upload_avatars/3333716/14924725-5e8f-4e3c-b77e-20a80f9b2ee3.png",head2)
        ShowImage.showCircle(MyApplication.context,"https://upload.jianshu.io/users/upload_avatars/3333716/14924725-5e8f-4e3c-b77e-20a80f9b2ee3.png",head3)
        ShowImage.showCircle(MyApplication.context,"https://upload.jianshu.io/users/upload_avatars/3333716/14924725-5e8f-4e3c-b77e-20a80f9b2ee3.png",head4)

    }

    override fun onClick(helper: BaseViewHolder?, data: EHome?, position: Int) {
        super.onClick(helper, data, position)
        //跳转到详情页
        var  intent = Intent(MyApplication.context, AStoreDetail::class.java)
        mContext.startActivity(intent)
    }
}