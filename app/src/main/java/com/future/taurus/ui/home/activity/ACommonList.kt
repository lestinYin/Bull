package com.future.taurus.ui.home.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.future.taurus.R
import com.future.taurus.ui.home.fragment.FNewStoreList
import com.future.taurus.ui.home.fragment.FRecommendDapei
import com.future.taurus.ui.home.fragment.FStaffStyle
import com.lestin.yin.base.ABase

/**
 * 公有列表Activity
 */
class ACommonList : ABase() {
    private var type: Int = 0

    override fun layoutId(): Int = R.layout.activity_common_fragment

    override fun initView() {
        type = intent.getIntExtra("type", 1)


        var fMyAddress: Fragment = FNewStoreList()
        when (type) {
            1 -> {
                mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.colorPrimary).init()
                fMyAddress = FNewStoreList()
            }
            //推荐搭配页面
            2 -> {
                mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.white).init()
                fMyAddress = FRecommendDapei()
            }
            //员工风采页面
            3 -> {
                mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.white).init()
                fMyAddress = FStaffStyle()
            }
        }

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
        var title = findViewById<TextView>(R.id.tv_title)
        toolbar.title = ""

        when (type) {
            1 -> {
                toolbar.setBackgroundColor(resources.getColor(R.color.colorPrimary))

                title.text = resources.getString(R.string.where_is_eat)
            }
            2 -> {
                toolbar.setBackgroundColor(resources.getColor(R.color.white))
                title . text = resources . getString (R.string.recommend_mix)
            }
            3 -> {
                toolbar.setBackgroundColor(resources.getColor(R.color.white))
                title . text = resources . getString (R.string.staff_style)
            }

        }


        var rightImage = findViewById<ImageView>(R.id.iv_rigth)
        rightImage.visibility = View.VISIBLE
        rightImage.background = resources.getDrawable(R.mipmap.black_clock)
    }


}

class NewStoreListLunBo(val content: String) : MultiItemEntity {
    override fun getItemType(): Int {
        return 1
    }
}

class NewStoreListContent(val content: String) : MultiItemEntity {
    override fun getItemType(): Int {
        return 2
    }
}
