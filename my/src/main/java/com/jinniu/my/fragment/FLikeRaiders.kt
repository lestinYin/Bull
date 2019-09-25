package com.jinniu.my.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.future.taurus.ui.home.entity.EHome
import com.future.taurus.ui.home.entity.LHome
import com.jinniu.my.R
import com.jinniu.my.adapter.CouponAdapter
import com.jinniu.my.adapter.LikeStoreAdapter
import com.jinniu.my.adapter.MyAddressAdapter
import com.lestin.yin.base.FBase
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder

/**
 *
 * @ProjectName:    Taurus
 * @Package:        com.jinniu.my.fragment
 * @ClassName:      FWallet
 * @Description:     java类作用描述
 * @Author:         lestin.yin
 * @CreateDate:     2019-09-09 11:29
 * @Version:        1.0
 */
class FLikeRaiders : FBase() {
    var recyclerView : RecyclerView ?= null
    override fun layoutId(): Int = R.layout.list_common_new


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        recyclerView = view!!.findViewById<RecyclerView>(R.id.recylerView)

        super.onViewCreated(view, savedInstanceState)
    }
    override fun initData() {
        setTools()

    }

    override fun initView() {
        //设置布局管理器
        recyclerView!!.layoutManager = GridLayoutManager(context, 2,
                LinearLayoutManager.VERTICAL, false)

    }

    override fun start() {

    }


    companion object {
        private const val PARAM_STATUS = "status"

        fun newInstance(status: String): FLikeRaiders {
            val fragment = FLikeRaiders()
            val args = Bundle()
            args.putString(PARAM_STATUS, status)
            fragment.arguments = args
            return fragment
        }
    }
    /**
     * 设置工具集
     */
    private fun setTools() {
        val stringArray = resources.getStringArray(R.array.my_tools)

        var adapter = object : CommonAdapter<String>(context, R.layout.item_like_raiders, stringArray.toMutableList()) {
            override fun convert(holder: ViewHolder?, t: String?, position: Int) {


            }
        }
        recyclerView!!.adapter = adapter
    }

}