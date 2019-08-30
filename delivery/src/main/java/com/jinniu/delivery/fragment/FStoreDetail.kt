package com.jinniu.delivery.fragment

import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import com.jinniu.delivery.R
import com.lestin.yin.base.FBase
import com.lestin.yin.utils.ViewUtil
import com.lestin.yin.utils.image.ShowImage
import kotlinx.android.synthetic.main.fragment_delivery_store_detail.*

/**
 *
 * @ProjectName:    Taurus
 * @Package:        com.jinniu.delivery.fragment
 * @ClassName:      FStoreDetail
 * @Description:     点菜页中商店详情
 * @Author:         lestin.yin
 * @CreateDate:     2019-08-22 17:15
 * @Version:        1.0
 */
class FStoreDetail : FBase() {

    companion object {
        private const val PARAM_STATUS = "status"

        fun newInstance(status: String): FStoreDetail {
            val fragment = FStoreDetail()
            val args = Bundle()
            args.putString(PARAM_STATUS, status)
            fragment.arguments = args
            return fragment
        }
    }

    override fun layoutId(): Int = R.layout.fragment_delivery_store_detail

    override fun initData() {
        fillHorizentalPic()
    }

    override fun initView() {
    }

    override fun start() {
    }

    /**
     *填充水平图片
     */
    private fun fillHorizentalPic() {
        val categoryList = arrayOf("饺子混沌 包子粥", "快餐便当", "汉堡薯条", "意面披萨", "包子粥店")
        ll_horizental_pic.removeAllViews()
        val layoutParam = LinearLayout.LayoutParams(ViewUtil.dp2px(92), ViewUtil.dp2px(92))
        layoutParam.gravity = Gravity.CENTER
        layoutParam.leftMargin = ViewUtil.dp2px(8)
//        layoutParam.leftMargin = 8
        //为布局中textview设置好相关属性
        for (i in categoryList) {
            var image = ImageView(context)
            ShowImage.show(context,"https://f11.baidu.com/it/u=778664578,3658473305&fm=173&app=49&f=JPEG?w=640&h=427&s=79986F9350344F9ADAACB6F503004025&access=215967316",image)
            image.layoutParams = layoutParam
            ll_horizental_pic.addView(image)
            ll_horizental_pic.invalidate()
        }
    }
}