package com.jinniu.my.activity


import android.content.Intent
import com.lestin.yin.base.ABase
import com.lestin.yin.utils.image.ShowImage
import kotlinx.android.synthetic.main.activity_amy.*
import android.support.v7.widget.GridLayoutManager
import android.widget.ImageView
import android.widget.TextView
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.jinniu.my.R
import com.lestin.yin.utils.ViewUtil
import kotlinx.android.synthetic.main.activity_amy.iv_my_back
import kotlinx.android.synthetic.main.activity_amy.rl_head_img
import kotlinx.android.synthetic.main.activity_amy_account.*


@Route(path = "/my/setting")
class AMy : ABase() {
    val infoImg = arrayOf(R.mipmap.icon_dianhua, R.mipmap.icon_bangzhu, R.mipmap.icon_huidui, R.mipmap.icon_xiaozan, R.mipmap.icon_shanghu, R.mipmap.icon_ruzhu, R.mipmap.icon_hezuo, R.mipmap.icon_qishou)

    override fun layoutId(): Int {


        return R.layout.activity_amy

    }

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.transparent).init()
        //设置布局管理器
        rv_my.layoutManager = GridLayoutManager(this, 4,
                LinearLayoutManager.VERTICAL, false)
    }

    override fun initData() {

        ShowImage.showCircle(applicationContext, "https://upload.jianshu.io/users/upload_avatars/3333716/14924725-5e8f-4e3c-b77e-20a80f9b2ee3.png", iv_head_img)

        iv_my_back.setOnClickListener {
            finish()
        }
        //跳转到我的账户
        rl_head_img.setOnClickListener {
            startActivity(Intent(this,AMyAccount::class.java))
        }

        miv_wallet.setOnClickListener {
            startActivity(Intent(this,AWallet::class.java))
        }

        iv_setting.setOnClickListener {
            startActivity(Intent(this,ASetting::class.java))
        }
        //我的客服
        miv_my_customer_service.setOnClickListener {
            startActivity(Intent(this,ACustomerService::class.java))
        }
        //优惠卷
        miv_coupon.setOnClickListener {
            startActivity(Intent(this,ACoupon::class.java).putExtra("type","coupon"))
        }
        //我的喜欢
        miv_my_like.setOnClickListener {
            startActivity(Intent(this,ACoupon::class.java).putExtra("type","like"))
        }
        //积分中心
        miv_integral.setOnClickListener {
            startActivity(Intent(this,AIntegralCenter::class.java))
        }
        //我的评价
        miv_my_comment.setOnClickListener {
            startActivity(Intent(this,AComment::class.java))
        }
        //我的地址
        miv_my_address.setOnClickListener {
            startActivity(Intent(this,AMyReceiptAddress::class.java))
        }

//        miv_money.rightText.setTextColor(resources.getColor(R.color.colorPrimary))
//        miv_money.rightText.textSize = 15F

        val drawable = resources.getDrawable(R.mipmap.coin)
        drawable.setBounds(0, 0, drawable.minimumWidth,drawable.minimumHeight)
        join_daren.text.setCompoundDrawables(null,null,drawable,null)
        join_daren.text.compoundDrawablePadding = ViewUtil.dp2px(5)

        join_tuiguang.text.setCompoundDrawables(null,null,drawable,null)
        join_tuiguang.text.compoundDrawablePadding = ViewUtil.dp2px(5)

    }

    override fun start() {
        setTools()

    }

    /**
     * 设置工具集
     */
    private fun setTools() {
        val stringArray = resources.getStringArray(R.array.my_tools)
        var adapter = object : CommonAdapter<String>(this, R.layout.item_my_tools, stringArray.toMutableList()) {
            override fun convert(holder: ViewHolder?, t: String?, position: Int) {
                holder!!.getView<ImageView>(R.id.iv_item_my).background = getDrawable(infoImg[position])
                holder!!.getView<TextView>(R.id.tv_item_my).text = stringArray[position]


            }
        }
        rv_my.adapter = adapter
    }

}
