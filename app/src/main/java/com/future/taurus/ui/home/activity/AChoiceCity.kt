package com.future.taurus.ui.home.activity

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.future.taurus.R
import com.lestin.yin.base.ABase
import kotlinx.android.synthetic.main.activity_achoice_city.*

class AChoiceCity : ABase() {
//    val cityList = resources.getStringArray(R.array.city_list)
//    val cityListInfo = resources.getStringArray(R.array.city_list_info)
    val cityList = arrayOf("汇兑信息", "安全电话", "应急卡", "小费助手", "电话充值", "帮助中心", "积分兑换", "推广收益")


    override fun layoutId(): Int = R.layout.activity_achoice_city

    override fun initView() {
        mImmersionBar!!.statusBarColor(R.color.colorPrimary).init()
    }

    override fun initData() {
        var cityAdapter = CityAdapter(cityList.toMutableList() )
        recycler_city_list.adapter = cityAdapter



    }

    override fun start() {

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

}

//城市列表
class CityAdapter(data: List<String>) : BaseQuickAdapter<String, BaseViewHolder>(data) {
    init {
        mLayoutResId = R.layout.item_ciyt_list
    }

    override fun convert(helper: BaseViewHolder?, item: String?) {
    }

}
