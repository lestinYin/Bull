package com.jinniu.my.fragment

import android.os.Bundle
import com.future.taurus.ui.home.entity.EHome
import com.future.taurus.ui.home.entity.LHome
import com.jinniu.my.adapter.CouponAdapter
import com.jinniu.my.adapter.MyAddressAdapter
import com.jinniu.my.adapter.MyWalletAdapter
import com.lestin.yin.base.AdapterInterface
import com.lestin.yin.base.BaseCommonListFragment
import com.lestin.yin.base.FBase
import io.reactivex.Observable

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
class FCoupon : BaseCommonListFragment<LHome, EHome>() {
    override fun getAdapter(): AdapterInterface<EHome> {
        var adapter = CouponAdapter()
        return adapter
    }

    override fun initData() {

    }

    override fun getData(page: Int): Observable<LHome> {
        //set data
        var home = LHome()
        var eHome = EHome(1,"")
        var eHome2 = EHome(1,"")
        var eHome6 = EHome(1,"")
        var list : MutableList<EHome> = ArrayList()
        list.add(eHome)
        list.add(eHome2)
        list.add(eHome6)
        var homes : LHome.Home = LHome.Home()
        homes.setList(list)

        home.code = 200
        home.data = homes

        return Observable.just(home)
    }

    override fun initView() {
    }

    companion object {
        private const val PARAM_STATUS = "status"

        fun newInstance(status: String): FCoupon {
            val fragment = FCoupon()
            val args = Bundle()
            args.putString(PARAM_STATUS, status)
            fragment.arguments = args
            return fragment
        }
    }
}