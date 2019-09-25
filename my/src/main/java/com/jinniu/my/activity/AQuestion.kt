package com.jinniu.my.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.chad.library.adapter.base.entity.AbstractExpandableItem
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.jinniu.my.R
import com.jinniu.my.adapter.QuestionAdapter
import com.lestin.yin.base.ABase
import kotlinx.android.synthetic.main.activity_aquestion.*

class AQuestion : ABase() {
    override fun layoutId(): Int = R.layout.activity_aquestion

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.white).init()
        val question = intent.getIntExtra("question",0)
        when(question) {
            1 -> {
                tv_title.text = resources.getString(R.string.pay_question)
            }
            2 -> {
                tv_title.text = resources.getString(R.string.delivery_question)
            }
            3 -> {
                tv_title.text = resources.getString(R.string.other_question)
            }
        }



        var list = arrayListOf<MultiItemEntity>()
        var leavel0 = Leavel0("红包无法使用")
        var leavel1 = Leavel0("我要申请退款")
        var leavel2 = Leavel0("投诉商家")
        var leavel3 = Leavel0("投诉骑手")
        var leavel4 = Leavel0("我要追加备注")


        var leavel0Sub = Leavel1("请您别着急，如果您下单时红包或代金券不能用，您可在提交订单页面点击美团红包或商家代金券，进入后会有不可用原因展示，您根据不能用的原因调整订单满足条件就可以使用啦。\n" +
                "温馨提示：\n" +
                "1.非到店自取订单指的是由骑手或商家进行配送的订单。\n" +
                "2.首单红包仅限首次在美团外卖下单时使用，如首次下单未使用首单红包，则下次下单无法再享受首单红包优惠哦。")
        var leavel1Sub = Leavel1("如需退款请按以下步骤进行操作。\n" +
                "外卖客户端点击“订单-全部订单-选择您要退款的订单-进入订单详情-申请退款”即可。\n" +
                "温馨提示：\n" +
                "订单超过24小时将无法申请退款。")
        var leavel2Sub = Leavel1("很抱歉因商家服务问题给您带来不好的体验，我们向您表示歉意，您可在订单评价页如实评价，我们会纳入商家的考核排名，您也可以点击我的客服的【电话客服】，会有专人协助您处理。")
        var leavel3Sub = Leavel1("因骑手服务问题给您带来不好的服务体验，我们向您表示歉意，您可在订单评价页如实评价，我们会纳入骑手的考核。您也可以在意见反馈进行投诉。\n")
        var leavel4Sub = Leavel1("请您在订单页查询一下目前订单状态。若是骑手未取餐时建议您联系商家沟通一下哦，骑手取餐后咱们的餐品就不能增加备注了呢。")
        leavel0.addSubItem(leavel0Sub)

        leavel1.addSubItem(leavel1Sub)
        leavel2.addSubItem(leavel2Sub)
        leavel3.addSubItem(leavel3Sub)
        leavel4.addSubItem(leavel4Sub)




        list.add(leavel0)
        list.add(leavel1)
        list.add(leavel2)
        list.add(leavel3)
        list.add(leavel4)

        var adapter = QuestionAdapter(list)
        recylerView_question.adapter = adapter
    }

    override fun initData() {
        iv_back.setOnClickListener {
            finish()
        }
    }

    override fun start() {
    }


}

class Leavel0(val title: String) : AbstractExpandableItem<Leavel1>(),MultiItemEntity{
    override fun getLevel(): Int {
        return 0
    }


    override fun getItemType(): Int {
        return 0
    }

}


class Leavel1(val content : String) : MultiItemEntity {
    override fun getItemType(): Int {
        return 1
    }
}
