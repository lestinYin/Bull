package com.jinniu.delivery.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jinniu.delivery.R
import com.jinniu.delivery.activity.ADeliveryStoreDetail
import com.jinniu.delivery.activity.AStoreList
import com.jinniu.delivery.adapter.DeliveryHomeAdapter
import com.lestin.yin.base.FBase
import com.lestin.yin.entity.IHomeType
import com.lestin.yin.entity.Line
import kotlinx.android.synthetic.main.fragment_delivery_home.*

/**
 *
 * @ProjectName:    Taurus
 * @Package:        com.future.taurus.ui.home.fragment
 * @ClassName:      FHome
 * @Author:         Lestin.Yin
 * @CreateDate:     2019/7/25 11:49
 * @Description:     首页展示
 * @Version:        1.0
 */
class FDeliveryHome : FBase() {

    override fun layoutId(): Int = R.layout.fragment_delivery_home

    private var page: Int = 1

    override fun initData() {
        var deliveryList: MutableList<IHomeType> = ArrayList()
        var line = Line("1")
        deliveryList.add(line)
        deliveryList.add(line)
        deliveryList.add(line)
        deliveryList.add(line)
        deliveryList.add(line)
        deliveryList.add(line)
        deliveryList.add(line)

        var homeAdapter = DeliveryHomeAdapter(deliveryList)
        recylerView.adapter = homeAdapter

        homeAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            startActivity(Intent(context,ADeliveryStoreDetail::class.java))
        }


    }


    override fun initView() {
        recylerView.layoutManager = LinearLayoutManager(context)

//        recylerView.isNestedScrollingEnabled = false//禁止滑动


    }

    override fun start() {
    }

    private val PARAM_STATUS = "status"

    companion object {
        private const val PARAM_STATUS = "status"

        fun newInstance(status: String): FDeliveryHome {
            val fragment = FDeliveryHome()
            val args = Bundle()
            args.putString(PARAM_STATUS, status)
            fragment.arguments = args
            return fragment
        }
    }
}