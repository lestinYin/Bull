package com.jinniu.delivery.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.jinniu.delivery.R
import com.jinniu.delivery.entity.ETestAddClose
import com.jinniu.delivery.entity.ETestAddMy
import com.jinniu.delivery.entity.ETestAddTitle
import com.jinniu.delivery.entity.ETestSearchAddressResult
import com.jinniu.delivery.fragment.FoodAdapter
import com.jinniu.delivery.fragment.FoodCover
import com.jinniu.delivery.fragment.setImageUrl
import com.lestin.yin.base.ABase
import com.lestin.yin.utils.ViewUtil
import com.oushangfeng.pinnedsectionitemdecoration.PinnedHeaderItemDecoration
import kotlinx.android.synthetic.main.activity_achoice_receipt_add.*
import kotlinx.android.synthetic.main.merchant_page_food_layout.*

/**
 * 选择收货地址页面
 */
class AChoiceReceiptAdd : ABase() {
    override fun layoutId(): Int = R.layout.activity_achoice_receipt_add

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.white).init()
    }

    override fun initData() {
        val list = mutableListOf(ETestAddTitle("我的收货地址", R.mipmap.my_address), ETestAddMy(""), ETestAddMy(""), ETestAddMy("")
                , ETestAddTitle("附近地址", R.mipmap.location_solid)
                , ETestAddClose(""), ETestAddClose(""), ETestAddClose("")
        )
        val showAddAdapter = ShowAddAdapter(list)
        recycler_show_add.addItemDecoration(PinnedHeaderItemDecoration.Builder(ShowAddAdapter.TYPE_HEAD)
                .setDividerId(R.drawable.transparent)
                .create())
        recycler_show_add.adapter = showAddAdapter
        showAddAdapter.openLoadAnimation()

        //搜索按钮
        tv_search.setOnClickListener {
            searchState(false)
            rl_location.visibility = View.GONE
            list.clear()
            list.add(ETestSearchAddressResult(""))
            list.add(ETestSearchAddressResult(""))
            list.add(ETestSearchAddressResult(""))
            list.add(ETestSearchAddressResult(""))
            list.add(ETestSearchAddressResult(""))
            showAddAdapter.notifyDataSetChanged()

        }

    }

    override fun start() {
        iv_back.setOnClickListener {
            finish()
        }
        et_search_address.setOnClickListener {
            searchState(true)

        }
        view_transparent.setOnClickListener {
            searchState(false)
        }

    }
    //是否处于搜索状态中
    private fun searchState(showState: Boolean) {
        if (showState) {
            et_search_address.isFocusable = true
            view_transparent.visibility = View.VISIBLE
            openKeyBord(et_search_address,this)
            tv_search.visibility = View.VISIBLE
        } else {
            et_search_address.isFocusable = false
            view_transparent.visibility = View.GONE
            closeKeyBord(et_search_address,this)
            tv_search.visibility = View.GONE
        }

    }

}

class ShowAddAdapter(data: List<MultiItemEntity>) : BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(data) {

    companion object {
        var TYPE_HEAD = 1
        var TYPE_MY_ADD = TYPE_HEAD++
        var TYPE_CLOSE_ADD = TYPE_HEAD++
        var TYPE_RESEARCH_RESULT = TYPE_HEAD++

    }


    init {
//        addItemType(TYPE_TOP, R.layout.merchant_food_list_top)
//        addItemType(TYPE_RECOMMEND, R.layout.merchant_food_list_recommend)
        addItemType(TYPE_HEAD, R.layout.merchant_food_list_side)
        addItemType(TYPE_MY_ADD, R.layout.item_my_address)
        addItemType(TYPE_CLOSE_ADD, R.layout.item_my_address)
        addItemType(TYPE_RESEARCH_RESULT, R.layout.item_search_add_result)
    }

    override fun convert(helper: BaseViewHolder?, item: MultiItemEntity?) {
        when (helper!!.itemViewType) {
            TYPE_HEAD -> {
                (item as ETestAddTitle).apply {
//                    helper.setImageUrl(R.id.vImage, url)
                    val title = helper.getView<TextView>(R.id.vTitle)
                    val titleRoot = helper.getView<RelativeLayout>(R.id.vTitle_root)
                    title.text = item.title
                    helper.getView<TextView>(R.id.tv_right_circle).visibility = View.GONE
                    val drawableLeft = mContext.resources.getDrawable(item.pic)
                    title.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,null,null,null)
                    title.compoundDrawablePadding = 10

                    //重新设置title的高度
                    val layoutParams = titleRoot.layoutParams
                    layoutParams.height = ViewUtil.dp2px(40)
                    titleRoot.layoutParams = layoutParams


                }
            }
            TYPE_MY_ADD -> {
                (item as ETestAddMy).apply {
//                    helper.setImageUrl(R.id.vImage, url)
                }
            }
            TYPE_CLOSE_ADD -> {
                (item as ETestAddClose).apply {
                    helper.getView<ConstraintLayout>(R.id.constraint_my_add).visibility = View.GONE
                    helper.getView<TextView>(R.id.tv_close_add).visibility = View.VISIBLE
//                    helper.setImageUrl(R.id.vImage, url)
                }
            }
        }

    }

}
