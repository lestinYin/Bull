package com.future.taurus.ui.home.activity

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.future.taurus.R
import com.lestin.yin.base.ABase
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import kotlinx.android.synthetic.main.activity_ahome.*

/**
 * 主页
 */
@Route(path = "/app/home")
class AHome : ABase() {
    val info = arrayOf("汇兑信息", "安全电话", "应急卡", "小费助手", "电话充值", "帮助中心")
    val iconImg = arrayOf(R.mipmap.home_page_three_icon_1, R.mipmap.home_page_three_icon_2, R.mipmap.home_page_three_icon_3, R.mipmap.home_page_three_icon_4, R.mipmap.home_page_three_icon_5, R.mipmap.home_page_three_icon_6)


    override fun layoutId(): Int = R.layout.activity_ahome

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.transparent).init()
        rv_home_page_three_icon.layoutManager = GridLayoutManager(this, 3,
                LinearLayoutManager.VERTICAL, false)
    }

    override fun initData() {
        setTools()

        ll_home_page_two.setOnClickListener {
            ARouter.getInstance().build("/app/top").navigation()
        }
        ll_home_page_one.setOnClickListener {
            ARouter.getInstance().build("/delivery/hometest").navigation()
        }
    }

    override fun start() {
    }

    /**
     * 设置工具集
     */
    private fun setTools() {
        var adapter = object : CommonAdapter<String>(this, R.layout.item_home_page_three_icon, info.toMutableList()) {
            override fun convert(holder: ViewHolder?, t: String?, position: Int) {
                holder!!.getView<ImageView>(R.id.iv_icon_bg).background = getDrawable(iconImg[position])


            }
        }
        rv_home_page_three_icon.adapter = adapter
    }

}
