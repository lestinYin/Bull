package com.future.taurus.ui.home.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.future.taurus.R
import com.future.taurus.ui.home.fragment.FNewStoreList
import com.lestin.yin.base.ABase

/**
 * 新的店铺列表页
 */
class ANewStoreList : ABase() {
    override fun layoutId(): Int = R.layout.activity_common_fragment

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.colorPrimary).init()

        var fMyAddress = FNewStoreList()
        val message = Bundle()
        fMyAddress.setArguments(message)
        supportFragmentManager.beginTransaction().replace(R.id.frame, fMyAddress).commit()
    }

    override fun initData() {
        var back = findViewById<ImageView>(R.id.iv_back)
        back.setOnClickListener {
            finish()
        }
    }

    override fun start() {
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setBackgroundColor(resources.getColor(R.color.colorPrimary))
        toolbar.title = ""

        var title = findViewById<TextView>(R.id.tv_title)
        title.text = resources.getString(R.string.where_is_eat)

        var rightImage = findViewById<ImageView>(R.id.iv_rigth)
        rightImage.visibility = View.VISIBLE
        rightImage.background = resources.getDrawable(R.mipmap.black_clock)
    }


}

class NewStoreListLunBo(val content : String) : MultiItemEntity {
    override fun getItemType(): Int {
        return 1
    }
}
class NewStoreListContent(val content : String) : MultiItemEntity {
    override fun getItemType(): Int {
        return 2
    }
}
