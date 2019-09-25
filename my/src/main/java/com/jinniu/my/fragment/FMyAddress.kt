package com.jinniu.my.fragment

import com.future.taurus.ui.home.entity.EHome
import com.future.taurus.ui.home.entity.LHome
import com.jinniu.my.adapter.MyAddressAdapter
import com.lestin.yin.base.AdapterInterface
import com.lestin.yin.base.BaseCommonListFragment
import io.reactivex.Observable

/**
 *
 * @ProjectName:    Taurus
 * @Package:        com.jinniu.my.fragment
 * @ClassName:      FMyAddress
 * @Description:     我的地址
 * @Author:         lestin.yin
 * @CreateDate:     2019-09-06 14:43
 * @Version:        1.0
 */
class FMyAddress : BaseCommonListFragment<LHome, EHome>() {
    override fun initData() {
    }

    override fun getAdapter(): AdapterInterface<EHome> {
        var adapter = MyAddressAdapter()
        return adapter

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

}
