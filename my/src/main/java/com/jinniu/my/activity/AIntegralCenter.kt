package com.jinniu.my.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.lestin.yin.base.ABase
import com.lestin.yin.utils.ViewUtil
import kotlinx.android.synthetic.main.activity_aintegral_center.*
import java.io.Serializable
import com.jinniu.my.R


class AIntegralCenter : ABase() {
    override fun layoutId(): Int = R.layout.activity_aintegral_center

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.transparent).init()
        tv_integral_number.setPadding(0, 0, 0, -30)
    }

    override fun initData() {
        iv_back.setOnClickListener {
            finish()
        }
        rl_duihuan.setOnClickListener {
            startActivity(Intent(this,AWithDraw::class.java).putExtra("isDuihuan",true))
        }
        //积分明细
        tv_integral_detail.setOnClickListener {
            startActivity(Intent(this,ACoupon::class.java).putExtra("type","integralDetail"))
        }
    }

    override fun start() {
        var list = arrayListOf<MultiItemEntity>()
        list.add(FoodTitle("",true))
        list.add(FoodContent(""))
        list.add(FoodContent(""))
        list.add(FoodTitle("",true))
        list.add(FoodContent(""))
        list.add(FoodContent(""))
        list.add(FoodContent(""))

        var adapter = IntegralAdapter(list)
        vRecycler_integral.adapter = adapter



    }

}

class IntegralAdapter(data: List<MultiItemEntity>) : BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(data), BaseQuickAdapter.OnItemClickListener {
    companion object {
        var TYPE_TITLE = 1
        //        val TYPE_TOP = TYPE_PRODUCER++
//        val TYPE_RECOMMEND = TYPE_PRODUCER++
        val TYPE_CONTENT = TYPE_TITLE++
    }

    init {
        addItemType(TYPE_TITLE, R.layout.item_integral_title)
        addItemType(TYPE_CONTENT, R.layout.item_integral)
        this.onItemClickListener = this
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
    }

    override fun convert(helper: BaseViewHolder?, item: MultiItemEntity?) {
        when (helper!!.itemViewType) {
            TYPE_CONTENT -> {
                (item as FoodContent).apply {
                    if (helper.layoutPosition == data.size-1) {
                        val view = helper.getView<TextView>(R.id.tv_message)
                        view.setPadding(0,0,0,ViewUtil.dp2px(40))
                    }

                    val stateText = helper.getView<TextView>(R.id.tv_state)
                    val background : GradientDrawable = stateText.background as GradientDrawable
                    background.setStroke(ViewUtil.dp2px(1),mContext.resources.getColor(R.color.colorPrimary))


                }
            }

        }
    }

}

class FoodTitle(val title: String, var isSelected : Boolean) : MultiItemEntity {
    override fun getItemType(): Int = IntegralAdapter.TYPE_TITLE
}

class FoodContent(val name: String) : MultiItemEntity, Serializable {
    override fun getItemType(): Int = IntegralAdapter.TYPE_CONTENT
}





