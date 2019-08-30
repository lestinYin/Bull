package com.jinniu.delivery.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.lestin.yin.base.ABase
import kotlinx.android.synthetic.main.activity_asearch_delivery.*
import android.view.inputmethod.EditorInfo
import android.widget.PopupWindow
import android.widget.TextView
import com.jinniu.delivery.R
import com.jinniu.delivery.fragment.FDeliveryHome
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import kotlinx.android.synthetic.main.activity_adelivery_home_test.*
import kotlinx.android.synthetic.main.activity_asearch_delivery.radio_group
import kotlinx.android.synthetic.main.include_delivery_choice.*


/**
 * 搜索店铺页
 */
class ASearchDelivery : ABase() {
    private lateinit var popupWindow: PopupWindow
    private var isShowPopu = false

    override fun layoutId(): Int = R.layout.activity_asearch_delivery

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.transparent).init()

//        openKeyBord(et_search_delivery,this)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        createDialogs()
    }

    override fun initData() {
    }

    override fun start() {
        tv_cancel.setOnClickListener {
            closeKeyBord(et_search_delivery, this)
            finish()
        }

        et_search_delivery.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //关闭软键盘
                    closeKeyBord(et_search_delivery, applicationContext)
//                    finish()
                    showStoreList()
                    return true
                }
                return false
            }
        })


        radio_group.setOnCheckedChangeListener { group, checkedId ->
            //            include_delivery_top.visibility = View.GONE
            if (checkedId == R.id.rb_zonghe) {
                isShowPopu = true

                popupWindow.showAsDropDown(radio_group)

            }
        }

    }

    private var fDeliveryHome: FDeliveryHome? = null
    private fun showStoreList() {
        rl_history_search.visibility = View.GONE
        rl_show_list.visibility = View.VISIBLE
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        if (fDeliveryHome == null) {
            fDeliveryHome = FDeliveryHome.newInstance("附近商家")
            fragmentTransaction.add(R.id.fl_show_search, fDeliveryHome).commit()
        } else {
            fragmentTransaction.show(fDeliveryHome).commit()
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


}
