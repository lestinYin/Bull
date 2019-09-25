package com.future.taurus.ui.home.provider

import android.content.Intent
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.future.taurus.MyApplication
import com.future.taurus.R
import com.future.taurus.ui.home.activity.AStoreDetail
import com.future.taurus.ui.home.adapter.HomeAdapter
import com.lestin.yin.entity.IHomeType
import com.lestin.yin.entity.Store
import com.lestin.yin.utils.ViewUtil

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
class StoreListContentProvider : BaseItemProvider<MultiItemEntity, BaseViewHolder>() {
    val categoryList = arrayOf("饺子混沌 包子粥", "快餐便当", "汉堡薯条", "意面披萨", "包子粥店")

    override fun layout(): Int = R.layout.item_new_storelist_content

    override fun viewType(): Int = HomeAdapter.TYPE_IMG

    override fun convert(helper: BaseViewHolder?, data: MultiItemEntity?, position: Int) {
        var item_content = helper!!.getView<LinearLayout>(R.id.ll_horizental_pic)

        item_content.removeAllViews()
//        val layoutParam = LinearLayout.LayoutParams(ViewUtil.dp2px(92), ViewUtil.dp2px(92))
//        layoutParam.gravity = Gravity.CENTER
//        layoutParam.leftMargin = ViewUtil.dp2px(8)
//        layoutParam.leftMargin = 8
        //为布局中textview设置好相关属性
        for (i in categoryList) {
            var view = LayoutInflater.from(mContext).inflate(R.layout.item_storelist_horizental_item,null)
            item_content.addView(view)
            item_content.invalidate()
            view.setOnClickListener {
                mContext.startActivity(Intent(mContext,AStoreDetail::class.java).putExtra("id",""))

            }

        }

    }

    override fun onClick(helper: BaseViewHolder?, data: MultiItemEntity?, position: Int) {
        super.onClick(helper, data, position)

//
    }
}