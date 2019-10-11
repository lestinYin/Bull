package com.future.taurus.ui.home.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.future.taurus.R
import com.future.taurus.ui.home.adapter.RecommendDapeiAdapter
import com.future.taurus.ui.home.entity.EHome
import com.future.taurus.ui.home.entity.LHome
import com.jinniu.my.adapter.CouponAdapter
import com.jinniu.my.adapter.MyAddressAdapter
import com.jinniu.my.adapter.MyWalletAdapter
import com.jinniu.my.fragment.FLikeRaiders
import com.lestin.yin.base.AdapterInterface
import com.lestin.yin.base.BaseCommonListFragment
import com.lestin.yin.base.FBase
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import io.reactivex.Observable

/**
 *
 * @ProjectName:    Taurus
 * @Package:        com.jinniu.my.fragment
 * @ClassName:      FWallet
 * @Description:     员工风采
 * @Author:         lestin.yin
 * @CreateDate:     2019-10-04 21:20
 * @Version:        1.0
 */
class FStaffStyle : FBase() {
    var recyclerView : RecyclerView?= null
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
        recyclerView!!.setBackgroundColor(resources.getColor(R.color.color_f3f3f3))

    }

    override fun start() {

    }


    companion object {
        private const val PARAM_STATUS = "status"

        fun newInstance(status: String): FStaffStyle {
            val fragment = FStaffStyle()
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

        var adapter = object : CommonAdapter<String>(context, R.layout.item_staff_style, stringArray.toMutableList()) {
            override fun convert(holder: ViewHolder?, t: String?, position: Int) {


            }
        }
        recyclerView!!.adapter = adapter
    }
}