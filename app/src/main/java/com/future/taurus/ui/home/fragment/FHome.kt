package com.future.taurus.ui.home.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import com.future.taurus.R
import com.future.taurus.ui.home.adapter.HomeAdapter
import com.lestin.yin.Constants
import com.lestin.yin.base.FBase
import com.lestin.yin.entity.IHomeType
import com.lestin.yin.entity.Line
import com.lestin.yin.utils.LogUtil
import kotlinx.android.synthetic.main.list_common.*
import java.util.*

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
class FHome : FBase() {

    override fun layoutId(): Int = R.layout.fragment_delivery_home

    private var page: Int = 1

    override fun initData() {
        LogUtil.e("liangciliangci++++++++++++++")
        getDateFromServer()

    }

    @SuppressLint("CheckResult")
    private fun getDateFromServer() {
        mainModel.getHome("北京市", "1", "1", page.toString(), "5").subscribe { result ->
            if (Constants.SUCCESS.equals(result.code.toString())) {
                val reviewInfos = result.content.reviewInfos
                val stores = result.content.stores
                var list: MutableList<IHomeType> = ArrayList()
                list.addAll(stores)
                list.add(Line("3"))
                list.addAll(reviewInfos)

                var homeAdapter = HomeAdapter(list)

//                homeAdapter.addData(list)
                val footerView = getFooterView(0, View.OnClickListener { homeAdapter.addFooterView(getFooterView(1, getRemoveFooterListener()), 0) })
                homeAdapter.addFooterView(footerView, 0)

                recylerView.adapter = homeAdapter
                //数据加载完首页显示积分弹窗
//                EventBus.getDefault().postSticky(Event.HomeShowDialog())
                swipeRefreshLayout.isRefreshing = false
            }
        }
//        addSubscription(disposable)
    }

    private fun getFooterView(type: Int, listener: View.OnClickListener): View {
        val view = layoutInflater.inflate(R.layout.list_footer, recylerView.getParent() as ViewGroup, false)
        view.setOnClickListener(listener)
        return view
    }

    private fun getRemoveFooterListener(): View.OnClickListener {
        return View.OnClickListener { v ->  }
    }

    override fun initView() {
        recylerView.layoutManager = LinearLayoutManager(context)

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary)
        swipeRefreshLayout.isRefreshing = false

        swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            page = 1
            getDateFromServer()

        })
    }

    override fun start() {
    }

    private val PARAM_STATUS = "status"

    companion object {
        private const val PARAM_STATUS = "status"

        fun newInstance(status: String): FHome {
            val fragment = FHome()
            val args = Bundle()
            args.putString(PARAM_STATUS, status)
            fragment.arguments = args
            return fragment
        }
    }
}