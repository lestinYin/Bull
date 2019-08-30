package com.jinniu.delivery.activity

import android.content.Intent
import android.os.Handler
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.jinniu.delivery.fragment.FDeliveryHome
import com.lestin.yin.base.ABase
import com.lestin.yin.utils.SetIndicater
import com.lestin.yin.widget.CommonPagerAdapter
import java.util.ArrayList
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.lestin.yin.widget.listener.AppBarStateChangeListener
import kotlinx.android.synthetic.main.activity_adelivery_home.tb_delivery
import kotlinx.android.synthetic.main.activity_adelivery_home.vp_delivery
import kotlinx.android.synthetic.main.activity_adelivery_home_test.*
import kotlinx.android.synthetic.main.bar_delivery_home.*
import kotlinx.android.synthetic.main.include_delivery_choice.*
import android.widget.PopupWindow
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import com.jinniu.delivery.R
import com.jinniu.delivery.adapter.BannerAdapter
import com.jude.rollviewpager.hintview.ColorPointHintView
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import kotlinx.android.synthetic.main.include_delivery_home_top.*
import org.w3c.dom.Text




@Route(path = "/delivery/hometest")
class ADeliveryHomeTest : ABase() {
    private var mAlertDialog: AlertDialog? = null
    private var mDialogWindow: Window? = null
    internal var toolBarPositionY = 0
    private lateinit var popupWindow: PopupWindow;
    private var isShowPopu = false


    override fun layoutId(): Int = R.layout.activity_adelivery_home_test

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.transparent).init()
    }

    override fun initData() {
        iv_back.setOnClickListener {
            finish()
        }

        val titlesDis = ArrayList<CharSequence>()
        titlesDis.add("附近商家")
        titlesDis.add("精品推荐")

        val fragmentsDis = ArrayList<Fragment>()
        fragmentsDis.add(FDeliveryHome.newInstance(titlesDis[0].toString()))
        fragmentsDis.add(FDeliveryHome.newInstance(titlesDis[1].toString()))


        val adapterDis = CommonPagerAdapter(applicationContext, this.supportFragmentManager, fragmentsDis, titlesDis)
        vp_delivery.adapter = adapterDis
        tb_delivery.tabMode = TabLayout.MODE_SCROLLABLE
        tb_delivery.setupWithViewPager(vp_delivery)

        SetIndicater.setIndicator(tb_delivery)
        SetIndicater.setTabTextStyle(tb_delivery, true, 0)
        tb_delivery.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val position = tab.position
                //选中加粗字体
                SetIndicater.setTabTextStyle(tb_delivery, true, position)
                vp_delivery.setCurrentItem(tab.position, false)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val position = tab.position
                //不选中细字体
                SetIndicater.setTabTextStyle(tb_delivery, false, position)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
        dealBar()

        tv_delivery_home_food.setOnClickListener {
            var  intent = Intent(applicationContext, AStoreList::class.java)
            startActivity(intent)
        }
    }

    private fun dealBar() {
        // 监听滚动状态
        app_bar.addOnOffsetChangedListener(object : AppBarStateChangeListener() {

            override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
                if (state === State.EXPANDED) {
                    tool_bar_delivery_home_one.visibility = View.VISIBLE
                    tool_bar_delivery_home_two.visibility = View.GONE
                    //展开状态
                } else if (state === State.COLLAPSED) {
                    tool_bar_delivery_home_one.visibility = View.GONE
                    tool_bar_delivery_home_two.visibility = View.VISIBLE

                    //折叠状态
                } else {
                    //中间状态
//                    toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material)
                }
            }
        })

        radio_group.setOnCheckedChangeListener { group, checkedId ->
            //            include_delivery_top.visibility = View.GONE
            if (checkedId == R.id.rb_zonghe) {
                isShowPopu = true
                app_bar.setExpanded(false)
                Handler().postDelayed(Runnable {
                    popupWindow.showAsDropDown(include_choice)
                }, 300)    //延时1s执行
                // 延迟15秒
                // do something

            }
        }
    }

    //创建dialog
    private fun createDialogs() {
        //准备PopupWindow的布局
        var popupView = LayoutInflater.from(this).inflate(R.layout.popu_delivery_home_choice, null)
        val finish = popupView.findViewById<TextView>(R.id.delivery_popu_finish)
        val rvDeliveryHomePopu = popupView.findViewById<RecyclerView>(R.id.rv_delivery_home_popu)
        // 初始化一个PopupWindow，width和height都是WRAP_CONTENT
        popupWindow = PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//设置PopupWindow的视图内容
        popupWindow.contentView = popupView;
        //点击空白区域PopupWindow消失，这里必须先设置setBackgroundDrawable，否则点击无反应
        popupWindow.setBackgroundDrawable(resources.getDrawable(R.color.transparent));
        popupWindow.isOutsideTouchable = true;
        //设置PopupWindow动画popupWindow.setAnimationStyle(R.style.AnimDown);
        // 设置是否允许PopupWindow的范围超过屏幕范围
        popupWindow.isClippingEnabled = false

//        popupWindow.showAsDropDown(include_choice)

        finish.setOnClickListener {
            isShowPopu = false
            popupWindow.dismiss()
        }

        val res = resources

        rvDeliveryHomePopu.layoutManager = LinearLayoutManager(this)

        val homeSort = res.getStringArray(R.array.home_list_sort)
        var adapter = object : CommonAdapter<String>(this, R.layout.item_delivery_home_sort_list, homeSort.toMutableList()) {
            override fun convert(holder: ViewHolder?, t: String?, position: Int) {
                val view = holder!!.getView<TextView>(R.id.tv_delivery_home_sort_list)
                view.text = t

            }
        }
        rvDeliveryHomePopu.adapter = adapter
    }

    override fun start() {
        createDialogs()
        //进入选择收货地址
        tv_street_location.setOnClickListener {
            startActivity(Intent(this,AChoiceReceiptAdd::class.java))
        }
        //进入搜索界面
        rl_delivery_search.setOnClickListener {
            startActivity(Intent(this,ASearchDelivery::class.java))
        }

        setupBanner(mutableListOf("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566999272467&di=5a34c50eef55f0df14303006e078cb1b&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fln%2Fpics%2Fhv1%2F197%2F114%2F1750%2F113823017.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566999272624&di=1deed7ed084e1720678ca2882ff75aba&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201411%2F26%2F20141126160858_dWwxt.jpeg",
                "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=364055337,609376702&fm=27&gp=0.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1566989229&di=d7cc35d81cf849351643ebd1a0a239d8&src=http://img02.tooopen.com/images/20151128/tooopen_sy_149759881365.jpg"))

    }

    private fun setupBanner(bannerList: List<String>) {
        val mBannerAdapter = BannerAdapter(rollpager, applicationContext, bannerList)
        rollpager.setAdapter(mBannerAdapter)
        rollpager.setPlayDelay(3000)
        rollpager.setAnimationDurtion(500)
        rollpager.setHintPadding(5, 0, 15, 28)
        rollpager.setHintView(ColorPointHintView(this, ContextCompat.getColor(this, R.color.white),
                ContextCompat.getColor(this, R.color.transparent_five)))
    }

    /**
     * 控制appbar的滑动
     * @param isScroll true 允许滑动 false 禁止滑动
     */
    private fun banAppBarScroll(isScroll: Boolean) {
        val mAppBarChildAt = app_bar.getChildAt(0)
        val mAppBarParams = mAppBarChildAt.getLayoutParams() as AppBarLayout.LayoutParams
        if (isScroll) {
            mAppBarParams.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
            mAppBarChildAt.setLayoutParams(mAppBarParams)
        } else {
            mAppBarParams.scrollFlags = 0
        }

    }

}
