package com.future.taurus.ui.home.activity

import android.content.Intent
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.future.taurus.R
import com.lestin.yin.base.ABase
import com.lestin.yin.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_achange_theme.*

/**
 * 榜单中切换主题页面
 */
class AChangeTheme : ABase() {
    val cityList = arrayOf("城市夜生活", "排队美食榜", "什么值得玩")

    override fun layoutId(): Int = R.layout.activity_achange_theme

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.white).init()
    }

    override fun initData() {

        var adapter = ThemeAdapter(cityList.toMutableList())
        recycler_theme_list.adapter = adapter

        adapter.setOnItemClickListener { adapter, view, position ->
            setResult(2, Intent().putExtra("theme",cityList[position]))
            finish()
        }


    }

    override fun start() {
        iv_change_theme_back.setOnClickListener {
            finish()
        }
    }

}

class ThemeAdapter(data: List<String>) : BaseQuickAdapter<String, BaseViewHolder>(data) {
    init {
        mLayoutResId = R.layout.item_theme_adapter
    }

    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper?.setText(R.id.tv_theme,item)
    }

}
