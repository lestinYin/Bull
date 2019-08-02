package com.future.taurus.ui.home.fragment

import android.os.Bundle
import com.future.taurus.R
import com.future.taurus.ui.home.adapter.HomeAdapter
import com.future.taurus.ui.home.entity.EHome
import com.future.taurus.ui.home.entity.LHome
import com.lestin.yin.base.AdapterInterface
import com.lestin.yin.base.BaseListMoreFragment
import io.reactivex.Observable
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
class FHome : BaseListMoreFragment<LHome,EHome>(){
    override fun getAdapter(): AdapterInterface<EHome> {
        return HomeAdapter(data = null)
    }

    override fun getData(page: Int): Observable<LHome> {
        var home = LHome()
        var eHome = EHome(1,"")
        var eHome2 = EHome(1,"")
        var eHome6 = EHome(1,"")
        var eHome3 = EHome(2,"")
        var eHome4 = EHome(3,"")
        var eHome5 = EHome(3,"")
        var eHome7 = EHome(3,"")

        var list : MutableList<EHome> = ArrayList()
        list.add(eHome)
        list.add(eHome2)
        list.add(eHome6)
        list.add(eHome3)
        list.add(eHome4)
        list.add(eHome5)
        list.add(eHome7)
        var homes : LHome.Home = LHome.Home()
        homes.setList(list)

        home.code = 200
        home.data = homes

        return Observable.just(home)
    }


    override fun initData() {
    }

    override fun initView() {
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