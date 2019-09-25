package com.future.taurus.ui.home.adapter

import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.future.taurus.MyApplication
import com.future.taurus.R
import com.lestin.yin.entity.StoreComment
import com.lestin.yin.utils.image.ShowImage

/**
 *
 * @ProjectName:    Taurus
 * @Package:        com.future.taurus.ui.home.adapter
 * @ClassName:      StoreCommentAdapter
 * @Description:     java类作用描述
 * @Author:         lestin.yin
 * @CreateDate:     2019-08-02 15:09
 * @Version:        1.0
 */
class StoreCommentAdapter : BaseQuickAdapter<StoreComment, BaseViewHolder> {

    constructor(data: MutableList<StoreComment>?) : super(R.layout.item_comment_list,data) {
    }
    override fun convert(helper: BaseViewHolder?, storeComment: StoreComment?) {
        val view = helper!!.getView<ImageView>(R.id.iv_store_comment_list_head)
        helper.getView<TextView>(R.id.iv_store_comment_detail).text = storeComment!!.content
        helper.getView<TextView>(R.id.tv_store_comment_publish_time).text = storeComment!!.publishTime


        ShowImage.showCircle(MyApplication.context,"https://upload.jianshu.io/users/upload_avatars/3333716/14924725-5e8f-4e3c-b77e-20a80f9b2ee3.png",view)
    }
}