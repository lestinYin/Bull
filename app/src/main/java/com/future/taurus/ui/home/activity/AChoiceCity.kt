package com.future.taurus.ui.home.activity

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.future.taurus.R
import com.lestin.yin.base.ABase
import kotlinx.android.synthetic.main.activity_achoice_city.*

class AChoiceCity : ABase() {


    override fun layoutId(): Int = R.layout.activity_achoice_city

    override fun initView() {

        mImmersionBar!!.statusBarColor(R.color.colorPrimary).init()
    }

    override fun initData() {
        var cityList = resources.getStringArray(R.array.city_lists)
        var cityListInfo = resources.getStringArray(R.array.city_list_infos)

        var cityAdapter = CityAdapter(cityList.toMutableList() ,cityListInfo.toMutableList())
        recycler_city_list.adapter = cityAdapter



    }

    override fun start() {

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

}

//城市列表
class CityAdapter(data: List<String>, private val cityInfo: MutableList<String>) : BaseQuickAdapter<String, BaseViewHolder>(data) {

    init {
        mLayoutResId = R.layout.item_ciyt_list
    }
    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper!!.setText(R.id.tv_city_name,item)
        helper!!.setText(R.id.tv_city_info,cityInfo[helper.position])
    }

}
