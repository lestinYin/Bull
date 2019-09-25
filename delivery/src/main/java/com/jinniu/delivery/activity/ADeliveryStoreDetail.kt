package com.jinniu.delivery.activity

import android.annotation.SuppressLint
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jinniu.delivery.Data
import com.jinniu.delivery.R
import com.jinniu.delivery.activity
import com.jinniu.delivery.fragment.*
import com.lestin.yin.base.ABase
import com.lestin.yin.utils.*
import com.lestin.yin.utils.image.ShowImage
import com.lestin.yin.widget.CommonPagerAdapter
import kotlinx.android.synthetic.main.activity_adelivery_store_detail.*
import kotlinx.android.synthetic.main.include_delivery_store_detail_head.*
import kotlinx.android.synthetic.main.include_store_detail_cart.*
import java.util.ArrayList

/**
  * @ProjectName:    ${PACKAGE_NAME}
  * @Package:
  * @ClassName:
  * @Description:    店铺详情页
  * @Author:         lestin.yin
  * @CreateDate:
  * @Version:        1.0
 */
class ADeliveryStoreDetail : ABase() {
    //是否展示了购物车详情
    var isShowCartDetail = true

    override fun layoutId(): Int = R.layout.activity_adelivery_store_detail

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.transparent).init()

//        rl_store_head.translationZ = 10F
//        setSupportActionBar(toolbar)

        ShowImage.show(this,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566210613338&di=6dd642ad8802e95cc8779406941d8ee5&imgtype=0&src=http%3A%2F%2Fpic67.nipic.com%2Ffile%2F20150514%2F3269097_162102734000_2.jpg",mIv_store_detail)

    }

    override fun initData() {
        toolbar.setNavigationOnClickListener {
            finish()
        }

        val tabList = resources.getStringArray(R.array.tab_store_detail)


        val fragmentsDis = ArrayList<Fragment>()
        fragmentsDis.add(FChoiceFood.newInstance(tabList[0].toString()))
        fragmentsDis.add(FStoreReview.newInstance(tabList[1].toString()))
        fragmentsDis.add(FStoreDetail.newInstance(tabList[2].toString()))


        val adapterDis = CommonPagerAdapter(applicationContext, this.supportFragmentManager, fragmentsDis, tabList.toMutableList())
        vp_store_detail.adapter = adapterDis
        tb_store_detail.tabMode = TabLayout.MODE_FIXED
        tb_store_detail.setupWithViewPager(vp_store_detail)

        SetIndicater.setIndicator(tb_store_detail)
        SetIndicater.setTabTextStyle(tb_store_detail, true, 0)
        tb_store_detail.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val position = tab.position
                //切换到review和Detail不显示购物车
                if (position == 0) {
                    include_shop_car.visibility = View.VISIBLE
                } else {
                    include_shop_car.visibility = View.GONE
                }

                //选中加粗字体
                SetIndicater.setTabTextStyle(tb_store_detail, true, position)
                vp_store_detail.setCurrentItem(tab.position, false)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val position = tab.position
                //不选中细字体
                SetIndicater.setTabTextStyle(tb_store_detail, false, position)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        dealClick()
    }

    /**
     * 逻辑处理
     */
    private fun dealClick() {
        rl_cart.setOnClickListener {
            showShopCartDetail()
        }
        tv_check_out.setOnClickListener {
            ToastUtils.showShort("check out")
        }
        rl_shop_car_detail.setOnClickListener {
            showShopCartDetail()
        }

    }
    //是否显示购物车详情
    private fun showShopCartDetail() {

        if (isShowCartDetail) {
            rl_shop_car_detail.visibility = View.VISIBLE
            rl_cart_sale_msg.visibility = View.GONE
            Utils.setViewMargin(rl_cart,true,0,0,0,0)
            setShopCarData()
        } else {
            rl_shop_car_detail.visibility = View.GONE
            rl_cart_sale_msg.visibility = View.VISIBLE
            Utils.setViewMargin(rl_cart,true,0,0,-35,0)
        }
        isShowCartDetail = !isShowCartDetail

    }
    //设置购物车详情的列表数据
    private fun setShopCarData() {
        val foodDetails = Data.foodMenus()
        val foodAdapter = FoodAdapter(foodDetails)
        recycler_shop_car.adapter = foodAdapter


    }

    @SuppressLint("ResourceAsColor")
    override fun start() {
        //展开伸缩tag详情
        iv_arrow_show_detail.setOnClickListener {
//            rl_show_tag.visibility = View.GONE
//            ll_show_detail.visibility = View.VISIBLE
            HiddenAnimUtils.newInstance(this,ll_show_detail,iv_arrow_show_detail,ViewUtil.px2dp(rl_store_head.height.toFloat())-50).toggle()


        }
//        iv_show_tag.setOnClickListener {
////            rl_show_tag.visibility = View.VISIBLE
////            ll_show_detail.visibility = View.GONE
//            HiddenAnimUtils.newInstance(this,rl_show_tag,iv_arrow_show_detail,ViewUtil.px2dp(rl_store_head.height.toFloat())).toggle()
//
//
//        }
    }


}
//左侧列表
class FoodAdapter(data: List<FoodTitle>) : BaseQuickAdapter<FoodTitle, BaseViewHolder>(data) {
    init {
        mLayoutResId = R.layout.item_shop_cart
    }
    override fun convert(helper: BaseViewHolder?, item: FoodTitle?) {

        val vAdd = helper!!.getView<ImageView>(R.id.vAdd)
        val vLess = helper.getView<ImageView>(R.id.vLess)
        val choiceNumber = helper.getView<TextView>(R.id.tv_cart_number)

        //数量更改
        vAdd.setOnClickListener {
            val number = Integer.valueOf(choiceNumber.text.toString())
            vLess.visibility = View.VISIBLE
            choiceNumber.visibility = View.VISIBLE
            choiceNumber.text = (number + 1).toString()

            //开启动画
            var startLocation = IntArray(2)
            vAdd.getLocationInWindow(startLocation)
            var flyRes: ImageView = ImageView(mContext)
            flyRes.setBackgroundResource(R.mipmap.add)

            var finalView = mContext.activity()?.findViewById<RelativeLayout>(R.id.include_shop_car)?.findViewById<TextView>(R.id.tv_shopcart_number)

            mContext.activity()?.let { it1 -> FlyUtils.setAnim(it1,flyRes,startLocation, finalView!!) }
        }
        //点击减
        vLess.setOnClickListener {
            val number = Integer.valueOf(choiceNumber.text.toString())
            if (number==1) {
                vLess.visibility = View.GONE
                choiceNumber.visibility = View.GONE
            }
            choiceNumber.text = (number - 1).toString()

        }
    }




}
