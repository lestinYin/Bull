package com.future.taurus.ui.home.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.future.taurus.R
import com.future.taurus.ui.home.adapter.NewStoreListAdapter
import com.future.taurus.ui.home.entity.EHome
import com.future.taurus.ui.home.entity.LHome
import com.future.taurus.ui.home.entity.LStoreList
import com.jinniu.my.adapter.CouponAdapter
import com.jinniu.my.adapter.LikeStoreAdapter
import com.jinniu.my.adapter.MyAddressAdapter
import com.jinniu.my.adapter.MyWalletAdapter
import com.lestin.yin.base.AdapterInterface
import com.lestin.yin.base.BaseCommonListFragment
import io.reactivex.Observable
import kotlinx.android.synthetic.main.item_storelist_horizental_item.*

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
class FNewStoreList : BaseCommonListFragment<LStoreList, MultiItemEntity>() {

    override fun getAdapter(): AdapterInterface<MultiItemEntity> {
        var list : MutableList<MultiItemEntity> = ArrayList()
        var adapter = NewStoreListAdapter(list)
        return adapter
    }

    override fun initData() {

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView.setBackgroundColor(resources.getColor(R.color.color_f3f3f3))
    }

    override fun getData(page: Int): Observable<LStoreList> {
        var home = LStoreList()
        if (page == 1) {
            //set data
            var eHome = ENewStoreLunBo("")
            var eHome2 = ENewStoreContent("")
            var eHome6 = ENewStoreContent("")

            var list: MutableList<MultiItemEntity> = ArrayList()
            list.add(eHome)
            list.add(eHome2)
            list.add(eHome6)

            var homes: LStoreList.Home = LStoreList.Home()
            homes.setList(list)

            home.code = 200
            home.data = homes
        } else {
            var eHome2 = ENewStoreContent("")
            var eHome6 = ENewStoreContent("")

            var list: MutableList<MultiItemEntity> = ArrayList()
            list.add(eHome2)
            list.add(eHome6)

            var homes: LStoreList.Home = LStoreList.Home()
            homes.setList(list)

            home.code = 200
            home.data = homes
        }

        return Observable.just(home)

    }

    override fun initView() {
    }

    companion object {
        private const val PARAM_STATUS = "status"

        fun newInstance(status: String): FNewStoreList {
            val fragment = FNewStoreList()
            val args = Bundle()
            args.putString(PARAM_STATUS, status)
            fragment.arguments = args
            return fragment
        }
    }

}

class ENewStoreLunBo(val content : String) : MultiItemEntity {
    override fun getItemType(): Int {
        return 100
    }
}
class ENewStoreContent(val content : String) : MultiItemEntity {
    override fun getItemType(): Int {
        return 200
    }
}